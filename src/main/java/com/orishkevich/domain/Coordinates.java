package com.orishkevich.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * //TODO add comments
 * latitude and longitude
 * широта и долгота
 *
 * @author orishkevich
 * @since 26.11.2017
 */
@Entity(name = "coordinates")
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double latitude, longitude;

//    @ManyToOne
//    @JoinColumn(name="bonus_id")
//    private Bonus bonus;
//    @ManyToOne
//    @JoinColumn(name="person_id")
//    private Person person;

    public Coordinates(double latitude, double longitude/*, Person person, Bonus bonus*/) {
        this();
        this.latitude = latitude;
        this.longitude = longitude;
//        this.bonus = bonus;
//        this.person = person;
    }

//    public Coordinates(final int id) {
//        this();
//        this.id = id;
//    }
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public Coordinates() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates coord= (Coordinates) o;

        return id == coord.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
