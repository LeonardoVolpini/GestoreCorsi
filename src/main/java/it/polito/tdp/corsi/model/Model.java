package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {
	private CorsoDAO corsoDAO;
	
	public Model() {
		corsoDAO = new CorsoDAO();
	}
	
	public List<Corso> getCorsiByPeriod (Integer pd) {
		return this.corsoDAO.getCorsiByPeriod(pd);
	}
	
	public Map<Corso,Integer> getIscrittiByPeriod(Integer pd) {
		return this.corsoDAO.getIscrittiByPeriod(pd);
	}
}
