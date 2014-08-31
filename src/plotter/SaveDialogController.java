package plotter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Octogonapus
 */

public class SaveDialogController {
    @FXML
    private TextField fileNameTextField;
    private BetterPair2f<String, Boolean> savePackage = new BetterPair2f<>();

    @FXML
    private void saveConfirmButtonPressed(ActionEvent actionEvent) {
        savePackage.setKey(fileNameTextField.getText());
        savePackage.setValue(Boolean.TRUE);
        Stage stage = (Stage) fileNameTextField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveCancelButtonPressed(ActionEvent actionEvent) {
        savePackage.setValue(Boolean.FALSE);
        Stage stage = (Stage) fileNameTextField.getScene().getWindow();
        stage.close();
    }

    public BetterPair2f<String, Boolean> getSavePackage() {
        return savePackage;
    }
}