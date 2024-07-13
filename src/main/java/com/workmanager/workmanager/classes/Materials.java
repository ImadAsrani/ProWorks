package com.workmanager.workmanager.classes;

import javafx.scene.paint.Color;

public class Materials {

    private int ID;
    private String name;
    private int hardness;
    private Color color;

    public Materials(int ID, String name, int hardness, Color color) {
        this.ID = ID;
        this.name = name;
        this.hardness = hardness;
        this.color = color;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHardness() {
        return hardness;
    }

    public void setHardness(int hardness) {
        this.hardness = hardness;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ID: " + this.ID + " name " + this.name + " hardness " + this.hardness + " color: " + this.color;
    }
}
