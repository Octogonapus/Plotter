package plotter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Octogonapus
 */

public class ResizableCanvas extends Canvas
{
    private ObservableList<Point> points = FXCollections.observableArrayList();

    public ResizableCanvas(ObservableList<Point> points)
    {
        this.points = points;

        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }

    public void draw()
    {
        double width = getWidth();
        double height = getHeight();

        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, width, height);

        if (points.size() > 0 && points.size() == 1)
        {
            Point point = points.get(0);
            graphicsContext.setStroke(Color.web(point.getC()));
            graphicsContext.strokeLine(point.getX(), point.getY(), point.getX(), point.getY());

            if (DataManager.getCircleIndex() != -1)
            {
                graphicsContext.setStroke(Color.BLACK);
                graphicsContext.strokeOval(points.get(DataManager.getCircleIndex()).getX() - 10, points.get(DataManager.getCircleIndex()).getY() - 10, 20, 20);
            }

        }
        else
        {
            for (int i = 0; i < points.size() - 1; i++)
            {
                graphicsContext.setStroke(Color.web(points.get(i).getC()));
                graphicsContext.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i + 1).getX(), points.get(i + 1).getY());
            }

            if (DataManager.getCircleIndex() != -1)
            {
                graphicsContext.setStroke(Color.BLACK);
                graphicsContext.strokeOval(points.get(DataManager.getCircleIndex()).getX() - 10, points.get(DataManager.getCircleIndex()).getY() - 10, 20, 20);
            }
        }
    }

    @Override
    public boolean isResizable()
    {
        return true;
    }

    @Override
    public double prefWidth(double height)
    {
        return getWidth();
    }

    @Override
    public double prefHeight(double width)
    {
        return getHeight();
    }

    public ObservableList<Point> getPoints()
    {
        return points;
    }

    public void setPoints(ObservableList<Point> points)
    {
        this.points = points;
    }
}
