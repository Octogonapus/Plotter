package plotter;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Octogonapus
 */

public class ResizableCanvas extends Canvas
{
    public ResizableCanvas()
    {
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }

    public void draw()
    {
        double width = getWidth();
        double height = getHeight();

        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, width, height);

        if (DataManager.getPoints().size() > 0 && DataManager.getPoints().size() == 1)
        {
            Point point = DataManager.getPoint(0);
            graphicsContext.setStroke(Color.web(point.getC()));
            graphicsContext.strokeLine(point.getX(), point.getY(), point.getX(), point.getY());

            if (DataManager.getCircleIndex() != -1)
            {
                graphicsContext.setStroke(Color.BLACK);
                graphicsContext.strokeOval(DataManager.getPoint(DataManager.getCircleIndex()).getX() - 10, DataManager.getPoint(DataManager.getCircleIndex()).getY() - 10, 20, 20);
            }

        }
        else if (DataManager.getPoints().size() > 1)
        {
            for (int i = 0; i < DataManager.getPoints().size() - 1; i++)
            {
                graphicsContext.setStroke(Color.web(DataManager.getPoint(i).getC()));
                graphicsContext.strokeLine(DataManager.getPoint(i).getX(), DataManager.getPoint(i).getY(), DataManager.getPoint(i + 1).getX(), DataManager.getPoint(i + 1).getY());
            }

            if (DataManager.getCircleIndex() != -1)
            {
                graphicsContext.setStroke(Color.BLACK);
                graphicsContext.strokeOval(DataManager.getPoint(DataManager.getCircleIndex()).getX() - 10, DataManager.getPoint(DataManager.getCircleIndex()).getY() - 10, 20, 20);
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
}
