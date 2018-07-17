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
                                      GameRepository gameRepository) {
        return (args) -> {

            Game game1 = new Game();
            Game game2 = new Game();
            Game game3 = new Game();

            Date date1=game1.getStartDate();
            Date date2=Date.from(date1.toInstant().plusSeconds(3600));
            Date date3=Date.from(date2.toInstant().plusSeconds(3600));

            game2.setStartDate(date2);
            game3.setStartDate(date3);


//            // save a couple of customers
            gameRepository.save(game1);
            gameRepository.save(game2);
            gameRepository.save(game3);

//            gameRepository.save(new Game());
//            gameRepository.save(new Game());
            playerRepository.save(new Player("c.obrian@ctu.gov" ));
            playerRepository.save(new Player("kim_bauer@gmail.com" ));
            playerRepository.save(new Player("t.almeida@ctu.gov"));
        };
    }
}

