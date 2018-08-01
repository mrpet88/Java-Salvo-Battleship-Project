package com.codeoftheweb.salvo;

import javax.persistence.*;
import java.util.Date;

@Entity
    public class Score {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private double score;
        private Date finishedDate;


    public Score(){}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game")
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="player")
    private Player player;


    public Score(double score, Game game, Player player, Date finishedDate){
        this.score = score;
        this.game = game;
        this.player = player;
        this.finishedDate=finishedDate;

    }

    public long getId() {
        return id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }




}

