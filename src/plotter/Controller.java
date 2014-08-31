package plotter;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    public static final String fileSeparator = System.getProperty("file.separator");
    public static File mainDirectory = new File(System.getProperty("user.home") + Controller.fileSeparator + "Plotter");
    public static boolean firstSave = true;

    @FXML
    private AnchorPane ap;
    private ResizableCanvas canvas;
    private GraphicsContext graphicsContext;

    @FXML
    private TextField pointXCoordField;
    @FXML
    private TextField pointYCoordField;
    @FXML
    private TextField pointColorField;

    @FXML
    private ListView<Point> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.setProperty("java.awt.headless", "false");
        DataManager.setPoints(FXCollections.observableArrayList());
        DataManager.setCircleIndex(-1);

        canvas = new ResizableCanvas();

        ap.getChildren().add(canvas);
        canvas.widthProperty().bind(ap.widthProperty());
        canvas.heightProperty().bind(ap.heightProperty());

        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setStroke(Color.BLACK);

        listView.setItems(DataManager.getPoints());
        listView.getSelectionModel().selectedItemProperty().addListener((ov, old_val, new_val) -> {
            DataManager.setCurrentSelection(new_val);
            if (new_val != null)
            {
                //Draw circle around selected point
                DataManager.setCircleIndex(- 1);
                canvas.draw();
                DataManager.setCircleIndex(DataManager.getPoints().indexOf(new_val));
                graphicsContext.setStroke(Color.BLACK);
                graphicsContext.strokeOval(new_val.getX() - 10, new_val.getY() - 10, 20, 20);
            }
        });

        canvas.setScaleX(1);
        canvas.setScaleY(-1);
    }

    public void addPointButtonPressed(ActionEvent actionEvent)
    {
        if (pointXCoordField.getText().equals(""))
        {
            System.out.println("Point X must be specified");
        }
        else if (pointYCoordField.getText().equals(""))
        {
            System.out.println("Point Y must be specified");
        }
        else
        {
            Point point;

            try
            {
                point = new Point(Integer.parseInt(pointXCoordField.getText()), Integer.parseInt(pointYCoordField.getText()), pointColorField.getText());

                if (DataManager.getPoints().size() == 0)
                {
                    graphicsContext.setStroke(Color.web(point.getC()));
                    graphicsContext.strokeLine(point.getX(), point.getY(), point.getX(), point.getY());
                }
                else
                {
                    graphicsContext.setStroke(Color.web(point.getC()));
                    graphicsContext.strokeLine(DataManager.getPoint(DataManager.getPoints().size() - 1).getX(), DataManager.getPoint(DataManager.getPoints().size() - 1).getY(), point.getX(), point.getY());
                }

                DataManager.addPoint(point);
            }
            catch (NumberFormatException e)
            {
                System.out.println("NaN");
            }

            pointXCoordField.setText("");
            pointYCoordField.setText("");
        }
    }

    public static void setupApp()
    {
        try
        {
            DataManager.setupMainFolder();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void removeLastPointButtonPressed(ActionEvent actionEvent)
    {
        if (DataManager.getPoints().size() == 0)
        {
            System.out.println("A point must be created first");
        }
        else if (DataManager.getPoints().size() == 1)
        {
            graphicsContext.setStroke(Color.WHITE);
            graphicsContext.strokeLine(DataManager.getPoint(0).getX(), DataManager.getPoint(0).getY(), DataManager.getPoint(0).getX(), DataManager.getPoint(0).getY());
            graphicsContext.setStroke(Color.BLACK);
            DataManager.removePoint(0);
            canvas.draw();
        }
        else
        {
            graphicsContext.setStroke(Color.WHITE);
            graphicsContext.strokeLine(DataManager.getPoint(DataManager.getPoints().size() - 2).getX(), DataManager.getPoint(DataManager.getPoints().size() - 2).getY(), DataManager.getPoint(DataManager.getPoints().size() - 1).getX(), DataManager.getPoint(DataManager.getPoints().size() - 1).getY());
            graphicsContext.setStroke(Color.BLACK);
            DataManager.removePoint(DataManager.getPoints().size() - 1);
            canvas.draw();
        }
    }

    public void clearPointsButtonPressed(ActionEvent actionEvent)
    {
        DataManager.getPoints().clear();
        canvas.draw();
    }

    public void saveButtonPressed(ActionEvent actionEvent)
    {
        if (Controller.firstSave)
        {
            try
            {
                BetterPair2f<String, Boolean> savePackage = Dialogs.showSaveDialog();
                if (savePackage.getValue())
                {
                    DataManager.saveFile(savePackage.getKey());
                    Controller.firstSave = false;
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            DataManager.saveFile();
        }
    }

    public void saveAsButtonPressed(ActionEvent actionEvent)
    {
        try
        {
            BetterPair2f<String, Boolean> savePackage = Dialogs.showSaveDialog();
            if (savePackage.getValue())
            {
                DataManager.saveFile(savePackage.getKey());
                Controller.firstSave = false;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void openButtonPressed(ActionEvent actionEvent)
    {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML Resource File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml", "*.xml"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null)
        {
            DataManager.openAndLoadFile(file);
            canvas.draw();
            Controller.firstSave = false;
        }
    }
}
