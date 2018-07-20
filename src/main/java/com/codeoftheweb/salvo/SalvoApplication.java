package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
    @Bean
    public CommandLineRunner initData(PlayerRepository playerRepository,
                                      GameRepository gameRepository,
                                      GamePlayerRepository gamePlayerRepository,
                                      ShipRepository shipRepository) {
        return (args) -> {

            Ship ship1=new Ship("Carrier");
            Ship ship2=new Ship("Battleship");
            Ship ship3=new Ship("Submarine");
            Ship ship4=new Ship("Destroyer");
            Ship ship5=new Ship("Patrol Boat");

            shipRepository.save(ship1);
            shipRepository.save(ship2);
            shipRepository.save(ship3);
            shipRepository.save(ship4);
            shipRepository.save(ship5);

            Game game1 = new Game();
            Game game2 = new Game();
            Game game3 = new Game();
            Game game4 = new Game();
            Game game5 = new Game();
            Game game6 = new Game();
            Game game7 = new Game();
            Game game8 = new Game();

            Date date1=game1.getStartDate();
            Date date2=Date.from(date1.toInstant().plusSeconds(3600));
            Date date3=Date.from(date2.toInstant().plusSeconds(3600));
            Date date4=Date.from(date3.toInstant().plusSeconds(3600));
            Date date5=Date.from(date4.toInstant().plusSeconds(3600));
            Date date6=Date.from(date5.toInstant().plusSeconds(3600));
            Date date7=Date.from(date6.toInstant().plusSeconds(3600));
            Date date8=Date.from(date7.toInstant().plusSeconds(3600));

            game2.setStartDate(date2);
            game3.setStartDate(date3);
            game4.setStartDate(date4);
            game5.setStartDate(date5);
            game6.setStartDate(date6);
            game7.setStartDate(date7);
            game8.setStartDate(date8);

            gameRepository.save(game1);
            gameRepository.save(game2);
            gameRepository.save(game3);
            gameRepository.save(game4);
            gameRepository.save(game5);
            gameRepository.save(game6);
            gameRepository.save(game7);
            gameRepository.save(game8);

            Player player1 = new Player("j.bauer@ctu.gov");
            Player player2 = new Player("c.obrian@ctu.gov");
            Player player3 = new Player("kim_bauer@gmail.com");
            Player player4 = new Player("t.almeida@ctu.gov");

            playerRepository.save(player1);
            playerRepository.save(player2);
            playerRepository.save(player3);
            playerRepository.save(player4);

            GamePlayer gamePlayer1 = new GamePlayer(game1, player1,date1);
            GamePlayer gamePlayer2 = new GamePlayer(game1, player2,date1);
            GamePlayer gamePlayer3 = new GamePlayer(game2, player1,date2);
            GamePlayer gamePlayer4 = new GamePlayer(game2, player2,date2);
            GamePlayer gamePlayer5 = new GamePlayer(game3, player2,date3);
            GamePlayer gamePlayer6 = new GamePlayer(game3, player4,date3);
            GamePlayer gamePlayer7 = new GamePlayer(game4, player2,date4);
            GamePlayer gamePlayer8 = new GamePlayer(game4, player1,date4);
            GamePlayer gamePlayer9 = new GamePlayer(game5, player4,date5);
            GamePlayer gamePlayer10 = new GamePlayer(game5, player1,date5);
            GamePlayer gamePlayer11 = new GamePlayer(game6, player3,date6);
            GamePlayer gamePlayer12 = new GamePlayer(game7, player4,date7);
            GamePlayer gamePlayer13 = new GamePlayer(game8, player3,date8);
            GamePlayer gamePlayer14 = new GamePlayer(game8, player4,date8);




            gamePlayerRepository.save(gamePlayer1);
            gamePlayerRepository.save(gamePlayer2);
            gamePlayerRepository.save(gamePlayer3);
            gamePlayerRepository.save(gamePlayer4);
            gamePlayerRepository.save(gamePlayer5);
            gamePlayerRepository.save(gamePlayer6);
            gamePlayerRepository.save(gamePlayer7);
            gamePlayerRepository.save(gamePlayer8);
            gamePlayerRepository.save(gamePlayer9);
            gamePlayerRepository.save(gamePlayer10);
            gamePlayerRepository.save(gamePlayer11);
            gamePlayerRepository.save(gamePlayer12);
            gamePlayerRepository.save(gamePlayer13);
            gamePlayerRepository.save(gamePlayer14);
        };
    }
}

