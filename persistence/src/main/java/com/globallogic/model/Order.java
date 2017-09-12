package com.globallogic.model;

import com.globallogic.model.animals.Animal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public final class Order implements Serializable {

    private static final long serialVersionUID = 2L;
    private final int id;
    private final String name;
    private final Date date;
    private final List<Animal> orderedAnimalList;
    private final String comment;
    private final BigDecimal payment;


    private Order(Builder orderBuilder) {
        this.id = orderBuilder.id;
        this.name = orderBuilder.name;
        this.date = orderBuilder.date;
        this.orderedAnimalList = orderBuilder.orderedAnimalList;
        this.comment = orderBuilder.comment;
        this.payment = orderBuilder.payment;
    }

    public static class Builder {
        private int id;
        private String name;
        private Date date;
        private List<Animal> orderedAnimalList = new ArrayList<>();
        private String comment;
        private BigDecimal payment = new BigDecimal(0);


        public Builder(int id, String name) {
            this.id = id;
            this.name = name;
        }
        public Builder date(Date date) {
            this.date = date;
            return this;
        }
        public Builder withTodayDate() {
            this.date = new Date();
            return this;
        }
        public Builder addAnimal(List<Animal> animals) {
            orderedAnimalList.addAll(animals);
            return this;
        }
        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }
        public Order build() {
            for (Animal animal:orderedAnimalList) {
               payment = payment.add(animal.getPrice());
            }
            return new Order(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public List<Animal> getOrderedAnimalList() {
        return new ArrayList<>(orderedAnimalList);
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return id + "; " + name + "; " + date + "; " + orderedAnimalList + "; " + comment + "; " + payment;
    }
}




