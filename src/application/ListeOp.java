package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListeOp{
	private String name;
	private final int MAX = 20;
	private OP[] elements = new OP[MAX];
	private int isel;
	private int ilibre;
	private int lastindex = 0;
	
	
	public ListeOp(String name) {
		this.name=name;
	}
	
	public void ajouter(OP unOp) {
		int cpt = 0;
		boolean end = false;
		while(cpt < MAX && !end) {
			if(elements[cpt] == null) {
				elements[cpt] = unOp;
				end = true;
			}
			cpt++;
		}
		lastindex++;
	}
	
	
	public boolean estVide() {
		int cpt = 0;
		for(int i = 0;i<MAX;i++) {
			if(elements[cpt] != null) {cpt++;}
		}
		return (cpt == 0);
	}
	
	public OP getSelected() {
		return this.elements[isel];
	}
	
	public void delSelected() {
		this.elements[isel] = null;
		lastindex--;
		
		int limit = elements.length - isel;
		for(int i = isel+1; i < limit; i++) {
			elements[i-1] = elements[i];
		}
		
	}
	
	public int getSelectedId() {
		return this.isel;
	}
	
	public void select(int i) {
		this.isel = i;
	}
	
	public int getFreeSpace() {
		return this.ilibre;
	}
	
	public void setFreeSpaceValue(int i) {
		this.ilibre = i;
	}
	
	public boolean estPresent(String code) {
		boolean ret = false;
		boolean end = false;
		int cpt = 0;
		while(!end) {
			if(cpt == lastindex) {
				end = true;
			}
			else if(code.equals(elements[cpt].getMatricule())) {
				end = true;
				ret = true;
			}
			else {
				cpt++;
			}
		}
		
		return ret;
	}
	
	
	public OP get(int i) {
		return elements[i];
	}
	
	public int count() {
		return lastindex;
	}
	
	public ObservableList<OpModel> getObservableList() {
		ObservableList<OpModel> data = FXCollections.observableArrayList();
		for(int i = 0;i < MAX;i++) {
			if(elements[i] != null) {
				data.add(new OpModel(elements[i].getMatricule(),elements[i].getNom(),elements[i].getPrenom()));
			}
		}
		return data;
	}
	
	public void updateDatabase() {
		for(int i = 0; i < lastindex; i++) {
			
		}
		
	}
	
}
