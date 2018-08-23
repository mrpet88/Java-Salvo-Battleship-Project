package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;




@SpringBootApplication
public class SalvoApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
    @Bean
    public CommandLineRunner initData(PlayerRepository playerRepository,
                                      GameRepository gameRepository,
                                      GamePlayerRepository gamePlayerRepository,
                                      ShipRepository shipRepository,
                                      SalvoRepository salvoRepository,
                                      ScoresRepository scoresRepository) {
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

            Player player1 = new Player("j.bauer@ctu.gov","24");
            Player player2 = new Player("c.obrian@ctu.gov", "42");
            Player player3 = new Player("kim_bauer@gmail.com","kb");
            Player player4 = new Player("t.almeida@ctu.gov", "mole");

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

            List<String>SalvoLocation1 = Arrays.asList("B5","C5","F1");
            List<String>SalvoLocation2 = Arrays.asList("F2","D5");
            List<String>SalvoLocation3 = Arrays.asList("A2","A4","G6");
            List<String>SalvoLocation4 = Arrays.asList("A3","H6");
            List<String>SalvoLocation5 = Arrays.asList("G6","H6","A4");
            List<String>SalvoLocation6 = Arrays.asList("A2","A3","D8");
            List<String>SalvoLocation7 = Arrays.asList("A3","A4","F7");
            List<String>SalvoLocation8 = Arrays.asList("A2","G6","H6");
            List<String>SalvoLocation9 = Arrays.asList("A1","A2","A3");
            List<String>SalvoLocation10 = Arrays.asList("G6","G7","G8");
            List<String>SalvoLocation11 = Arrays.asList("B4","B5","B6");
            List<String>SalvoLocation12 = Arrays.asList("E1","H3","A2");
            List<String>SalvoLocation13 = Arrays.asList("B5","D5","C7");
            List<String>SalvoLocation14 = Arrays.asList("C5","C6");
            List<String>SalvoLocation15 = Arrays.asList("H1","H2","H3");
            List<String>SalvoLocation16 = Arrays.asList("E1","F2","G3");
            List<String>SalvoLocation17 = Arrays.asList("B5","C6","H1");
            List<String>SalvoLocation18 = Arrays.asList("C5","C7","D5");
            List<String>SalvoLocation19 = Arrays.asList("B5","B6","C7");
            List<String>SalvoLocation20 = Arrays.asList("C6","D6","E6");
            List<String>SalvoLocation21 = Arrays.asList("H1","H8");

            Salvo salvo1=new Salvo(1,gamePlayer1,SalvoLocation1);
            Salvo salvo2=new Salvo(1,gamePlayer2,SalvoLocation11);
            Salvo salvo3=new Salvo(2,gamePlayer1,SalvoLocation2);
            Salvo salvo4=new Salvo(2,gamePlayer2,SalvoLocation12);
            Salvo salvo5=new Salvo(1,gamePlayer3,SalvoLocation3);
            Salvo salvo6=new Salvo(1,gamePlayer4,SalvoLocation13);
            Salvo salvo7=new Salvo(2,gamePlayer3,SalvoLocation4);
            Salvo salvo8=new Salvo(2,gamePlayer4,SalvoLocation14);
            Salvo salvo9=new Salvo(1,gamePlayer5,SalvoLocation5);
            Salvo salvo10=new Salvo(2,gamePlayer6,SalvoLocation15);
            Salvo salvo11=new Salvo(1,gamePlayer5,SalvoLocation6);
            Salvo salvo12=new Salvo(2,gamePlayer6,SalvoLocation16);
            Salvo salvo13=new Salvo(1,gamePlayer7,SalvoLocation7);
            Salvo salvo14=new Salvo(2,gamePlayer8,SalvoLocation17);
            Salvo salvo15=new Salvo(1,gamePlayer7,SalvoLocation8);
            Salvo salvo16=new Salvo(2,gamePlayer8,SalvoLocation18);
            Salvo salvo17=new Salvo(1,gamePlayer9,SalvoLocation9);
            Salvo salvo18=new Salvo(2,gamePlayer10,SalvoLocation19);
            Salvo salvo19=new Salvo(1,gamePlayer9,SalvoLocation10);
            Salvo salvo20=new Salvo(2,gamePlayer10,SalvoLocation20);
            Salvo salvo21=new Salvo(3,gamePlayer10,SalvoLocation21);


            salvoRepository.save(salvo1);
            salvoRepository.save(salvo2);
            salvoRepository.save(salvo3);
            salvoRepository.save(salvo4);
            salvoRepository.save(salvo5);
            salvoRepository.save(salvo6);
            salvoRepository.save(salvo7);
            salvoRepository.save(salvo8);
            salvoRepository.save(salvo9);
            salvoRepository.save(salvo10);
            salvoRepository.save(salvo11);
            salvoRepository.save(salvo12);
            salvoRepository.save(salvo13);
            salvoRepository.save(salvo14);
            salvoRepository.save(salvo15);
            salvoRepository.save(salvo16);
            salvoRepository.save(salvo17);
            salvoRepository.save(salvo18);
            salvoRepository.save(salvo19);
            salvoRepository.save(salvo20);
            salvoRepository.save(salvo21);



//            Date finishedDate= new Date();

            Date finishedDate1 = Date.from(date1.toInstant().plusSeconds(1800));
            Date finishedDate2 = Date.from(date2.toInstant().plusSeconds(1800));
            Date finishedDate3 = Date.from(date3.toInstant().plusSeconds(1800));
            Date finishedDate4 = Date.from(date4.toInstant().plusSeconds(1800));
            Date finishedDate5 = Date.from(date5.toInstant().plusSeconds(1800));
            Date finishedDate6 = Date.from(date6.toInstant().plusSeconds(1800));
            Date finishedDate7 = Date.from(date7.toInstant().plusSeconds(1800));
            Date finishedDate8 = Date.from(date8.toInstant().plusSeconds(1800));



            Score scores1= new Score(1.0, game1,player1, finishedDate1);
            Score scores2= new Score(0.0, game1,player2, finishedDate1);

            Score scores3= new Score(0.5, game2,player1, finishedDate2);
            Score scores4= new Score(0.5, game2,player2, finishedDate2);

            Score scores5= new Score(0.0, game3,player2, finishedDate3);
            Score scores6= new Score(1.0, game3,player4, finishedDate3);

            Score scores7= new Score(0.5, game4,player2, finishedDate4);
            Score scores8= new Score(0.5, game4,player1, finishedDate4);

            Score scores9= new Score(0.5, game5,player4, finishedDate5);
            Score scores10= new Score(0.5, game5,player1, finishedDate5);

            Score scores11= new Score(0.5, game6,player3, finishedDate6);

            Score scores12= new Score(0.5, game6,player4, finishedDate7);

            Score scores13= new Score(0.5, game7,player3, finishedDate8);
            Score scores14= new Score(0.5, game8,player4, finishedDate8);

            scoresRepository.save(scores1);
            scoresRepository.save(scores2);
            scoresRepository.save(scores3);
            scoresRepository.save(scores4);
            scoresRepository.save(scores5);
            scoresRepository.save(scores6);
            scoresRepository.save(scores7);
            scoresRepository.save(scores8);
            scoresRepository.save(scores9);
            scoresRepository.save(scores10);
            scoresRepository.save(scores11);
            scoresRepository.save(scores12);
            scoresRepository.save(scores13);
            scoresRepository.save(scores14);

            @EnableWebSecurity
            @Configuration
            class WebSecurityConfig extends WebSecurityConfigurerAdapter {

                @Override
                protected void configure(HttpSecurity http) throws Exception {
                    http
                            .authorizeRequests()
                            .antMatchers("/web/game.html").permitAll()
                            .anyRequest().authenticated()
                            .and()
                            .formLogin()
                            .loginPage("/login")
                            .permitAll()
                            .and()
                            .logout()
                            .permitAll();
                }
            }
        };
    }
}


@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName -> {
            Player player = playerRepository.findByUserName(inputName).get(0);
            if (player != null) {
                return new User(player.getUserName(), player.getPassword(),
                        AuthorityUtils.createAuthorityList("USER"));
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }
        });
    }
}

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/games").permitAll()
                .antMatchers("/api/players").permitAll()
//                .antMatchers("/rest/**").denyAll()
//                .antMatchers("/api/login").authenticated()
                .and()
                .formLogin();

        http.formLogin()
                .usernameParameter("userName")
                .passwordParameter("password")
                .loginPage("/api/login");

        http.logout().logoutUrl("/api/logout");

        // turn off checking for CSRF tokens
        http.csrf().disable();

        // if user is not authenticated, just send an authentication failure response
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication
        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // if login fails, just send an authentication failure response
        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if logout is successful, just send a success response
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
