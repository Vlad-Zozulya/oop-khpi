package test007;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class mainPageController implements Initializable{
    @FXML private RadioButton darkMode = new RadioButton();
    
    @FXML private BorderPane pane = new BorderPane();
    @FXML private Button deleteButton = new Button();
    @FXML private Button deleteAllButton = new Button();
    @FXML private Button exportButton = new Button();
    @FXML private Button importButton = new Button();
    @FXML private Button searchButton = new Button();
    @FXML private Button generateButton = new Button();
    @FXML private Button sortButton = new Button();
    @FXML private Button addNewAccountButton = new Button();
    @FXML private Button addMobileNumberButton = new Button();
    @FXML private Button hideWindowButton = new Button();
    @FXML private Button maximizeWindowButton = new Button();
    @FXML private Button closeWindowButton = new Button();
    
    @FXML private ChoiceBox<String> sortChoiceBox = new ChoiceBox<String>();
    
    @FXML private TextField addFirstNameField = new TextField();
    @FXML private TextField addSecondNameField = new TextField();
    @FXML private TextField addBirthdayField = new TextField();
    @FXML private TextField addAddressField = new TextField();
    @FXML private TextField addMobileNumbersField = new TextField();
    @FXML private TextField searchFirstNameField = new TextField();
    @FXML private TextField searchSecondNameField = new TextField();
    @FXML private TextField searchAddressField = new TextField();
    @FXML private TextField generateField = new TextField();
    
    @FXML private CheckBox landlineCheckBox = new CheckBox();
    @FXML private CheckBox vodafoneCheckBox = new CheckBox();
    @FXML private CheckBox lifecellCheckBox = new CheckBox();
    @FXML private CheckBox kyivstarCheckBox = new CheckBox();

    @FXML private Label firstNameErrorLabel = new Label();
    @FXML private Label secondNameErrorLabel = new Label();
    @FXML private Label mobileNumberErrorLabel = new Label();
    @FXML private Label birthdayErrorLabel = new Label();
    @FXML private Label sizeLabel = new Label();
   
    @FXML private ListView<String> mobileNumbersListView;

    @FXML private TableView<Account> accountTableView;
    @FXML private TableColumn<Account, String> firstNameColumn;
    @FXML private TableColumn<Account, String> secondNameColumn;
    @FXML private TableColumn<Account, String> birthdayColumn;
    @FXML private TableColumn<Account, String> addressColumn;
    @FXML private TableColumn<Account, String> editingTimeColumn;
    @FXML private TableColumn<Account, String> mobileNumbersColumn;
    
	private ArrayList<String> mobileNumbers;
	private ObservableList<String> data = FXCollections.observableArrayList();
	private boolean set_block_1, set_block_2, set_block_3, atLeastOneNumber;
    private double x,y;
    
	private String themeD = getClass().getResource("dark_theme.css").toExternalForm();
    private String themeL = getClass().getResource("light_theme.css").toExternalForm();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeAddSection();
		initializeTable();

	}
    
    @FXML 
    private void addMobileNumberButtonOnAction(ActionEvent event) {
		if(!addMobileNumbersField.getText().trim().isEmpty()) {
			mobileNumbers.add(addMobileNumbersField.getText());
			data.add(addMobileNumbersField.getText());
			addMobileNumbersField.setText("");
			atLeastOneNumber = true; switcher();
		}
    }

    @FXML
    private void addNewAccountButtonOnAction(ActionEvent event) {
		boolean successful_adding = true;
		if(addFirstNameField.getText().trim().isEmpty()) {
			successful_adding = false;
			firstNameErrorLabel.setStyle("-fx-text-fill:red");
			firstNameErrorLabel.setText("✖   Empty field.");
			addMobileNumberButton.setDisable(true);
		}
		if(addSecondNameField.getText().trim().isEmpty()) {
			successful_adding = false;
			secondNameErrorLabel.setStyle("-fx-text-fill:red");
			secondNameErrorLabel.setText("✖   Empty field.");
			addMobileNumberButton.setDisable(true);
		}
		if(addBirthdayField.getText().trim().isEmpty()) {
			successful_adding = false;
			birthdayErrorLabel.setStyle("-fx-text-fill:red");
			birthdayErrorLabel.setText("✖   Empty field.");
			addMobileNumberButton.setDisable(true);
		}
		if(mobileNumbers.size() == 0) {
			successful_adding = false;
			mobileNumberErrorLabel.setStyle("-fx-text-fill:red");
			mobileNumberErrorLabel.setText("✖   Empty list.");
			addMobileNumberButton.setDisable(true);
		}
		if(successful_adding) {
			Account account = new Account();
			account.setName(enterName());
			account.setSurname(enterSurname());
			account.setBirthday(enterBirthday());
			account.setAddress(enterAddress());
			account.setMobileNumbers(mobileNumbers);
			account.setId(Collections.setUniqueId());
			Collections.accounts.add(account);
			Collections.accountsCollectionView.add(account);
		}
    	updateSizeLabel();
    }

    @FXML
    private void closeWindowButtonOnAction(ActionEvent event) {
    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void darkModeOnAction(ActionEvent event) {
    	Scene scene = (((RadioButton)event.getSource()).getScene());
    	if(darkMode.isSelected()) {
    		scene.getStylesheets().clear();
    		scene.getStylesheets().add(themeD);
    	} else {
    		scene.getStylesheets().clear();
    		scene.getStylesheets().add(themeL);
    	}
    }

    @FXML
    private void deleteAllButtonOnAction(ActionEvent event) {
        Collections.accounts.removeAll(Collections.accountsCollectionView);	
        Collections.accountsCollectionView.clear();
        updateSizeLabel();
    }

    @FXML
    private void deleteButtonOnAction(ActionEvent event) {
		ObservableList<Account> accountsSelected = accountTableView.getSelectionModel().getSelectedItems();
		LinkedList<Account> items =  new LinkedList<Account> (accountTableView.getSelectionModel().getSelectedItems());
        Collections.accountsCollectionView.removeAll(accountsSelected);	
        accountTableView.getSelectionModel().clearSelection();
        Collections.accounts.removeAll(items);
        updateSizeLabel();
    }

    @FXML
    private void exportButtonOnAction(ActionEvent event) throws IOException {
		SerializeViewController s = new SerializeViewController();
		s.serialize(event);
		updateSizeLabel();
    }

    @FXML
    private void generateButtonOnAction(ActionEvent event) {
    	try {
    		int size = Integer.parseInt(generateField.getText());
    		Generator._gen_add(size);
    	} catch (Exception e) {

    	}
    	updateSizeLabel();
    }

    @FXML
    private void hideWindowButtonOnAction(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	stage.setIconified(true);
    }

    @FXML
    private void importButtonOnAction(ActionEvent event) throws ClassNotFoundException, IOException {
    	Deserialize d = new Deserialize();
    	d.deserialize(event);
    	updateSizeLabel();
    }

    @FXML
    private void maximizeWindowButtonOnAction(ActionEvent event) {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	stage.setFullScreenExitHint("  ");
    	stage.setFullScreen(true);
    }

    @FXML
    private void searchButtonOnAction(ActionEvent event) {
    	Collections.update();
    	Search s = new Search();
    	s.search(landlineCheckBox.isSelected(), vodafoneCheckBox.isSelected(), 
    			lifecellCheckBox.isSelected(), kyivstarCheckBox.isSelected(),
    			searchFirstNameField, searchSecondNameField, searchAddressField);
    	updateSizeLabel();
    }

    @FXML
    private void sortButtonOnAction(ActionEvent event) {
    	Sort.sortChoice(sortChoiceBox.getValue());
    }

    @FXML
    private void pressed(MouseEvent event) {
    	x = event.getSceneX();
    	y = event.getSceneY();

    }
    
    @FXML
    private void dragged(MouseEvent event) {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	stage.setX(event.getScreenX() - x);
    	stage.setY(event.getScreenY() - y);
    }
    
    
	public void updateSizeLabel() {
//		sizeLabel.setStyle("-fx-text-fill:black");
		sizeLabel.setText("[" + Collections.accountsCollectionView.size() + "/" + Collections.accounts.size() + "]");
	}
	public void switcher() {
		if(!set_block_1 && !set_block_2 && !set_block_3 && atLeastOneNumber) {
			addNewAccountButton.setDisable(false);
		} else {
			addNewAccountButton.setDisable(true);
		}
	}
	public Date enterBirthday() {
		Date theDate = null;
		try {
			String birthday = addBirthdayField.getText();
			theDate = new SimpleDateFormat("ddMMyyyy").parse(birthday.replaceAll("/", ""));
		} catch (ParseException e) {
			System.out.println(e);
		}
		return theDate;
	}
	public String enterAddress() {
		return addAddressField.getText();
	}
	public String enterName() {
		return addFirstNameField.getText();
	}
	public String enterSurname() {
		return addSecondNameField.getText();
	}
	private void initializeAddSection() {
		data = mobileNumbersListView.getItems();
		mobileNumbers = new ArrayList<String>();
		addFirstNameField.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				if(CheckWithRegex.check_name_surname(addFirstNameField.getText())) {
					firstNameErrorLabel.setStyle("-fx-text-fill:green");
					firstNameErrorLabel.setText("✔");
					set_block_1 = false;  switcher();
				} else {              
					firstNameErrorLabel.setStyle("-fx-text-fill:red");
					firstNameErrorLabel.setText("✖   Invalid Name. Try again.");
					set_block_1 = true;	switcher();
				}
			}      
		});
		addSecondNameField.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				if(CheckWithRegex.check_name_surname(addSecondNameField.getText())){    
					secondNameErrorLabel.setStyle("-fx-text-fill:green");
					secondNameErrorLabel.setText("✔");
					set_block_2 = false; switcher();
				}else{              
					secondNameErrorLabel.setStyle("-fx-text-fill:red");
					secondNameErrorLabel.setText("✖   Invalid Surname. Try again.");
					set_block_2 = true; switcher();
				}
			}      
		});
		addBirthdayField.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				if(CheckWithRegex.check_birthday(addBirthdayField.getText())){  
					birthdayErrorLabel.setStyle("-fx-text-fill:green");
					birthdayErrorLabel.setText("✔");
					set_block_3 = false; switcher();
				}else{              
					birthdayErrorLabel.setStyle("-fx-text-fill:red");
					birthdayErrorLabel.setText("✖   Invalid Birthday. Try again.");		
					set_block_3 = true; switcher();
				}
			}      
		});
		addMobileNumbersField.setOnKeyReleased(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				if(CheckWithRegex.check_phone_number(addMobileNumbersField.getText())){  
					mobileNumberErrorLabel.setStyle("-fx-text-fill:green");
					mobileNumberErrorLabel.setText("✔");
					addMobileNumberButton.setDisable(false);
				}else{              
					mobileNumberErrorLabel.setStyle("-fx-text-fill:red");
					mobileNumberErrorLabel.setText("✖   Invalid mobile phone. Try again.");
					addMobileNumberButton.setDisable(true);
				}
			}      
		});
	}
	private void initializeTable() {
		Collections.accountsCollectionView.addAll(Collections.accounts);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("name"));
		secondNameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("surname"));
		birthdayColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("birthday_string"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("address"));
		editingTimeColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("editingTime_string"));
		mobileNumbersColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("mobileNumbers_string"));
		accountTableView.setItems(Collections.accountsCollectionView);
		
		firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		firstNameColumn.setOnEditCommit(e->{
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue());
		});
		secondNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		secondNameColumn.setOnEditCommit(e->{
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setSurname(e.getNewValue());
		});
		addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		addressColumn.setOnEditCommit(e->{
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setAddress(e.getNewValue());
		});
		
		accountTableView.setEditable(true);
		accountTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		sortChoiceBox.getItems().add("name");
		sortChoiceBox.getItems().add("surname");
		sortChoiceBox.getItems().add("birthday");
		sortChoiceBox.getItems().add("edit. time");
		sortChoiceBox.setValue("");
		updateSizeLabel();
	}
}
