package plotter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nu.xom.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author Octogonapus
 */

public class XOMHandler
{
    public static void save(ObservableList<Point> points, int circleIndex, String fileName)
    {
        Element root = new Element("p");
        root.addAttribute(new Attribute("i", "" + circleIndex));

        for (Point p : points)
        {
            Element point = new Element("e");
            point.addAttribute(new Attribute("x", "" + p.getX()));
            point.addAttribute(new Attribute("y", "" + p.getY()));
            point.addAttribute(new Attribute("c", p.getC()));
            root.appendChild(point);
        }

        Document document = new Document(root);
        try
        {
            if (fileName != null)
            {
                Serializer serializer = new Serializer(new PrintStream(Controller.mainDirectory + Controller.fileSeparator + fileName + ".xml"), "ISO-8859-1");
                serializer.setIndent(0);
                serializer.write(document);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static BetterPair2f<ObservableList<Point>, Integer> loadPoints(File file)
    {
        Builder builder = new Builder();
        ObservableList<Point> points = FXCollections.observableArrayList();
        int circleIndex = -1;
        try
        {
            Document document = builder.build(file);
            Element root = document.getRootElement();
            circleIndex = Integer.parseInt(root.getAttributeValue("i"));
            for (int i = 0; i < root.getChildElements("e").size(); i++)
            {
                Element p = root.getChildElements("e").get(i);
                int xCoord = Integer.parseInt(p.getAttributeValue("x"));
                int yCoord = Integer.parseInt(p.getAttributeValue("y"));
                String pClass = p.getAttributeValue("c");
                points.add(new Point(xCoord, yCoord, pClass));
            }
        }
        catch (IOException | ParsingException e)
        {
            e.printStackTrace();
        }

        return new BetterPair2f<>(points, circleIndex);
    }
}
