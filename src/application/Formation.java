package application;

public class Formation {
	private String code, libelle, datedeb, datefin;
	
	public Formation (String code, String libelle, String datedeb, String datefin) {
		this.code= code;
		this.datedeb= datedeb;
		this.datefin= datefin;
		this.libelle= libelle;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getLibelle() {
		return libelle;
	}
	
	public String getDatedeb() {
		return datedeb;
	}
	
	public String getDatefin() {
		return datefin;
	}
	

}
