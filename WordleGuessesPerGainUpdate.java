public class WordleGuessesPerGainUpdate {
    private static int guessRowOne = 0;
    private static int guessRowTwo = 0;
    private static int guessRowThree = 0;
    private static int guessRowFour = 0;
    private static int guessRowFive = 0;
    private static int guessRowSix = 0;
    public static void updateGuessesEachGame()
    {
        if (Wordle.guesses == 1)
        {
            guessRowOne++;
        }
        else if(Wordle.guesses == 2)
        {
            guessRowTwo++;
        }
        else if(Wordle.guesses == 3)
        {
            guessRowThree++;
        }
        else if(Wordle.guesses == 4)
        {
            guessRowFour++;
        }
        else if(Wordle.guesses == 5)
        {
            guessRowFive++;
        }
        else
        {
            guessRowSix++;
        }
    }

    public static int returnRowOneGuess()
    {
        return guessRowOne;

    }
    public static int returnRowTwoGuess()
    {
        int two = guessRowTwo;
        return two;

    }
    public static int returnRowThreeGuess()
    {
        int three = guessRowThree;
        return three;

    }
    public static int returnRowFourGuess()
    {
        int four = guessRowFour;
        return four;

    }
    public static int returnRowFiveGuess()
    {
        int five = guessRowFive;
        return five;

    }
    public static int returnRowSixGuess()
    {
        int six = guessRowSix;
        return six;

    }
    
}
