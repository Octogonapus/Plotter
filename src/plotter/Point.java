package plotter;

/**
 * @author Octogonapus
 */

public class Point
{
    private int x, y;
    private String c;

    public Point(int x, int y, String c)
    {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public String getC()
    {
        return c;
    }

    public void setC(String c)
    {
        this.c = c;
    }

    @Override
    public String toString()
    {
        return "Point{" +
               "x=" + x +
               ", y=" + y +
               ", c='" + c + '\'' +
               '}';
    }
}
