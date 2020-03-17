package com.example.olympictrials;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "athlete", schema = "db_athlete") //do I need a catalog here.
public class AthleteEntity {
    private long id;
    private String firstName;
    private String lastName;
    private String sport;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "sport")
    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AthleteEntity that = (AthleteEntity) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(sport, that.sport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, sport);
    }
}
