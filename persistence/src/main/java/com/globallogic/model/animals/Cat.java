package com.globallogic.model.animals;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Cat extends Animal implements Comparable<Cat> {

    public Cat(String name, BigDecimal price, Date estimatedBornDate) {
        super(name, price, estimatedBornDate);
    }

    public Cat() {
    }

    @Override
    public int compareTo(Cat o) {
        return o.getEstimatedBornDate().compareTo(getEstimatedBornDate());
    }
}

