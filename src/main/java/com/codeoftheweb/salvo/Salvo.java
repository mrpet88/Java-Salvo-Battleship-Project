package com.codeoftheweb.salvo;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Salvo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private int turn;

    @JsonIgnore
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public long getId() {
        return id;
    }

    @ElementCollection
    @Column(name="Salvolocation")
    private List<String> Salvolocations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    private GamePlayer gamePlayer;

    public Salvo(){}

    public Salvo(Integer turn, GamePlayer gamePlayer, List<String> SalvoLocation){
        this.turn = turn;
        this.gamePlayer=gamePlayer;
        this.Salvolocations = SalvoLocation;
    }
    public List<String> getLocations() {
        return Salvolocations;
    }

    public void setLocations(List<String> locations) {
        this.Salvolocations = Salvolocations;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
