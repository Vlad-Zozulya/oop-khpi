package test007;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class _Main_ extends Application{
	private String themeD = getClass().getResource("dark_theme.css").toExternalForm();
    private String themeL = getClass().getResource("light_theme.css").toExternalForm();
	public static void main(String[] args) {
		launch(args);
    }
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
		
		Scene scene = new Scene(root);

		scene.getStylesheets().add(themeL);
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setTitle("Lab 10 (JavaFX)");
		stage.show();
	}
}