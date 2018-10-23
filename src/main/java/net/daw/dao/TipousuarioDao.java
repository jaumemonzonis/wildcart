package net.daw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.daw.bean.TipousuarioBean;

public class TipousuarioDao {

	Connection oConnection;
	String ob = "tipousuario";

	public TipousuarioDao(Connection oConnection) {
		super();
		this.oConnection = oConnection;
	}

	public TipousuarioBean get(int id) throws Exception {
		String strSQL = "SELECT * FROM " + ob + " WHERE id=?";
		TipousuarioBean oTipousuarioBean;
		ResultSet oResultSet = null;
		PreparedStatement oPreparedStatement = null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oPreparedStatement.setInt(1, id);
			oResultSet = oPreparedStatement.executeQuery();
			if (oResultSet.next()) {
				oTipousuarioBean = new TipousuarioBean();
				oTipousuarioBean.setId(oResultSet.getInt("id"));
				oTipousuarioBean.setDesc(oResultSet.getString("desc"));
			} else {
				oTipousuarioBean = null;
			}
		} catch (SQLException e) {
			throw new Exception("Error en Dao get de tipousuario", e);
		} finally {
			if (oResultSet != null) {
				oResultSet.close();
			}
			if (oPreparedStatement != null) {
				oPreparedStatement.close();
			}
		}
		return oTipousuarioBean;
	}

	public String remove(int id) throws Exception {
		String strRes;
		String strSQL = "DELETE FROM " + ob + " WHERE id=?";

		PreparedStatement oPreparedStatement = null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oPreparedStatement.setInt(1, id);
			oPreparedStatement.execute();
			strRes = "Registro " + id + " eliminado.";
		} catch (SQLException e) {
			throw new Exception("Error en Dao remove de tipousuario", e);
		} finally {
			if (oPreparedStatement != null) {
				oPreparedStatement.close();
			}
		}
		return strRes;
	}

	public int getcount() throws Exception {
		String strSQL = "SELECT COUNT(id) FROM " + ob;
		int res = 0;
		ResultSet oResultSet = null;
		PreparedStatement oPreparedStatement = null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oResultSet = oPreparedStatement.executeQuery();
			if (oResultSet.next()) {
				res = oResultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new Exception("Error en Dao get de tipousuario", e);
		} finally {
			if (oResultSet != null) {
				oResultSet.close();
			}
			if (oPreparedStatement != null) {
				oPreparedStatement.close();
			}
		}
		return res;
	}

	public String create(String desc) throws Exception {
		String strRes;
		String strSQL = "INSERT INTO `tipousuario` (`id`, `desc`) VALUES (NULL, ?); ";

		PreparedStatement oPreparedStatement = null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oPreparedStatement.setString(1, desc);
			oPreparedStatement.executeUpdate();
			strRes = "Registro añadido.";
		} catch (SQLException e) {
			throw new Exception("Error en Dao create de tipousuario", e);
		} finally {
			if (oPreparedStatement != null) {
				oPreparedStatement.close();
			}
		}
		return strRes;
	}

	public String update(int id, String desc) throws Exception {
		String strRes;
		String strSQL = "UPDATE `tipousuario` SET `desc` = ? WHERE `tipousuario`.`id` = ?;";

		PreparedStatement oPreparedStatement = null;
		try {
			oPreparedStatement = oConnection.prepareStatement(strSQL);
			oPreparedStatement.setString(1, desc);
			oPreparedStatement.setInt(2, id);
			oPreparedStatement.executeUpdate();
			strRes = "Registro editado.";
		} catch (SQLException e) {
			throw new Exception("Error en Dao update de tipousuario", e);
		} finally {
			if (oPreparedStatement != null) {
				oPreparedStatement.close();
			}
		}
		return strRes;
	}

}