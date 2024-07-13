package com.workmanager.workmanager.classes;


import com.workmanager.workmanager.ControllerSuccessfulAddedMaterial;

public class middleClass {

    private final static middleClass instance = new middleClass();
    private final ControllerSuccessfulAddedMaterial instanceGoalClass = new ControllerSuccessfulAddedMaterial();


    public static middleClass getInstance() {
        return instance;
    }
    public ControllerSuccessfulAddedMaterial getInstanceOfGoalClass() {
        return instanceGoalClass;
    }
}