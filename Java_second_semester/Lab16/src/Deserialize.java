package test007;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class Deserialize {
	private File selectedFile;
	private boolean isCancel = false;
	
	public void deserialize(ActionEvent event) throws IOException, ClassNotFoundException {
		FileChooser fileChooser = new FileChooser();
		File defaultDirectory = new File("c:/");
		fileChooser.setInitialDirectory(defaultDirectory);
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("XML", "*.xml*"), 
				new FileChooser.ExtensionFilter("SERIALIZABLE", "*.ser"));
		showConfirmation(event);

		if(!isCancel) {
			selectedFile = fileChooser.showOpenDialog((((Button)event.getSource()).getScene().getWindow()));
			if (selectedFile != null && CheckWithRegex.check_file_extension(selectedFile.getName())) {
				if(CheckWithRegex.isXml(selectedFile.getName())) 
					xmlLoader();
				else 
					standartLoader();
				Collections.accountsCollectionView.addAll(Collections.accounts);
			} else {
				Warning.showAlertWithHeaderText("Choose .xml/.ser file to open!");
			}
		}
	}

	private void xmlLoader() throws FileNotFoundException {
		XMLDecoder decoder = new XMLDecoder(
				new BufferedInputStream(
						new FileInputStream(selectedFile.getAbsolutePath())));
		Collections.accounts =  (LinkedList<Account>) decoder.readObject();
		decoder.close();
	}
	private void standartLoader() throws IOException, ClassNotFoundException {
		FileInputStream fileInputStream = new FileInputStream(selectedFile.getAbsolutePath());
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		Collections.accounts = (LinkedList<Account>) objectInputStream.readObject();
	}
	private void showConfirmation(ActionEvent event) throws IOException {
		Optional<ButtonType> option;
		ButtonType save = new ButtonType("Save");
		ButtonType dontSave = new ButtonType("Don't save");
		ButtonType cancel = new ButtonType("Cancel");
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Select");
		alert.setHeaderText("Do you want to save current table? It would be lost after importing.");
		alert.getButtonTypes().clear(); 
		alert.getButtonTypes().addAll(save, dontSave, cancel);
		option = alert.showAndWait();
		if (option.get() == save) {
			SerializeViewController s = new SerializeViewController();
			s.serialize(event);
		} else if (option.get() == dontSave) {
			
		} else if(option.get() == cancel) {
			isCancel = true;
			return;
		}
		Collections.accounts.clear();
		Collections.accountsCollectionView.clear();
	}
}
