/*
 * File: Wordle.java
 * -----------------
 * This module is the starter file for the Wordle assignment.
 * BE SURE TO UPDATE THIS COMMENT WHEN YOU COMPLETE THE CODE.
 */

import edu.willamette.cs1.wordle.WordleDictionary;
import edu.willamette.cs1.wordle.WordleGWindow;

public class Wordle {
    private final double FINAL_S_FRACTION = 1.0/3.0;
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

    public void enterAction(String s) { // Checks if the word is in the dictonary, then colors the square boxes/key caps to it's correct color for the guess and then displayed a "you win" message if the user guesses the word correctly
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
            gw.showMessage("not in word list. Try again.");
        }else{
            gw.showMessage("in word list.");
        }


        for(int col = 0; col < WordleGWindow.N_COLS; col++)
        {
            if(gw.getSquareLetter(gw.getCurrentRow(), col).equals(answerToWordle.substring(col,col+1)))   
            {
                gw.setSquareColor(gw.getCurrentRow(), col,  WordleGWindow.CORRECT_COLOR);
                gw.setKeyColor(gw.getSquareLetter(gw.getCurrentRow(), col), WordleGWindow.CORRECT_COLOR);
            }
            else if((answerToWordle).indexOf(gw.getSquareLetter(gw.getCurrentRow(), col))!=-1)
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

        if(userWord.equalsIgnoreCase(answerToWordle))
        {
            rainbowEffect();
            gw.showMessage("You got the word in " + guesses + " guess.");
            if(guesses > 1)
            {
                gw.showMessage("You got the word in " + guesses + " guesses.");
            }
            WordleGuessesPerGainUpdate.updateGuessesEachGame();
            displayGuessesPerGame();
            gw.showMessage("Here are your scores for all games you've played!");
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
            for(int clearCol = 0; clearCol < WordleGWindow.N_COLS; clearCol++)
            {
                gw.setSquareColor(row, clearCol, WordleGWindow.WHITE_COLOR);
                gw.setSquareLetter(row, clearCol, ""); 
            }
        }

        //__________________________________________________________

        //Row One:
        gw.setSquareColor(0, 0, WordleGWindow.CORRECT_COLOR);
        gw.setSquareLetter(0, 0, "1");
        if(WordleGuessesPerGainUpdate.returnRowOneGuess() > 9)
        {
            String RowOneNumToWord2 = Integer.toString(WordleGuessesPerGainUpdate.returnRowOneGuess() % 10);
            String RowOnenumToWord1 = Integer.toString(WordleGuessesPerGainUpdate.returnRowOneGuess() / WordleGuessesPerGainUpdate.returnRowOneGuess());
            gw.setSquareLetter(0,3, RowOnenumToWord1);
            gw.setSquareLetter(0,4, RowOneNumToWord2);

        }
        else{
            gw.setSquareLetter(0,4,Integer.toString(WordleGuessesPerGainUpdate.returnRowOneGuess()));

        }

        //__________________________________________________________

        //Row Two:
        gw.setSquareColor(1, 0, WordleGWindow.CORRECT_COLOR);
        gw.setSquareLetter(1, 0, "2"); 
        if(WordleGuessesPerGainUpdate.returnRowTwoGuess() > 9)
        {
            String RowTwoNumToWord2 = Integer.toString(WordleGuessesPerGainUpdate.returnRowTwoGuess() % 10);
            String RowTwonumToWord1 = Integer.toString(WordleGuessesPerGainUpdate.returnRowTwoGuess() / WordleGuessesPerGainUpdate.returnRowTwoGuess());
            gw.setSquareLetter(1,3,RowTwonumToWord1);
            gw.setSquareLetter(1,4,RowTwoNumToWord2);
        }
        else{
            gw.setSquareLetter(1,4,Integer.toString(WordleGuessesPerGainUpdate.returnRowTwoGuess()));
        }
        
        //__________________________________________________________

        //Row Three:
        gw.setSquareColor(2, 0, WordleGWindow.CORRECT_COLOR);
        gw.setSquareLetter(2, 0, "3"); 
        if(WordleGuessesPerGainUpdate.returnRowThreeGuess() > 9)
        {
            String RowThreeNumToWord2 = Integer.toString(WordleGuessesPerGainUpdate.returnRowThreeGuess() % 10);
            String RowThreenumToWord1 = Integer.toString(WordleGuessesPerGainUpdate.returnRowThreeGuess() / WordleGuessesPerGainUpdate.returnRowThreeGuess());
            gw.setSquareLetter(2,3,RowThreenumToWord1);
            gw.setSquareLetter(2,4,RowThreeNumToWord2);
        }
        else{
            gw.setSquareLetter(2,4,Integer.toString(WordleGuessesPerGainUpdate.returnRowThreeGuess()));
        }

        //__________________________________________________________

        //Row Four:
        gw.setSquareColor(3, 0, WordleGWindow.CORRECT_COLOR);
        gw.setSquareLetter(3, 0, "4"); 
        if(WordleGuessesPerGainUpdate.returnRowFourGuess() > 9)
        {
            String RowFourNumToWord2 = Integer.toString(WordleGuessesPerGainUpdate.returnRowFourGuess() % 10);
            String RowFourNumToWord1 = Integer.toString(WordleGuessesPerGainUpdate.returnRowFourGuess() / WordleGuessesPerGainUpdate.returnRowFourGuess());
            gw.setSquareLetter(3,3,RowFourNumToWord1);
            gw.setSquareLetter(3,4,RowFourNumToWord2);
        }
        else{
            gw.setSquareLetter(3,4,Integer.toString(WordleGuessesPerGainUpdate.returnRowFourGuess()));
        }

        //__________________________________________________________

        //Row Five:
        gw.setSquareColor(4, 0, WordleGWindow.CORRECT_COLOR);
        gw.setSquareLetter(4, 0, "5");  
        if(WordleGuessesPerGainUpdate.returnRowFiveGuess() > 9)
        {
            String RowFiveNumToWord2 = Integer.toString(WordleGuessesPerGainUpdate.returnRowFiveGuess() % 10);
            String RowFiveNumToWord1 = Integer.toString(WordleGuessesPerGainUpdate.returnRowFiveGuess() / WordleGuessesPerGainUpdate.returnRowFiveGuess());
            gw.setSquareLetter(4,3,RowFiveNumToWord1);
            gw.setSquareLetter(4,4,RowFiveNumToWord2);
        }
        else{
            gw.setSquareLetter(4,4,Integer.toString(WordleGuessesPerGainUpdate.returnRowFiveGuess()));
        }

        //__________________________________________________________

        //Row Six:
        gw.setSquareColor(5, 0, WordleGWindow.CORRECT_COLOR);
        gw.setSquareLetter(5, 0, "6");    
        if(WordleGuessesPerGainUpdate.returnRowSixGuess() > 9)
        {
            String RowSixNumToWord2 = Integer.toString(WordleGuessesPerGainUpdate.returnRowSixGuess() % 10);
            String RowSixNumToWord1 = Integer.toString(WordleGuessesPerGainUpdate.returnRowSixGuess() / WordleGuessesPerGainUpdate.returnRowSixGuess());
            gw.setSquareLetter(5,3,RowSixNumToWord1);
            gw.setSquareLetter(5,4,RowSixNumToWord2);
        }
        else{
            gw.setSquareLetter(5,4,Integer.toString(WordleGuessesPerGainUpdate.returnRowSixGuess()));
        }

    }


/* Private instance variables */

    private WordleGWindow gw;

}
