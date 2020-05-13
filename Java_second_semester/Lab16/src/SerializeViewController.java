package test007;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SerializeViewController implements Initializable{
    @FXML private Button serializeButton_id = new Button();
    @FXML private Button browseButton_id = new Button();
    @FXML private Button cancelButton_id = new Button();
    @FXML private TextField nameField = new TextField();
    @FXML private TextField pathField = new TextField();
    @FXML private ChoiceBox<String> choiceBox_id = new ChoiceBox<String>();
    private File selectedDirectory;
    public void serialize(ActionEvent event) throws IOException {
		  Stage stage = new Stage();
		  Parent root = FXMLLoader.load(getClass().getResource("SerializeView.fxml"));
		  stage.setScene(new Scene(root));
		  stage.initModality(Modality.APPLICATION_MODAL);
		  stage.initOwner(((Node)event.getSource()).getScene().getWindow());
		  stage.showAndWait();
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        choiceBox_id.getItems().add("with standart protocol");
        choiceBox_id.getItems().add("with XML encoding");
        choiceBox_id.setValue("with standart protocol");
	}
    @FXML  
    private void browseButton(ActionEvent event) {
    	DirectoryChooser chooser = new DirectoryChooser();
    	chooser.setTitle("JavaFX Projects");
    	File defaultDirectory = new File("c:/");
    	chooser.setInitialDirectory(defaultDirectory);
    	selectedDirectory = chooser.showDialog((((Button)event.getSource()).getScene().getWindow()));
    	try {
    		pathField.setText(selectedDirectory.toString());
    	} catch (NullPointerException e) {

    	}
    }

    @FXML
    private void cancelButton(ActionEvent event) {
    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void serializeButton(ActionEvent event) throws IOException {
    	boolean successful_adding = true;
		if(pathField.getText().trim().isEmpty()) {
			successful_adding = false;
		}
		if(nameField.getText().trim().isEmpty()) {
			successful_adding = false;
		}
		if(successful_adding) {
			if(choiceBox_id.getValue().equals("with standart protocol")) {
				standartSaver();
			}else {
				xmlSaver();
			}
			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
		}else {
			Warning.showAlertWithHeaderText("Choose directory and name of file!");
		}
    }

	private void xmlSaver() throws FileNotFoundException {
		XMLEncoder encoder = new XMLEncoder(
				new BufferedOutputStream(
						new FileOutputStream(selectedDirectory.toString() + "\\" + nameField.getText() + ".xml")));
		encoder.writeObject(Collections.accounts);
		encoder.close(); 
	}
	private void standartSaver() throws IOException {
		FileOutputStream outputStream = new FileOutputStream(selectedDirectory.toString() + "\\" + nameField.getText() + ".ser");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
	    objectOutputStream.writeObject(Collections.accounts);
	    objectOutputStream.close();
	}
}
