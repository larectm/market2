/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import Datos.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class producto {
    public int codigo, stock, precio;
    public String nom_pro;
    /*
    
    */
    Conexion conex =new Conexion();
    
    private static Connection canal=null;
    static Statement  s=null;
    static ResultSet  resul=null;
    final String usuario = "postgres";
    final String clave = "1234";

    public producto(int codigo, int stock, int precio, String nom_pro) {
        this.codigo = codigo;
        this.stock = stock;
        this.precio = precio;
        this.nom_pro = nom_pro;
    }
    public producto ()
    {}
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNom_pro() {
        return nom_pro;
    }

    public void setNom_pro(String nom_pro) {
        this.nom_pro = nom_pro;
    }
    
    private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
      }
    
   
     public  void insertrPro(int codigo,String nom_pro,int stock,int precio) throws SQLException {		
               PreparedStatement prs = null; 
		try {   String sql ="insert into producto (codigo,nom_pro,stock,precio)values("+codigo+",'"+nom_pro + "',"+stock+","+precio+")";
			canal = conex.abrirConeccionBd(usuario, clave);
                        
			prs = canal.prepareStatement(sql);
			prs.setInt(1, codigo);
			prs.setString(2, nom_pro);
			prs.setInt(3,stock );
			prs.setInt(4,precio );
			// execute insert SQL stetement
			prs.executeUpdate();
			System.out.println("se inserto en la tabla producto!");
		} catch (SQLException ex) {
         	  Logger lgr = Logger.getLogger(producto.class.getName());
                 lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			if (prs != null) {
				prs.close();}
                                if (canal != null) {
				canal.close();
                          }
		}
	}
   
   public int eliminarPro(int codigo)
     {
         String SQL = "DELETE FROM producto WHERE id_Activo="+codigo+"";;
 
        int affectedrows = 0;
 
        try (Connection canal=conex.abrirConeccionBd(usuario, clave);
                PreparedStatement pstmt = canal.prepareStatement(SQL)) {
 
            pstmt.setInt(1, codigo);
 
            affectedrows = pstmt.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return affectedrows;
    }
         
     public static void main(String[] args) throws SQLException {
         
         producto pr = new producto ();
        
         pr.insertrPro(2, "pera", 23,65);
       }
    }
