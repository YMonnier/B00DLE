package com.univtln.b00dle.client.model.boodle.poll;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by sbellange845 on 18/10/16.
 */
public class ResponseTest {

    private final SimpleStringProperty rep = new SimpleStringProperty("");
    private final SimpleStringProperty rep2 = new SimpleStringProperty("");

    public ResponseTest() {
        this("", "");
    }

    public ResponseTest(String rep, String rep2) {
        setRep(rep);
        setRep2(rep2);
    }

    public String getRep() {
        return rep.get();
    }

    public SimpleStringProperty repProperty() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep.set(rep);
    }

    public String getRep2() {
        return rep2.get();
    }

    public SimpleStringProperty rep2Property() {
        return rep2;
    }

    public void setRep2(String rep2) {
        this.rep2.set(rep2);
    }
}
