package com.globallogic.model.animals;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;



@Entity
@Table(name="ANIMALS")
@JsonTypeInfo(use=JsonTypeInfo.Id.MINIMAL_CLASS)
public abstract class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name="NAME")
    private  String name;
    @Column(name="PRICE")
    private BigDecimal price;
    @Column(name="DATE")
    private Date estimatedBornDate;
    @Column(name="DTYPE", insertable = false, updatable = false)
    private String type;

    public Animal(String name, BigDecimal price, Date estimatedBornDate) {
        this.name = name;
        this.price = price;
        this.estimatedBornDate = estimatedBornDate;
        this.type = getClass().getSimpleName();
    }

    public Animal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Date getEstimatedBornDate() {
        return estimatedBornDate;
    }

    public void setEstimatedBornDate(Date estimatedBornDate) {
        this.estimatedBornDate = estimatedBornDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getType(){
        return type;
    }

    @Override
    public String toString() {
        return name + ", " + price + ", " + estimatedBornDate;

    }
}
