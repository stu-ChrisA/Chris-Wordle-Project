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

    public static String returnRowOneGuessAsString()
    {
        String one = Integer.toString(guessRowOne);
        return one;

    }
    public static String returnRowTwoGuessAsString()
    {
        String two = Integer.toString(guessRowTwo);
        return two;

    }
    public static String returnRowThreeGuessAsString()
    {
        String three = Integer.toString(guessRowThree);
        return three;

    }
    public static String returnRowOnFourGuessAsString()
    {
        String four = Integer.toString(guessRowFour);
        return four;

    }
    public static String returnRowFiveGuessAsString()
    {
        String five = Integer.toString(guessRowFive);
        return five;

    }
    public static String returnRowSixGuessAsString()
    {
        String six = Integer.toString(guessRowSix);
        return six;

    }
    
}
