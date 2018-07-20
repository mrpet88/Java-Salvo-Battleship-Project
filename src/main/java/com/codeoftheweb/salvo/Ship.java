package com.codeoftheweb.salvo;

import javax.persistence.*;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String shipType;

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    public long getId() {
        return id;
    }
    public Ship(){}

    public Ship(String shipType){
        this.shipType = shipType;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }
}
