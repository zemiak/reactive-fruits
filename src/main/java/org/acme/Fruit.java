package org.acme;

import javax.persistence.Entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;

@Entity
public class Fruit extends PanacheEntity {
    public int size;
    public FruitType type;
    public double price;
}
