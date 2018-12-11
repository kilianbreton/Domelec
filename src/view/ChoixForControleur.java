package view;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.DBManager;
import application.FormationModel;
import application.Exceptions.DbmIOException;
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

public class ChoixForControleur {
	//Mod�les
	private ObservableList<FormationModel> data = FXCollections.observableArrayList();
	private DBManager dbm;
	private String codeSelected;
	private static FormationModel selectedFrm;
	//Constantes d'erreur :
	  //Erreur SelectedElem
	private final String ERROR = "Erreur !";
	private final String ERROR_SELECTED = "Erreur, aucun element selectionné";
	private final String SELERROR = "Aucune formation n'a été selecionnée";
      //Erreur SelectedElem
	private final String ERROR_IO = "Erreur, base de donn�es introuvable";
	
	//Composants FXML
	@FXML
	private TableView<FormationModel> tv;
	@FXML
	private TableColumn<FormationModel,String> codeCol = new TableColumn<FormationModel,String>("CODE");
	@FXML
	private TableColumn<FormationModel,String> libelleCol = new TableColumn<FormationModel,String>("Libelle");
	@FXML
	private TableColumn<FormationModel,String> debutCol = new TableColumn<FormationModel,String>("Date debut");
	@FXML
	private TableColumn<FormationModel,String> finCol = new TableColumn<FormationModel,String>("Date fin");
	
	
	public ChoixForControleur() {}
	
	
	@FXML
	private void initialize() {
		codeCol.setCellValueFactory(new PropertyValueFactory<FormationModel,String>("code"));
		libelleCol.setCellValueFactory(new PropertyValueFactory<FormationModel,String>("libelle"));
		debutCol.setCellValueFactory(new PropertyValueFactory<FormationModel,String>("debut"));
		finCol.setCellValueFactory(new PropertyValueFactory<FormationModel,String>("fin"));

		
		
		try {
			data = FXCollections.observableArrayList();
			dbm = new DBManager();
			ResultSet rs = dbm.selectQuery("SELECT * FROM FORMATION");
			while(rs.next()) {
				data.add(new FormationModel(rs.getString("CODEFORMATION"),rs.getString("LIBELLEFORMATION"),rs.getString("DATEDEBUTFORMATION"),rs.getString("DATEFINFORMATION")));
			}
			tv.setItems(data);
			dbm.closeConnection();
		} catch (DbmIOException e) {
			Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle(ERROR);
	    	alert.setHeaderText(ERROR_IO);
	    	alert.setContentText(e.getBadPath());
	    	alert.showAndWait();
	    	System.exit(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
	
	@FXML
	private void quitter(){
		System.exit(0);
	}
	
	@FXML
	private void suite() {
		selectedFrm = (FormationModel)tv.getSelectionModel().getSelectedItem();
		if(selectedFrm != null) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/view/Enreg.fxml"));
				Stage primaryStage = new Stage();
			    primaryStage.setScene(new Scene(root));
			    primaryStage.setTitle("Choix de formations");
			    primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
	    	alert.setTitle(ERROR);
	    	alert.setHeaderText(ERROR_SELECTED);
	    	alert.setContentText(SELERROR);
	    	alert.showAndWait();
		}
		
	}
	
	
	
	
	

	public static FormationModel getSelectedCodeFrm() {
		return selectedFrm;
	}

	public static void setSelectedCodeFrm(FormationModel selectedCodeFrm) {
		ChoixForControleur.selectedFrm = selectedCodeFrm;
	}
}
