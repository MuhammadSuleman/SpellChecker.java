/********************************************************************************
 *  Compilation:  javac SpellChecker.java
 *  Execution:    java SpellChecker 
 *
 *  Author: Muhammad Suleman 
 *  
 *********************************************************************************/

package spellchecker;

import java.io.*;
import java.util.*;

public class SpellChecker {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //loading the dictionary using the scanner 
        Scanner dictionaryScan = new Scanner(new File("wordlist.txt"));
        //creating a scanner for user input
        Scanner scanner = new Scanner(System.in);
        //variable to store the new spell checked words
        String newFile = "";
        //variable to store the word typed in
        String newWord = "";
        //variable to store the punctuation
        String other = "";
        // boolean variabel to check for valid input
        Boolean validInput = false;

        //declares and initialises a string treeset to store the words from dictionary file
        //using tree set because it automatically sorts the list into an alphabatical order
        // and also because its not case senstative
        Set<String> index = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        //asks the user to enter the file name
        System.out.println("Enter file name with file type extension:");
        //takes user input as a string
        String fileName = scanner.nextLine();
        // create i a new scanner
        Scanner fileInput;
        //reads the file to be spellchecked
        fileInput = new Scanner(new File(fileName));


        //reads through each line until there arent any left
        while (dictionaryScan.hasNextLine()) {
            //adds the current word to the treeset
            index.add(dictionaryScan.nextLine());
        }
        //this goes through the whole file
        while (fileInput.hasNext()) {
            //initilising the string word to store the current word
            String word = fileInput.next();
            //the for loop goes through each character in the word
            for (int i = 0; i < word.length(); i++) {
                //this stores each charachter
                char ch = word.charAt(i);
                
                //the if statement checks if the letter is a character
                if (Character.isLetter(ch)) {
                    //System.out.println(ch);
                    //if it is, add it to the newWord
                    newWord += ch;
                    //System.out.println(newWord);
                    
                }
                //if the letter is not a character and is e.g space, coma or question mark
                if (!Character.isLetter(ch)) {
                    //it is stored in the other variable
                    other += ch;
                }
            }
            //if the word is cotained in the treeset
            if (index.contains(newWord)) {
                // the word is added to newfile
                newFile += newWord;
                //the punctuation is added to the new file
                newFile += other;
                //adds a space, because has next misses it out
                newFile += " ";
                // new word is eptied out
                newWord = "";
                // other is emptied out
                other = "";



                //if the word is not in the dictionary
            } else {
                // it askes the user if they want to add or edit the word before adding it
                System.out.println("This word: " + newWord + " is.. not in the dictionary");
                System.out.println("If you want to add it to dictionary type in 1");
                System.out.println("If you want to edit the word type in 2");
                //the while loop check if the user input is valid
                while (!validInput) {
                    // prints out this message if its not
                    System.out.println("Please choose a correct option");
                    //and initialises the scanner again to take user input
                    String userInput = scanner.nextLine();



                    // if the user input = 2 
                    if (userInput.equals("2")) {
                        //valid input is a boolean so its set to true
                        validInput = true;
                        //prints out this
                        System.out.println("Write the new word");
                        //initialises the scanner for the user to eddit the word
                        newWord = scanner.nextLine();
                        //user inout is set to 1 automatically to go to the next if statement
                        userInput = "1";

                    }
                    //if user wants to add to dictionary
                    if (userInput.equals("1")) {
                        //valid input is set to true first
                        validInput = true;
                        //the file that is going to be edited is declared
                        File file = new File("dictionary.txt");
                        //declares the file writer which edits the file.
                        //Also sets the append boolean to false so it overwrites the previous content in file
                        FileWriter writer = new FileWriter(file, false);
                        //declares the print writer to print into the file
                        PrintWriter output = new PrintWriter(writer);
                        //the new word is added to the array 
                        index.add(newWord);

                        //declaring a new string outputline, 
                        //and using a for each loop to iterate through every element in the tree set
                        for (String outputLine : index) {
                            //the currrent word is printed into the file
                            output.println(outputLine);
                        }
                        //closes the print writer
                        output.close();
                        //and prints this out
                        System.out.println("The word is has been added to the dictionary, thank you for your contrubution");
                    }

                }
            }
        }
        // this declares the file that needs to be edited and instead of overwriting the same file everytime, 
        //adding filename, creates a new file everytime.
        File file = new File(fileName + "checkedFile.txt");
        //declares the filewriter and sets, file to false so it can be overwritten. 
        FileWriter writer = new FileWriter(file, false);
        //declares the print writer to print into the file
        PrintWriter output = new PrintWriter(writer);
        // new word is added to new file
        newFile += newWord;
        //the punctuation is added to the new file
        newFile += other;
        //adds a space, because has next misses it out
        newFile += " ";
        // new word is eptied out
        newWord = "";
        // other is emptied out
        other = "";
        // adds the current word into new file
        output.println(newFile);
        // and closes the printer
        output.close();


    }
}
