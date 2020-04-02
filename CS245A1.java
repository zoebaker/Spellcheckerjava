import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class CS245A1 {

    public static void main(String[] args) {

        String inputfile = args[0];

        String outputfile = "output.txt";
        String Dictname = "english.0.txt";

        File outputFile = new File(outputfile);

        //create an obj to read the new files and dictionary
        ReadandWriteFiles reader = new ReadandWriteFiles();

        //Create an arraylist of all of the words in the doc to be spell checked
        ArrayList<String> wordsToSpellCheck = reader.readInputFile(inputfile);

        //Create a dictionary array list from the jazzyfile
        ArrayList<String> dictionary = reader.readDictionary(Dictname);

        //Read the Properties file to determine tree or trie
        String storage = reader.readProperties("a1properties.txt");

        if (storage == "tree") {

            //create new tree
            BST tree = new BST();

            //insert dictionary into tree
            for (String word : dictionary){
                tree.insert(word);
            }


            //check input file for misspelled words
           tree.SpellCheckInputWords (wordsToSpellCheck);


        }else{
            Trie trie = new Trie();

            //insert dictionary into trie
            trie.insertDictIntoTrie(dictionary);

            //check input file for misspelled words
            trie.SpellCheckInputWords(wordsToSpellCheck);
        }
    }
}







