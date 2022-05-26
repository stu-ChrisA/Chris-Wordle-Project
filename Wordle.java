/*
 * File: Wordle.java
 * -----------------
 * This module is the starter file for the Wordle assignment.
 * BE SURE TO UPDATE THIS COMMENT WHEN YOU COMPLETE THE CODE.
 */

import edu.willamette.cs1.wordle.WordleDictionary;
import edu.willamette.cs1.wordle.WordleGWindow;
import java.util.ArrayList;

public class Wordle {
    private final double FINAL_S_FRACTION = 1.0/3.0;
    private int randomIndex = (int) (Math.random() * WordleDictionary.FIVE_LETTER_WORDS.length);
    private String answerToWordle = WordleDictionary.FIVE_LETTER_WORDS[randomIndex].toUpperCase();
    String test = "DROWN"; //tester code
    
    public void run() {
        gw = new WordleGWindow();
        gw.addEnterListener((s) -> enterAction(s));
    }

/*
 * Called when the user hits the RETURN key or clicks the ENTER button,
 * passing in the string of characters on the current row.
 */

    public void enterAction(String s) { // Checks if the word is in the dictonary, then colors the square boxes/key caps to it's correct color for the guess and then displayed a "you win" message if the user guesses the word correctly
        String userWord = s;
        //String [] x = new String[5]; change to arrayList
        userWord = userWord.toLowerCase();
        boolean check = false;
        for(String checkWord : WordleDictionary.FIVE_LETTER_WORDS)
        {
            if(userWord.equals(checkWord))
            {
                check = true;
            }
        }
        if(!check)
        {
            gw.showMessage("not in word list. Try again.");
        }else{
            gw.showMessage("in word list.");
        }


        for(int col = 0; col < WordleGWindow.N_COLS; col++)
        {
            if(gw.getSquareLetter(gw.getCurrentRow(), col).equals(test.substring(col,col+1)))   
            {
                //x.add();
                //test = test.replace(gw.getSquareLetter(gw.getCurrentRow(), col), "");
                gw.setSquareColor(gw.getCurrentRow(), col,  WordleGWindow.CORRECT_COLOR);
                gw.setKeyColor(gw.getSquareLetter(gw.getCurrentRow(), col), WordleGWindow.CORRECT_COLOR);
            }
            else if((test).indexOf(gw.getSquareLetter(gw.getCurrentRow(), col))!=-1)
            {
                gw.setSquareColor(gw.getCurrentRow(), col,  WordleGWindow.PRESENT_COLOR);
                gw.setKeyColor(gw.getSquareLetter(gw.getCurrentRow(), col), WordleGWindow.PRESENT_COLOR);
            }
            else
            {
                gw.setSquareColor(gw.getCurrentRow(), col,  WordleGWindow.MISSING_COLOR);
                gw.setKeyColor(gw.getSquareLetter(gw.getCurrentRow(), col), WordleGWindow.MISSING_COLOR);
            }

        }

        if(userWord.equalsIgnoreCase(test))
        {
            rainbowEffect();
            gw.showMessage("You got the word in " + guesses + " guess.");
            if(guesses > 1)
            {
                gw.showMessage("You got the word in " + guesses + " guesses.");
            }
            WordleGuessesPerGainUpdate.updateGuessesEachGame();
            displayGuessesPerGame();
            gw.showMessage("These are scores for each time you guessed");
        }
        else{
            updateGuess();
            updateRow();
        }






    }

/* Startup code */

    public static void main(String[] args) {
        new Wordle().run();
    }
    public boolean testS() //Make the dictonary more balanced by only choosing words ending with "s" 1/3 of the time
    {
        double d = Math.random();
        boolean result = false;

        if (d <= FINAL_S_FRACTION)
        {
            if(answerToWordle.substring(4).equals("s"))
            {
                result = true;
            }
        }
        else
        {
            if(!answerToWordle.substring(4).equals("s"))
            {
                result = true;
            }

        }
        return result;
    }

    public void checkWordleAnswer() //Updates the word with the boolean check from testS method
    {
        if(testS() == true)
        {
            randomIndex = (int) (Math.random() * WordleDictionary.FIVE_LETTER_WORDS.length);
            answerToWordle = WordleDictionary.FIVE_LETTER_WORDS[randomIndex].toUpperCase();
        }
    }
    public void RandomWord() //takes the answertowordle word and sets each letter to a square on the wordle screen
    {
        int letter = 0;
        for(int c = 0; c < WordleGWindow.N_COLS; c++)
        {
            gw.setSquareLetter(0,c,answerToWordle.substring(letter,letter+1));
            letter++;
        }
    
    }
    static int guesses = 1;
    int rowIndex = 1;
    public void updateGuess()
    {
        if (guesses <= WordleGWindow.N_ROWS)
        {
            guesses++;
        }
    }
    public void updateRow(){ //updates the row user types on after they inputted a word on the row before and increases guesses
        
        if(rowIndex <= WordleGWindow.N_ROWS)
        {
            gw.setCurrentRow(rowIndex);
            rowIndex++;
        }
    }

    public void rainbowEffect() //Nice graphic design for the user if they guess the word correctly
    {
        gw.setSquareColor(gw.getCurrentRow(), 0, WordleGWindow.RAINBOW_COLOR_RED);
        gw.setSquareColor(gw.getCurrentRow(), 1, WordleGWindow.RAINBOW_COLOR_ORANGE);
        gw.setSquareColor(gw.getCurrentRow(), 2, WordleGWindow.RAINBOW_COLOR_YELLOW);
        gw.setSquareColor(gw.getCurrentRow(), 3, WordleGWindow.RAINBOW_COLOR_GREEN);
        gw.setSquareColor(gw.getCurrentRow(), 4, WordleGWindow.RAINBOW_COLOR_PURPLE);
    }

    public void displayGuessesPerGame()
    {
        for(int row = 0; row < WordleGWindow.N_ROWS; row++)
        {
            for(int col2 = 0; col2 < WordleGWindow.N_COLS; col2++)
            {
                gw.setSquareColor(row, col2, WordleGWindow.WHITE_COLOR);
                gw.setSquareLetter(row, col2, ""); 
            }
        }

        /*gw.setSquareColor(0, 0, WordleGWindow.CORRECT_COLOR);
        gw.setSquareLetter(0, 0, "1");

        gw.setSquareColor(1, 0, WordleGWindow.CORRECT_COLOR);
        gw.setSquareLetter(1, 0, "2"); 

        gw.setSquareColor(2, 0, WordleGWindow.CORRECT_COLOR);
        gw.setSquareLetter(2, 0, "3"); 

        gw.setSquareColor(3, 0, WordleGWindow.CORRECT_COLOR);
        gw.setSquareLetter(3, 0, "4"); 

        gw.setSquareColor(4, 0, WordleGWindow.CORRECT_COLOR);
        gw.setSquareLetter(4, 0, "5");  

        gw.setSquareColor(5, 0, WordleGWindow.CORRECT_COLOR);
        gw.setSquareLetter(5, 0, "6"); */

    }

/* Private instance variables */

    private WordleGWindow gw;

}
