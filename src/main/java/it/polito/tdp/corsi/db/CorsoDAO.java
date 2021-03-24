package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public Map<Corso,Integer> getIscrittiByPeriod(Integer periodo) {
		String sql= "SELECT C.codins, C.crediti, C.nome, C.pd, COUNT(*) AS tot "
				+"FROM corso C, iscrizione I "
				+"WHERE C.codins=I.codins AND C.pd=? "
				+"GROUP BY C.codins, C.crediti, C.nome, C.pd "
				+"ORDER BY tot";
		Map<Corso,Integer> result= new HashMap<Corso,Integer>();
		try {
			Connection conn= DBConnect.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Corso c= new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				Integer n= rs.getInt("tot");
				result.put(c,n);
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
