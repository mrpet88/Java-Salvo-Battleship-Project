package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RequestMapping("/api")
@RestController
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @RequestMapping("/games")
    public List<Object> getGamesIds() {
        return gameRepository
                .findAll()
                .stream()
                .map(game -> makeGamesDTO(game))
                .collect(toList());

//            return repo.findAll().stream().map(b->b.getId()).collect(toList());
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

        return dto;
    }

    private Map<String, Object> makePlayersDTO(Player player) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", player.getId());
        dto.put("userName", player.getUserName());

        return dto;
    }

    @RequestMapping(value = "/game_view/{id}", method = RequestMethod.GET)
    public Map<String, Object> gameView(@PathVariable Long id) {
        GamePlayer gamePlayer = gamePlayerRepository.findOne(id);
        Map<String, Object> gameViewMap = new HashMap<>();
        gameViewMap.put("game", makeGamesDTO(gamePlayer.getGame()));
        gameViewMap.put("ships",gamePlayer.getShip().stream().map(ship -> fillTheShipTypeDTO(ship)).collect(toList()));

        return gameViewMap;
    }

//    private Map<String, Object> makeGameViewDTO(GamePlayer gamePlayer) {
//        Map<String, Object> dto = new LinkedHashMap<String, Object>();
//        dto.put("ships", gamePlayer.getShip());
//        return dto;
//    }

    private Map<String, Object> fillTheShipTypeDTO(Ship ship) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("type", ship.getShipType());
        dto.put("location", ship.getLocations());
        return dto;
    }//    private Map<String, Object> makeGamesViewDTO(GamePlayer gamePlayer) {
//        Map<String, Object> dto = new LinkedHashMap<String, Object>();
//            dto.put("id", gamePlayer.getId());
//            dto.put("created", gamePlayer.getGame().getStartDate());
//            dto.put("gamePlayers", gamePlayer.getGame().getGamePlayers()
//                    .stream()
//                    .map(singlePlayer -> makeGamePlayersViewDTO(singlePlayer))
//                    .collect(Collectors.toList()));
//            return dto;
//    }
//    private Map<String, Object> makeGamePlayersViewDTO(GamePlayer gamePlayer) {
//        Map<String, Object> dto = new LinkedHashMap<String, Object>();
//        dto.put("id", gamePlayer.getId());
//        dto.put("player", makePlayersViewDTO(gamePlayer.getPlayer()));
//
//        return dto;
//    }
//    private Map<String, Object>makePlayersViewDTO(Player player) {
//        Map<String, Object> dto = new LinkedHashMap<String, Object>();
//        dto.put("id", player.getId());
//        dto.put("userName", player.getUserName());
//
//        return dto;
//    }
    }





