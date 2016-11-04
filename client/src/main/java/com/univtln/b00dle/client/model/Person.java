package com.univtln.b00dle.client.model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

/**
 * Project client.
 * Package com.univtln.b00dle.client.model.
 * File Person.java.
 * Created by Ysee on 03/11/2016 - 18:24.
 * www.yseemonnier.com
 * https://github.com/YMonnier
 */
public class Person {
    StringProperty name;
    StringProperty name2;
    StringProperty name3;


    public Person(String name, String name2, String name3) {
        this.name = new SimpleStringProperty(name);
        this.name2 = new SimpleStringProperty(name2);
        this.name3 = new SimpleStringProperty(name3);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getName2() {
        return name2.get();
    }

    public StringProperty name2Property() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2.set(name2);
    }

    public String getName3() {
        return name3.get();
    }

    public StringProperty name3Property() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3.set(name3);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", name2=" + name2 +
                ", name3=" + name3 +
                '}';
    }
}
