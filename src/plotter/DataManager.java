package plotter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;

/**
 * @author Octogonapus
 */

public class DataManager
{
    private static ObservableList<Point> points = FXCollections.observableArrayList();
    private static Point currentSelection;
    private static int circleIndex;
    public static String fileName = null;

    public static void setupMainFolder() throws IOException
    {
        String startingPath = System.getProperty("user.home") + Controller.fileSeparator + "Plotter";
        DirectoryTools.makeDirectory(startingPath, "", false);
    }

    public static void saveFile()
    {
        XOMHandler.save(points, circleIndex, DataManager.fileName);
    }

    public static void saveFile(String fileName)
    {
        DataManager.fileName = fileName;
        XOMHandler.save(points, circleIndex, fileName);
        Controller.firstSave = false;
    }

    public static void openAndLoadFile(File file)
    {
        points.clear();
        BetterPair2f<ObservableList<Point>, Integer> pair = XOMHandler.loadPoints(file);
        points.addAll(pair.getKey());
        circleIndex = pair.getValue();
        DataManager.fileName = file.getName().replace(".xml", "");
    }

    public static void addPoint(Point p)
    {
        points.add(p);
    }

    public static Point getPoint(int index)
    {
        return points.get(index);
    }

    public static Point removePoint(int index)
    {
        return points.remove(index);
    }

    public static ObservableList<Point> getPoints()
    {
        return points;
    }

    public static Point getCurrentSelection()
    {
        return currentSelection;
    }

    public static void setCurrentSelection(Point currentSelection)
    {
        DataManager.currentSelection = currentSelection;
    }

    public static void setPoints(ObservableList<Point> points)
    {
        DataManager.points = points;
    }

    public static int getCircleIndex()
    {
        return circleIndex;
    }

    public static void setCircleIndex(int circleIndex)
    {
        DataManager.circleIndex = circleIndex;
    }
}
