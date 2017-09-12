package com.globallogic.model.animals;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Hamster extends Animal implements Comparable<Hamster> {

    public Hamster(String name, BigDecimal price, Date estimatedBornDate) {
        super(name, price, estimatedBornDate);
    }
    public Hamster(){
    }

    @Override
    public int compareTo(Hamster o) {
        return getPrice().compareTo(o.getPrice());
    }
}
