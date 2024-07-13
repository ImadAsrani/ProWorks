package com.workmanager.workmanager.db;

import java.sql.*;
import java.util.ArrayList;


public class DBManager {


    private final String url;
    private final String user;
    private final String password;


    public DBManager(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }




    public ArrayList<String> selectSQLtoArrayList(String sql){

        ArrayList<String> lista=new ArrayList<>();

        try {

            Connection conexion = DriverManager.getConnection(url, user, password);
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery( sql );


            while (resultado.next()) {
                lista.add(resultado.getString( 1 ));
            }


        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e);
        }

        return lista;

    }

    public String testConnection() throws SQLException {

        Connection conexion = DriverManager.getConnection(url, user, password);

        Statement je=conexion.createStatement();

        return je.getConnection().getMetaData().getDatabaseProductVersion();


    }

    public boolean checkTable(String name) throws SQLException {

        boolean is = false;

        Connection conexion = DriverManager.getConnection(url, user, password);

        ResultSet resultSet = conexion.getMetaData().getTables(null, null, null, new String[] {"TABLE"});

        while (resultSet.next()) {

            String tableName = resultSet.getString("TABLE_NAME");

            if(tableName.equals(name)){

                is=true;

                System.out.println("Existe");

            } else {

                is=false;

                System.out.println("No existe");

            }


        }

        return is;

    }

}