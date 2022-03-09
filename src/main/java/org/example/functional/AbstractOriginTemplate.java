package org.example.functional;

public abstract class AbstractOriginTemplate {

    public String getNumber() {

        System.out.println("getting number...");
        return "number";

    }

    public abstract void judge();

    public abstract void handle();

    public void execute() {

        getNumber();

        handle();

        judge();

    }

}
