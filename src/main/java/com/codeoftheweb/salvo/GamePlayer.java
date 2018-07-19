package com.codeoftheweb.salvo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class GamePlayer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long id;
    private  Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Player_id")
    private Player player;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    private Game game;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="date_id")
//    private Date date;

    public GamePlayer() { }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }



    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public GamePlayer(Game game, Player player, Date date) {
        this.game = game;
        this.player = player;
        this.date = date;

    }



}
