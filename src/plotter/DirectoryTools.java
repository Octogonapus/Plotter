package plotter;

import java.io.File;

/**
 * @author Octogonapus
 */

public class DirectoryTools {
    public static void makeDirectory(String rawPath, String name, boolean addName) {
        File directory;
        String path;
        if (addName)
            path = rawPath + Controller.fileSeparator + name;
        else
            path = rawPath;
        directory = new File(path);
        if (directory.mkdir())
            System.out.println("Directory Created");
        else
            System.out.println("Directory Not Created");
    }
}