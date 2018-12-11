package application;

public class OP {	 
    private String matricule;
    private String  nom ;
    private String prenom ;
    
    public OP(String matricule, String  nom ,  String prenom) {
   	 this.matricule = matricule;
   	 this.nom = nom;
   	 this.prenom = prenom;
    }
    
    public String getNom() {
   	 return nom;
    }
    
    public String getMatricule() {
   	 return matricule;
    }
    
    public String getPrenom() {
   	 return prenom;
    }
    
    public String makeQuery() {
    	return "UPDATE SALARIE\r\n" + 
    			"SET nom_colonne_1 = 'nouvelle valeur'\r\n" + 
    			"WHERE condition";
    }
}                                                       
