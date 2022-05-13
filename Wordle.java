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
    

/* Private instance variables */

    private WordleGWindow gw;

}
