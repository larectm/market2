/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Felipe
 */
public class Conexion {
    Connection conectar = null;
    public Conexion(){
    try{Class.forName("org.postgresql.Drive");
    conectar = DriverManager.getConnection("jdbc:postgresql://localhost:5432/market","postgres","1234");}
    catch(Exception e){
        System.out.println(e.getMessage());
    }
    }
  public Connection getConnection(){
      return conectar;
  }  
  public void desconectar(){
  conectar = null ;
  }
  public static void main (String arg[])
  {
      
  }
  
}
