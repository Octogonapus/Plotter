package plotter;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Octogonapus
 */

public class Dialogs
{
    public static BetterPair2f<String, Boolean> showSaveDialog() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("SaveDialog.fxml"));
        Parent page = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Save");
        stage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(page);
        stage.setScene(scene);
        SaveDialogController controller = loader.getController();
        stage.showAndWait();
        return controller.getSavePackage();
    }
}
