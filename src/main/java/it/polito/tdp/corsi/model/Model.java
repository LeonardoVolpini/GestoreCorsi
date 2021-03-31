package it.polito.tdp.corsi.model;

import java.util.HashMap;
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
	
	public List<Studente> getStudentiByCorso (String codins) {
		return this.corsoDAO.getStudentiByCorso(new Corso(codins,null,null,null));
	}
	
	public Map<String,Integer> getDivisioneCDS (String codins) {
		// mi aspetto una roba del genere:
		// cds1 -> 50
		// cds2 -> 88 ... List<DivisioneCDS> (potrei fare una classe apposita solo con un int e una stringa)
		
		//SOLUZIONE 1, SENZA USARE IL DATABASE
		/*Map<String,Integer> divisione= new HashMap<>();
		List<Studente> studenti= this.getStudentiByCorso(codins);
		for (Studente s : studenti ) {
			if (s.getCds()!=null ) {
				if (divisione.get(s.getCds())==null) {
					divisione.put(s.getCds(), 1);
				} else {
					divisione.put(s.getCds(), divisione.get(s.getCds())+1 );
				}
			}
		}
		return divisione;*/
		
		//SOLUZIONE 2, CON METODO NELLA CLASSE DAO    MEGLIO SOL 2	
		return this.corsoDAO.getDivisioneStudenti(new Corso(codins,null,null,null));
	}

	public boolean esisteCorso(String codins) {
		return this.corsoDAO.esisteCorso(new Corso(codins,null,null,null));
	}
	
}
