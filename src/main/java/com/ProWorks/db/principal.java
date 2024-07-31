package com.ProWorks.db;

public class principal {
    public static void main(String[] args) {

        DBManager je=new DBManager("jdbc:mysql://g5e.h.filess.io:3307/WorkManager_rosetrack", "WorkManager_rosetrack", "imadgenio");

        System.out.println(je.selectSQLtoArrayList("select NombreMaterial from Materiales"));


    }
}
