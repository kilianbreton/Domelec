package application;

import javafx.beans.property.SimpleStringProperty;

public class OpModel {
  	private final SimpleStringProperty code;
	 

	private final SimpleStringProperty nom;
	 

 	private final SimpleStringProperty prenom;
	 


	    
	public OpModel(String code, String nom, String prenom) {
		super();
		this.code = new SimpleStringProperty(code);
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
	}
 	

 	public String getCode() {
		 return code.getValue();
	 }

 	public String getNom() {
		 return nom.getValue();
	 }


 	public String getPrenom() {
	      return prenom.getValue();
	 }
 

}
