package com.codeoftheweb.salvo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.util.stream.Collectors.toList;



@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private SalvoRepository salvoRepository;

    @RequestMapping(value = "/games",method = RequestMethod.GET)
    public Map<String,Object> getGamesInfo(Authentication authentication) {
        Map<String,Object> map = new HashMap<>();
        if (authentication != null) {
            Player player = playerRepository.findByUserName(authentication.getName()).get(0);
            map.put("player", makePlayersDTO(player));
            map.put("games", getGamesIds());
        } else {
            map.put("games", getGamesIds());
        }
        return map;
    }


    public List<Object> getGamesIds() {
        return gameRepository
                .findAll()
                .stream()
                .map(game -> makeGamesDTO(game))
                .collect(toList());

    }

    @RequestMapping("/leaderboard")
    private List<Object> getAllPlayers(){
        return playerRepository.findAll().stream().map(player -> makeLeaderboardDTO(player)).collect(toList());
    }
    private Map<String, Object> makeLeaderboardDTO(Player player){
        Map<String, Object> dto = new LinkedHashMap<>();
        Double totalScore = 0.0;
        Integer wins = 0;
        Integer losses = 0;
        Integer ties = 0;
        Set<Score> scoreSet = player.getScores();
        for(Score score : scoreSet){
            Double meme = score.getScore();
            System.out.println(meme);
            if(score.getScore() == 1.0){
                wins += 1;
                totalScore += 1.0;
            }
            if(score.getScore() == 0.5) {
                ties += 1;
                totalScore += 0.5;
            }
            if(score.getScore() == 0.0){
                losses +=1;
            }

        }
        dto.put("name", (player.getUserName()));
        dto.put("totalScore", totalScore);
        dto.put("numberOfWins", wins);
        dto.put("numberOfLosses", losses);
        dto.put("numberOfTies", ties);
        return dto;
    }


    private Map<String, Object> makeGamesDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", game.getId());
        dto.put("created", game.getStartDate());
        dto.put("gamePlayers", game.getGamePlayers().
                stream()
                .map(gamePlayer -> makeGamePlayersDTO(gamePlayer))
                .collect(toList()));
        return dto;
    }

    private Map<String, Object> makeGamePlayersDTO(GamePlayer gamePlayer) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", gamePlayer.getId());
        dto.put("player", makePlayersDTO(gamePlayer.getPlayer()));
        if (gamePlayer.getScore() != null) {
            dto.put("score", gamePlayer.getScore().getScore());
        }
        return dto;
    }

    private Map<String, Object> makePlayersDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", player.getId());
        dto.put("userName", player.getUserName());

        return dto;
    }


    @RequestMapping(value = "/game_view/{id}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> gameView(@PathVariable Long id,Authentication authentication) {
        GamePlayer gamePlayer = gamePlayerRepository.findOne(id);

        if (gamePlayer.getPlayer().getId() == currentPlayer(authentication).getId()) {
        Map<String, Object> gameViewMap = new HashMap<>();
        gameViewMap.put("game", makeGamesDTO(gamePlayer.getGame()));
        gameViewMap.put("ships", gamePlayer.getShip().stream().map(ship -> fillTheShipTypeDTO(ship)).collect(toList()));
        if (gamePlayer.getGame().getGamePlayers().size() == 2){
            GamePlayer enemy = getEnemyGamePlayer(gamePlayer);
            gameViewMap.put("EnemySalvos", salvoList(enemy));
        }
        gameViewMap.put("UserSalvos", salvoList(gamePlayer));
        return new ResponseEntity<>(makeMap("game-view", gameViewMap), HttpStatus.ACCEPTED);
    } else {
            return new ResponseEntity<>(makeMap("error", "This is not your game!")
                    , HttpStatus.FORBIDDEN);
        }
    }

    private Player currentPlayer (Authentication authentication){
        return playerRepository.findByUserName(authentication.getName()).get(0);
    }


    private Map<String, Object> fillTheShipTypeDTO(Ship ship) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("type", ship.getShipType());
        dto.put("location", ship.getLocations());
        return dto;
    }

    private List<Object> salvoList (GamePlayer gamePlayer) {
        List<Object> salvoList = new ArrayList<>();
        Set<Salvo> salvosSet = gamePlayer.getSalvo();
        for (Salvo salvo : salvosSet) {
            salvoList.add(fillTheSalvoTypeDTO(salvo));
        }
        return salvoList;
    }



    private Map<String, Object> fillTheSalvoTypeDTO(Salvo salvo) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("turn", salvo.getTurn());
        dto.put("player id", salvo.getGamePlayer().getPlayer().getId());
        dto.put("location", salvo.getLocations());
        return dto;
    }

    private GamePlayer getEnemyGamePlayer (GamePlayer gamePlayer) {

        List<GamePlayer> gamePlayerList = new ArrayList<>();
        Set<GamePlayer> gamePlayerSet = gamePlayer.getGame().getGamePlayers();
        for (GamePlayer gp : gamePlayerSet) {
            if (gp.id != gamePlayer.getId()) {
                gamePlayerList.add(gp);
            }
        }
        GamePlayer oponent = gamePlayerList.get(0);
        return oponent;
    }

    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createUser(@RequestParam String userName, String password) {
        if (userName.isEmpty()) {
            return new ResponseEntity<>(makeMap("error", "No name"), HttpStatus.FORBIDDEN);
        } else {
            List<Player> player = playerRepository.findByUserName(userName);
            if (!player.isEmpty()) {
                return new ResponseEntity<>(makeMap("error", "Username already exists"), HttpStatus.CONFLICT);
            } else {
                Player newPlayer = new Player(userName, password);
                playerRepository.save(newPlayer);
                return new ResponseEntity<>(makeMap("id", newPlayer.getId()), HttpStatus.CREATED);
            }
        }
    }

    private Map<String, Object> makeMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    @RequestMapping(path = "/games", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> createNewGame(Authentication authentication) {
        if(authentication == null){
            return new ResponseEntity<>(makeMap("error", "No logged in player to create game")
                    , HttpStatus.UNAUTHORIZED);
        }else{
            Game game = gameRepository.save(new Game());
            GamePlayer gamePlayer = gamePlayerRepository.save(new GamePlayer(game,currentPlayer(authentication), new Date()));

            return new ResponseEntity<>(makeMap("gamePlayerCreated", gamePlayer.getId())
                    , HttpStatus.CREATED);
        }
    }

    @RequestMapping(path = "/game/{id}/players", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> joinGame(@PathVariable Long id, Authentication authentication) {

        Game game = gameRepository.findOne(id);


        if(authentication == null){
            return new ResponseEntity<>(makeMap("error", "No logged in player to create game")
                    , HttpStatus.UNAUTHORIZED);
        }else if(game == null){
            return new ResponseEntity<>(makeMap("error", "No existing game")
                    , HttpStatus.UNAUTHORIZED);
        }
        else{
            Player player = currentPlayer(authentication);
            GamePlayer gamePlayer = gamePlayerRepository.save(new GamePlayer(game,player, new Date()));
            return new ResponseEntity<>(makeMap("gamePlayerID", makeGamePlayersDTO(gamePlayer))
                    , HttpStatus.CREATED);
        }
    }
    @RequestMapping(value="/games/players/{id}/ships", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> addShips(@PathVariable long id,
                                                        @RequestBody Set<Ship> ships,
                                                        Authentication authentication) {
        GamePlayer gamePlayer = gamePlayerRepository.findOne(id);

        if(authentication == null) {
            return new ResponseEntity<>(makeMap("error", "No logged in player to add ships")
                    , HttpStatus.UNAUTHORIZED);
        } else if (gamePlayer== null){
            return new ResponseEntity<>(makeMap("error", "The gamePlayer does not exist")
                , HttpStatus.UNAUTHORIZED);
        } else if(gamePlayer.getPlayer() != currentPlayer(authentication)){
            return new ResponseEntity<>(makeMap("error", "This is not your game (the current User is not in this gamePlayer)")
                    , HttpStatus.UNAUTHORIZED);
        } else if(gamePlayer.getShip().size() !=0) {
            return new ResponseEntity<>(makeMap("error", "Ships already has been placed")
                    , HttpStatus.FORBIDDEN);
        } else {

            for (Ship ship : ships) {
                ship.setGamePlayer(gamePlayer);
               shipRepository.save(ship);
            }
            return new ResponseEntity<>(makeMap("ships", makeGamePlayersDTO(gamePlayer))
                    , HttpStatus.CREATED);
        }
    }

    @RequestMapping(path="/games/players/{gamePlayerId}/salvos", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> placingSalvos(@PathVariable long gamePlayerId,
                                                             @RequestBody List<String> locationSalvos,
                                                             Authentication authentication){

        GamePlayer gamePlayer = gamePlayerRepository.findOne(gamePlayerId);
        if(authentication == null) {
            return new ResponseEntity<>(makeMap("error", "No logged in player to add ships")
                    , HttpStatus.UNAUTHORIZED);
            }
            else if (gamePlayer== null){
            return new ResponseEntity<>(makeMap("error", "The gamePlayer does not exist")
                    , HttpStatus.UNAUTHORIZED);
            }else if(gamePlayer.getPlayer() != currentPlayer(authentication)){
            return new ResponseEntity<>(makeMap("error", "This is not your game (the current User is not in this gamePlayer)")
                    , HttpStatus.UNAUTHORIZED);
            }else if(gamePlayer.getSalvo().size() !=0) {
            return new ResponseEntity<>(makeMap("error", "Salvo already has been placed")
                    , HttpStatus.FORBIDDEN);
            }
            else {
            Salvo salvo = new Salvo();
            salvo.setTurn(gamePlayer.getSalvo().size() + 1);
            if (salvo.getTurn() == gamePlayer.getSalvo().size() + 1 || salvo.getTurn() == 0) {
                salvo.setGamePlayer(gamePlayer);
                salvoRepository.save(salvo);

                return new ResponseEntity<>(makeMap("success", "Created and saved salvos")
                        , HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(makeMap("error", "You already shot this turn"), HttpStatus.FORBIDDEN);
            }
        }
        }
    }




