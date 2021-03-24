package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {

	public List<Corso> getCorsiByPeriod(Integer periodo) {
		String sql= "SELECT * "
				+"FROM corso "
				+"WHERE pd=?";
		List<Corso> result= new ArrayList<Corso>();
		try {
			Connection conn= DBConnect.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Corso c= new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				result.add(c);
			}
			rs.close();
			st.close();
			conn.close(); //unica fondamentale, st e rs close sono superflui
		} catch(SQLException e) {
			throw new RuntimeException("Errore nel leggere il database",e);
		}
		return result;
	}
	
	
}
