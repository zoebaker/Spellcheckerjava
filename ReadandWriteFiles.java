import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReadandWriteFiles {


    // read in the dictionary
    public ArrayList<String> readDictionary(String DictName) {
        BufferedReader reader;
        ArrayList<String> dict = new ArrayList<String>(97000);
        try {
            reader = new BufferedReader(new FileReader(
                    DictName));
            String line = reader.readLine();
            while (line != null) {
                dict.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dict;
    }

    public ArrayList<String> readInputFile (String fileName) {
        ArrayList<String> inputwords = new ArrayList<String>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                inputwords.add(line);
                line = reader.readLine();
                }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputwords;
        }



    public String readProperties(String propertyFileName) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFileName));
            String line = reader.readLine();
            reader.close();

            String[] data = line.split("=");
            if (data[1].equalsIgnoreCase("trie")) {
                return "trie";
            } else if (data[1].equalsIgnoreCase("tree")) {
                return "tree";
            }

        } catch (IOException e) {
            return "trie";
        }
        return "trie";
    }


    public static void writeOutput (String filename, String fileContent)
        {
            BufferedWriter writer; //write for write in the file.
            try {
                //write into this file
                writer = new BufferedWriter(new FileWriter(filename, true));

                //start next entry at new line
                writer.write(fileContent + "\n");

                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }
}