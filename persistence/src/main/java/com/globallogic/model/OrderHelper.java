package com.globallogic.model;

import java.io.Serializable;

public class OrderHelper implements Serializable {

    private int id;
    private String name;
    private int[] animalIds;
    private String comment;

    public OrderHelper() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getAnimalIds() {
        return animalIds;
    }

    public void setAnimalIds(int[] animalIds) {
        this.animalIds = animalIds;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
