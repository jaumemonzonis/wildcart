/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean;

import com.google.gson.annotations.Expose;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.daw.dao.UsuarioDao;
import net.daw.helper.EncodingHelper;

/**
 *
 * @author a044531896d
 */
public class FacturaBean {

    @Expose
    private int id;
    @Expose
    private Date fecha;
    @Expose
    private double iva;
    @Expose(serialize = false)
    private int id_usuario;
    @Expose(deserialize = false)
    private UsuarioBean obj_Usuario;

    public UsuarioBean getObj_Usuario() {
        return obj_Usuario;
    }

    public void setObj_Usuario(UsuarioBean obj_Usuario) {
        this.obj_Usuario = obj_Usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public FacturaBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws Exception {
        this.setId(oResultSet.getInt("id"));
        this.setFecha(oResultSet.getDate("fecha"));
        this.setIva(oResultSet.getDouble("iva"));

        if (expand > 0) {
            UsuarioDao oUsuarioDao = new UsuarioDao(oConnection, "usuario");
            this.setObj_Usuario(oUsuarioDao.get(oResultSet.getInt("id_usuario"), expand - 1));
        } else {
            this.setId_usuario(oResultSet.getInt("id_usuario"));
        }
        return this;
    }
    
   
    
public String getColumns() {
		String strColumns="";
		strColumns += "`id`,";
		strColumns += "`fecha`,";
		strColumns += "`iva`,";	
		strColumns += "`id_usuario`";		
		return strColumns;				
	}
public String getValues() {
		String strColumns="";
		strColumns += "null,";
		strColumns += EncodingHelper.quotate(null) + ",";
		strColumns += EncodingHelper.quotate(Double.toString(iva)) + ",";		
		strColumns += id_usuario;		
		return strColumns;				
	}
public String getPairs() {
		String strPairs="";
		strPairs += "id=" + id + ",";
		strPairs += "fecha=" + EncodingHelper.quotate(null) + ",";
		strPairs += "iva=" + EncodingHelper.quotate(Double.toString(iva)) + ",";
		strPairs += "id_usuario=" + id_usuario;
		return strPairs;
		
	}
	
	
}
