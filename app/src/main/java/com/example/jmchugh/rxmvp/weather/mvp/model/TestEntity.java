package com.example.jmchugh.rxmvp.weather.mvp.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.NameInDb;

/**
 * Created by jmchugh on 1/16/2018.
 */
@Entity
public class TestEntity {

    @Id
    public Long id;

    @NameInDb("VALUE")
    private String value;

    public TestEntity(String value){

        this.value = value;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {

        this.value = value;
    }
}
