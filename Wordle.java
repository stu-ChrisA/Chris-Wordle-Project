/*
 * File: Wordle.java
 * -----------------
 * This module is the starter file for the Wordle assignment.
 * BE SURE TO UPDATE THIS COMMENT WHEN YOU COMPLETE THE CODE.
 */

import edu.willamette.cs1.wordle.WordleDictionary;
import edu.willamette.cs1.wordle.WordleGWindow;

public class Wordle {
    private int randomIndex = (int) (Math.random() * WordleDictionary.FIVE_LETTER_WORDS.length);
    private String answerToWordle = WordleDictionary.FIVE_LETTER_WORDS[randomIndex].toUpperCase();
    
    public void run() {
        gw = new WordleGWindow();
        gw.addEnterListener((s) -> enterAction(s));
    }

/*
 * Called when the user hits the RETURN key or clicks the ENTER button,
 * passing in the string of characters on the current row.
 */

    public void enterAction(String s) {
        String userWord = s;
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
            gw.showMessage("not in word list.");

        }else{
            gw.showMessage("in word list.");
        }


        for(int col = 0; col < WordleGWindow.N_COLS; col++)
        {
            if(userWord.substring(col,col+1).equals(answerToWordle))    //Fix color later or tomorrow
            {
                gw.setSquareColor(gw.getCurrentRow(), col,  WordleGWindow.CORRECT_COLOR);
            }
            if(gw.getSquareLetter(gw.getCurrentRow(), col).compareTo(answerToWordle) != -1)
            {
                gw.setSquareColor(gw.getCurrentRow(), col,  WordleGWindow.PRESENT_COLOR);
            }
            else
            {
                gw.setSquareColor(gw.getCurrentRow(), col,  WordleGWindow.MISSING_COLOR);
            }

        }
        updateRow();

    }

/* Startup code */

    public static void main(String[] args) {
        new Wordle().run();
    }
    public void RandomWord()
    {
        int letter = 0;
        for(int c = 0; c < WordleGWindow.N_COLS; c++)
        {
            gw.setSquareLetter(0,c,answerToWordle.substring(letter,letter+1));
            letter++;
        }
    }
    public void updateRow(){
        int rowIndex = 1;
        if(rowIndex <= WordleGWindow.N_ROWS)
        {
            gw.setCurrentRow(rowIndex);
        }
    }
    

/* Private instance variables */

    private WordleGWindow gw;

}
