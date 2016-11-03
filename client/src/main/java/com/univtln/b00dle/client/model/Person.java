package com.univtln.b00dle.client.model;

import javafx.beans.property.*;

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
    IntegerProperty age;
    BooleanProperty close;
    ListProperty<BooleanProperty> lb;

    public Person(String name, int age) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.close = new SimpleBooleanProperty(false);
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

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public boolean isClose() {
        return close.get();
    }

    public BooleanProperty closeProperty() {
        return close;
    }

    public void setClose(boolean close) {
        this.close.set(close);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=" + name +
                ", age=" + age +
                ", close=" + close +
                '}';
    }
}
