package com.example.weatherapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "Sensor")
public class Sensor  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Should be not empty")
    @Size(min = 3,max = 30,message = "Name should be between 3 and 30 characters")
    private  String name;


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

    public Sensor() {
    }

    public Sensor(String name) {
        this.name = name;
    }
}
