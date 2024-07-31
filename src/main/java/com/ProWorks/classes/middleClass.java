package com.ProWorks.classes;


import com.ProWorks.Controller;
import com.ProWorks.ControllerSuccessfulAddedMaterial;

public class middleClass {

    private final static middleClass instance = new middleClass();
    private final ControllerSuccessfulAddedMaterial instanceOfSuccessClass = new ControllerSuccessfulAddedMaterial();

    private final Controller instanceOfController = new Controller();


    public static middleClass getInstance() {
        return instance;
    }

    public ControllerSuccessfulAddedMaterial getInstanceOfGoalClass() {
        return instanceOfSuccessClass;
    }

    public Controller getInstanceOfController() {
        return instanceOfController;
    }
}