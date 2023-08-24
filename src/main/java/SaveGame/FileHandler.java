package SaveGame;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class FileHandler {
    private static void createFile(Path path){
        if (!Files.exists(path)){
            try {
                Files.createFile(path); // If the file does not exit create a new one
            } catch (IOException e){
                System.out.println("Can not create the file");
            }
        }
    }

    public static void writeOver (String pathStr, String message){
        try {
            Path path = Paths.get(pathStr);
            createFile(path);
            Files.writeString(path, message, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e){
            System.out.println("Can not write the file");
        }
    }
    public static void emptyFile(String pathStr){
        try {
            Path path = Paths.get(pathStr);
            createFile(path);
            Files.writeString(path, "", StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e){
            System.out.println("Can not write the file");
        }
    }
    public static boolean isEmptyFile(String path) {
        return readFile(path).equals("");
    }


//https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
    public static String readFile(String path) {
        File file = new File(path);
        String toReturn = "";
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                toReturn += sc.nextLine() + "\n";
            }
        } catch (IOException e){
            System.out.println("Can not read file!");
        }
        return toReturn;
    }
}
