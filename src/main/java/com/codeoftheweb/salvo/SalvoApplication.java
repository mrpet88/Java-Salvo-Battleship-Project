package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


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


            List<String>location1 = Arrays.asList("H2","H3","H4");
            List<String>location2 = Arrays.asList("E1","F1","G1");
            List<String>location3 = Arrays.asList("B4","B5");
            List<String>location4 = Arrays.asList("B5","C5","D5");
            List<String>location5 = Arrays.asList("F1","F2");
            List<String>location6 = Arrays.asList("C6","C7");
            List<String>location7 = Arrays.asList("A2","A3","A4");
            List<String>location8 = Arrays.asList("G6","H6");
            List<String>location9 = Arrays.asList("B5","C5","D5");

            Ship ship1=new Ship("Submarine", gamePlayer1, location2);
            Ship ship2=new Ship("Patrol Boat",gamePlayer1,location3);
            Ship ship3=new Ship("Destroyer",gamePlayer2,location4);
            Ship ship4=new Ship("Patrol Boat",gamePlayer2,location5);
            Ship ship5=new Ship("Destroyer",gamePlayer3,location4);
            Ship ship6=new Ship("Patrol Boat",gamePlayer3,location6);
            Ship ship7=new Ship("Submarine",gamePlayer4,location7);
            Ship ship8=new Ship("Patrol Boat",gamePlayer4,location8);

            Ship ship9=new Ship("Destroyer",gamePlayer5,location9);
            Ship ship10=new Ship("Patrol Boat",gamePlayer5,location6);
            Ship ship11=new Ship("Destroyer",gamePlayer6,location7);
            Ship ship12=new Ship("Patrol Boat",gamePlayer6,location8);
            Ship ship13=new Ship("Submarine",gamePlayer7,location4);
            Ship ship14=new Ship("Patrol Boat",gamePlayer7,location6);
            Ship ship15=new Ship("Destroyer",gamePlayer8,location7);
            Ship ship16=new Ship("Patrol Boat",gamePlayer8,location8);

            Ship ship17=new Ship("Submarine",gamePlayer9,location9);
            Ship ship18=new Ship("Patrol Boat",gamePlayer9,location6);
            Ship ship19=new Ship("Submarine",gamePlayer10,location7);
            Ship ship20=new Ship("Patrol Boat",gamePlayer10,location8);
            Ship ship21=new Ship("Destroyer",gamePlayer11,location4);
            Ship ship22=new Ship("Patrol Boat",gamePlayer11,location6);

            Ship ship23=new Ship("Destroyer",gamePlayer12,location4);
            Ship ship24=new Ship("Patrol Boat",gamePlayer12,location6);
            Ship ship25=new Ship("Submarine",gamePlayer13,location7);
            Ship ship26=new Ship("Patrol Boat",gamePlayer14,location8);

            shipRepository.save(ship1);
            shipRepository.save(ship2);
            shipRepository.save(ship3);
            shipRepository.save(ship4);
            shipRepository.save(ship5);
            shipRepository.save(ship6);
            shipRepository.save(ship7);
            shipRepository.save(ship8);
            shipRepository.save(ship9);
            shipRepository.save(ship10);
            shipRepository.save(ship11);
            shipRepository.save(ship12);
            shipRepository.save(ship13);
            shipRepository.save(ship14);
            shipRepository.save(ship15);
            shipRepository.save(ship16);
            shipRepository.save(ship17);
            shipRepository.save(ship18);
            shipRepository.save(ship19);
            shipRepository.save(ship20);
            shipRepository.save(ship21);
            shipRepository.save(ship22);
            shipRepository.save(ship23);
            shipRepository.save(ship24);
            shipRepository.save(ship25);
            shipRepository.save(ship26);


        };
    }
}

