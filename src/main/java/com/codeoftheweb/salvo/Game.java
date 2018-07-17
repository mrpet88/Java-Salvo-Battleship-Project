package com.codeoftheweb.salvo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private Date StartDate;

    public Game() {
        this.StartDate = new Date();
//        this.StartDate = Date.from(StartDate.toInstant().plusSeconds(3600));
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }
}
