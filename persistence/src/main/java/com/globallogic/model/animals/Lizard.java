package com.globallogic.model.animals;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Lizard extends Animal implements Comparable<Lizard> {

    public Lizard(String name, BigDecimal price, Date estimatedBornDate) {
        super(name, price, estimatedBornDate);
    }

    public Lizard() {
    }

    @Override
    public int compareTo(Lizard o) {
        return getEstimatedBornDate().compareTo(o.getEstimatedBornDate());
    }
}
