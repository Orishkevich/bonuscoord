package com.orishkevich.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * //TODO: coment
 *
 * @author orishkevich
 * @since 26.11.2017
 */
@Entity(name = "bonus")
public class Bonus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String term;
    private double amount;

    @ManyToOne
    @JoinColumn(name="coordinates_id")
    private Coordinates coord;

    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;

    public Bonus() {
    }

    public Bonus(String term, double amount, Coordinates coord, Person person) {
        this();
        this.term = term;
        this.amount = amount;
        this.coord = coord;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bonus bonus = (Bonus) o;

        return id == bonus.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
