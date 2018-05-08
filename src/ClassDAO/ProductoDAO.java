/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDAO;

import ClassVo.productoVO;
import Datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public class ProductoDAO {
      public  static String insertrPro(productoVO pro)  {	
            String result = null , last = null;
            Conexion cc = new Conexion();
            Connection cn = cc.getConnection();
            PreparedStatement pst = null;
            String sql ="insert into producto values(?,?,?,?)";
            try {pst = cn.prepareStatement(sql);
                 pst.setInt(1,pro.getCodigo());
                 pst.setString(2,pro.getNom_pro());
                 pst.setInt(3,pro.getStock());
                 pst.setInt(4,pro.getPrecio());
                 pst.execute();
                 pst= cn.prepareStatement("select max(codigo) as codigo from producto");
                 ResultSet rs = pst.executeQuery();
                 if(rs.next())
                 {
                     last= rs.getString(1);
                     
                 }
                 result = "Producto registrado con exito, codigo"+last;
                 System.out.println("se inserto en la tabla producto!");
		} catch (SQLException ex) {
                    result="error en la consulta"+ex.getMessage();
         	 
		} finally {
                try{
                if (cn != null) {
                    cn.close();
                    pst.close();}
                }                           
                catch (Exception e){
                result= "error"+e;}
			
		}
            return result;
	}
      
      public  static String actuliazadorProd(productoVO pro)  {	
            String result = null , last = null;
            Conexion cc = new Conexion();
            Connection cn = cc.getConnection();
            PreparedStatement pst = null;
            String sql ="update producto set nom_pro=?,stock=?,precio=? where codigo =?";
            try {pst = cn.prepareStatement(sql);
                 pst.setString(1,pro.getNom_pro());
                 pst.setInt(2,pro.getStock());
                 pst.setInt(3,pro.getPrecio());
                 pst.setInt(4,pro.getCodigo());
                 pst.execute();
                 result = "Producto modificado con exito, codigo"+pro.getCodigo();
                 System.out.println("se modifico");
		} catch (SQLException ex) {
                    result="error en la consulta"+ex.getMessage();
         	 
		} finally {
                try{
                if (cn != null) {
                    cn.close();
                    pst.close();}
                }                           
                catch (Exception e){
                result= "error"+e;}
			
		}
            return result;
	}
      public  static productoVO buscarPro(String clave)  {	
            productoVO prov = new productoVO();
            String result = null;
            Conexion cc = new Conexion();
            Connection cn = cc.getConnection();
            PreparedStatement pst = null;
            String sql ="Select * from Producto where codigo =?";
            try {pst = cn.prepareStatement(sql);
                 pst.setString(1,clave);
                 ResultSet rs=pst.executeQuery();
                 if(rs.next())
                 {
                     prov.setCodigo(Integer.parseInt(rs.getString(1)));
                     prov.setNom_pro(rs.getString(2));
                     prov.setStock(Integer.parseInt(rs.getString(3)));
                     prov.setPrecio(Integer.parseInt(rs.getString(4)));
                 }
                 prov.setResultado("Busqueda exitosa");
                 System.out.println("busqueda weona");
		} catch (SQLException ex) {
                     prov.setResultado("Busqueda exitosa"+ex);
         	 
		} finally {
                try{
                if (cn != null) {
                    cn.close();
                    pst.close();}
                }                           
                catch (Exception e){
                result= "error"+e;}
			
		}
            return prov;
	}
          public  static String eliminarPro(String clave)  {	
            String result = null;
            Conexion cc = new Conexion();
            Connection cn = cc.getConnection();
            PreparedStatement pst = null;
            String sql ="delete from Producto where codigo =?";
            try {
                pst = cn.prepareStatement(sql);
                 pst.setString(1,clave);
                 pst.executeQuery();
                 result= "producto eliminado";
                 
                 System.out.println("se murio la weona");
		} catch (SQLException ex) {
                     result="Busqueda exitosa"+ex;
         	 
		} finally {
                try{
                if (cn != null) {
                    cn.close();
                    pst.close();}
                }                           
                catch (Exception e){
                result= "error"+e;}
			
		}
            return result;
	}
          
          public static ArrayList<productoVO>getListProductos(){
          ArrayList<productoVO> arrpro = new  ArrayList<productoVO> ();
          productoVO prov = new productoVO();
          String result = null;
          Conexion cc = new Conexion();
          Connection cn = cc.getConnection();
          PreparedStatement pst = null;
          String sql ="Select * from Producto ";
          try {pst = cn.prepareStatement(sql);
          ResultSet rs=pst.executeQuery();
          while(rs.next()){
           prov.setCodigo(Integer.parseInt(rs.getString(1)));
           prov.setNom_pro(rs.getString(2));
           if(arrpro.isEmpty())
           {arrpro.add(0,prov);}
           else{arrpro.add(prov);}
           }
            }catch (SQLException ex){System.out.println("Busqueda exitosa"+ex);}
            finally {
              try{
                    if (cn != null) {
                    cn.close();
                    pst.close();}
                }                           
                catch (Exception e){
                    System.out.println("error"+e);}
			
            }
            return arrpro;
	}
          
        
}

