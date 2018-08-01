package com.codeoftheweb.salvo;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long id;
    private String userName;

    public Player() { }

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<GamePlayer> gamePlayers;

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    Set<Score> scores;

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public Player(String userName) {
        this.userName = userName;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }


    public void addGamePlayer(GamePlayer gamePlayer) {
        gamePlayer.setPlayer(this);
        gamePlayers.add(gamePlayer);
    }

    public List<Game> getGames() {
            return gamePlayers.stream().map(gp -> gp.getGame()).collect(toList());
    }

    public  Score getScore (Game game){
        return scores.stream().filter(s -> s.getGame() == game).findFirst().orElse(null);
    }
}
