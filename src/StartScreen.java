import frontend.mainMenuGUI.presenters.MainMenuPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartScreen extends Application {

    private MainMenuPresenter mainMenuPresenter = new MainMenuPresenter();
    private final String mainMenuFXMLFile = "/frontend/mainMenuGUI/fxml_files/MainMenu.fxml";

    /**
     *
     * @param primaryStage Initial stage of the JavaFX program
     * @throws Exception if stage or stage properties could not be created
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(mainMenuFXMLFile));
        primaryStage.setTitle(mainMenuPresenter.programName());
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    /**
     * Used in case the application can not be launched through deployment artifacts
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
