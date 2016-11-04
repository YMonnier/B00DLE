package com.univtln.b00dle.client.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Created by Stéphen on 03/11/2016.
 */
public class Response {

    private int id;
    private BooleanProperty booleanProperty;

    public Response(int id) {
        this.id = id;
        this.booleanProperty = new SimpleBooleanProperty(false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBooleanProperty() {
        return booleanProperty.get();
    }

    public BooleanProperty booleanPropertyProperty() {
        return booleanProperty;
    }

    public void setBooleanProperty(boolean booleanProperty) {
        this.booleanProperty.set(booleanProperty);
    }
}
