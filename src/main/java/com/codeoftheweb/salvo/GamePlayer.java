package com.codeoftheweb.salvo;

import javax.persistence.*;
import java.util.*;

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

    public GamePlayer() { }

    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER)
    Set<Ship> ship = new HashSet<>();

    @ElementCollection
    @Column(name="location")
    private List<String> locations = new ArrayList<>();

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

    public Set<Ship> getShip() {
        return ship;
    }

    public void setShip(Set<Ship> ship) {
        this.ship = ship;
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
