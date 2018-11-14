package net.daw.bean;

import java.sql.Connection;
import java.sql.ResultSet;

import com.google.gson.annotations.Expose;
import net.daw.helper.EncodingHelper;

public class TipousuarioBean {
	@Expose
	private int id;
	@Expose
	private String desc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public TipousuarioBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws Exception {
		this.setId(oResultSet.getInt("id"));
		this.setDesc(oResultSet.getString("desc"));
		return this;
	}
        public String getColumns() {
		String strColumns="";
		strColumns += "`id`,";		
		strColumns += "`desc`";		
		return strColumns;				
	}
        public String getValues() {
		String strColumns="";
		strColumns += "null,";
		strColumns += EncodingHelper.quotate(desc);		
		return strColumns;				
	}
        
        public String getPairs() {
		String strPairs="";
		strPairs += "id=" + id + ",";
		strPairs += "desc=" + desc;
		return strPairs;	
	}
}
