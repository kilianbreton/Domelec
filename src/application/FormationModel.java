package application;


import javafx.beans.property.SimpleStringProperty;

/**
 * class permettant le remplissage du TableView
 */
public class FormationModel{
	 
  	private final SimpleStringProperty code;
	 

	private final SimpleStringProperty libelle;
	 

 	private final SimpleStringProperty debut;
	 

 	private final SimpleStringProperty fin;
	    
	    
	public FormationModel(String code, String libelle, String debut,String fin) {
		super();
		this.code = new SimpleStringProperty(code);
		this.libelle = new SimpleStringProperty(libelle);
		this.debut = new SimpleStringProperty(debut);
		this.fin = new SimpleStringProperty(fin);
	}
 	

 	public String getCode() {
		 return code.getValue();
	 }

 	public String getLibelle() {
		 return libelle.getValue();
	 }


 	public String getDebut() {
	      return debut.getValue();
	 }
 

 	public String getFin() {
		 return fin.getValue();
	 }
}