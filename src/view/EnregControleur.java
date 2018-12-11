package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.DBManager;
import application.FormationModel;
import application.OpModel;
import application.ListeOp;
import application.OP;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class EnregControleur {
	private final String ERROR = "Erreur !";
	private final String ERROR_SELECTED = "Erreur, aucun element selectionné";
	private final String SELERROR = "Aucun opérateur n'a été selecionnée";
	private final String ERROR_PRES = "Erreur, demandeur participant";
	private final String PRESERROR = "Ce demandeur participe déjà à la formation.";
	
	
	private ObservableList<OpModel> data = FXCollections.observableArrayList();
	private DBManager dbm;
	private ListeOp demandlist;
	private ListeOp partlist;
	
	
	//FXML----------------------------------------------------------------------------------------------------------------------------------------
	@FXML
	private TableView<OpModel> tv1;
	@FXML
	private TableView<OpModel> tv2;
	
	@FXML
	private TableColumn<OpModel,String> codeCol = new TableColumn<OpModel,String>("CODE");
	@FXML
	private TableColumn<OpModel,String> nomCol = new TableColumn<OpModel,String>("Nom");
	@FXML
	private TableColumn<OpModel,String> prenomCol = new TableColumn<OpModel,String>("Prenom");
	
	@FXML
	private TableColumn<OpModel,String> codeCol2 = new TableColumn<OpModel,String>("CODE");
	@FXML
	private TableColumn<OpModel,String> nomCol2 = new TableColumn<OpModel,String>("Nom");
	@FXML
	private TableColumn<OpModel,String> prenomCol2 = new TableColumn<OpModel,String>("Prenom");
	@FXML
	private TextField tf_codeForm;
	@FXML
	private TextField tf_libelleForm;
	@FXML
	private TextField tf_dateFin;
	@FXML
	private TextField tf_dateDebut;
	
	
	public EnregControleur() {}
	
	@FXML
	private void initialize() {
		codeCol.setCellValueFactory(new PropertyValueFactory<OpModel,String>("code"));
		nomCol.setCellValueFactory(new PropertyValueFactory<OpModel,String>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<OpModel,String>("prenom"));
		codeCol2.setCellValueFactory(new PropertyValueFactory<OpModel,String>("code"));
		nomCol2.setCellValueFactory(new PropertyValueFactory<OpModel,String>("nom"));
		prenomCol2.setCellValueFactory(new PropertyValueFactory<OpModel,String>("prenom"));
		
		
		
		
		FormationModel fm = ChoixForControleur.getSelectedCodeFrm();
		tf_codeForm.setText(fm.getCode());
		tf_libelleForm.setText(fm.getLibelle());
		tf_dateFin.setText(fm.getFin());
		tf_dateDebut.setText(fm.getDebut());
		
		try {
			dbm  = new DBManager();
			demandlist = new ListeOp();
			partlist = new ListeOp();
			ResultSet rs = dbm.selectQuery("SELECT * FROM SALARIE INNER JOIN DEMANDER ON SALARIE.Matricule = DEMANDER.Matricule WHERE CodeFormation = '" + fm.getCode() + "';");
			while(rs.next()) {
				demandlist.ajouter(new OP(rs.getString("MATRICULE"),rs.getString("NOM"),rs.getString("PRENOM")));
			}
			rs = dbm.selectQuery("SELECT * FROM SALARIE INNER JOIN SUIVRE ON SALARIE.Matricule = SUIVRE.Matricule WHERE CodeFormation = '" + fm.getCode() + "';");
			while(rs.next()) {
				partlist.ajouter(new OP(rs.getString("MATRICULE"),rs.getString("NOM"),rs.getString("PRENOM")));
			}
			dbm.closeConnection();
			tv1.setItems(demandlist.getObservableList());
			tv2.setItems(partlist.getObservableList());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/// Methodes Button ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@FXML
	private void tv1Totv2() {
		OpModel unOp = (OpModel)tv1.getSelectionModel().getSelectedItem();
		if(unOp == null) {
			alertSelect();
		}
		else {
			if(partlist.estPresent(unOp.getCode())) {
				alertPresent();
			}else {
				demandlist.select(tv1.getSelectionModel().getSelectedIndex());
				partlist.ajouter(new OP(unOp.getCode(),unOp.getNom(),unOp.getPrenom()));
				demandlist.delSelected();
				actualizeTvs();
			}
			
		}
	}
	@FXML
	private void tv2Totv1() {
		OpModel unOp = (OpModel)tv2.getSelectionModel().getSelectedItem();
		if(unOp == null) {
			alertSelect();
		}
		else {
			partlist.select(tv2.getSelectionModel().getSelectedIndex());
			if(!demandlist.estPresent(unOp.getCode())) {
				demandlist.ajouter(new OP(unOp.getCode(),unOp.getNom(),unOp.getPrenom()));
			}
			partlist.delSelected();
			actualizeTvs();
		}
	}
	
	@FXML
	private void enregistrer() {
		try {
			FormationModel fm = ChoixForControleur.getSelectedCodeFrm();
			ArrayList<String> querys = new ArrayList<String>();
			ResultSet rs = dbm.selectQuery("SELECT * FROM SALARIE INNER JOIN DEMANDER ON SALARIE.Matricule = DEMANDER.Matricule WHERE CodeFormation = '" + fm.getCode() + "';");	
			while(rs.next()) {
				if(!demandlist.estPresent(rs.getString("matricule"))) {
					querys.add("DELETE FROM DEMANDER WHERE matricule = '" + rs.getString("matricule") + "';");

					
				}
				
			}
			
			
			
			rs = dbm.selectQuery("SELECT * FROM SALARIE INNER JOIN SUIVRE ON SALARIE.Matricule = SUIVRE.Matricule WHERE CodeFormation = '" + fm.getCode() + "';");
			while(rs.next()) {
				
				if(!partlist.estPresent(rs.getString("matricule"))) {
					querys.add("DELETE FROM SUIVRE WHERE matricule = '" + rs.getString("matricule") + "';");
					
				}
			}
			
			for(int i = 0; i < querys.size();i++) {
				dbm.insertQuery(querys.get(i));
			}
			
			System.exit(0);
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void annuler() {
		
	}

	private void alertSelect() {
		Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle(ERROR);
    	alert.setHeaderText(ERROR_SELECTED);
    	alert.setContentText(SELERROR);
    	alert.showAndWait();
	}
	
	private void alertPresent() {
		Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle(ERROR);
    	alert.setHeaderText(ERROR_PRES);
    	alert.setContentText(PRESERROR);
    	alert.showAndWait();
	}
	
	private void actualizeTvs() {
		tv1.setItems(demandlist.getObservableList());
		tv2.setItems(partlist.getObservableList());
	}
	
	
}
