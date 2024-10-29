package day09;

import Card.Card;
import Card.CardValue;
import Card.Suit;
import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class App {
    public static void main(String[] args) {
        // Task9();
        // Task10();
        // Task11();
        Task12();
        // countDown(5);

    }


    public static void Task9() {
        int min = 0;
        int max = 999999;
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1);

        List<Integer> guessedNumbers = new ArrayList<>();

        Console console = System.console();
        String keyboardInput = "";

        Integer lowerBound = min;
        Integer higherBound = max;
        Boolean userWin = false;
        
        
        // main game loop
        while (!keyboardInput.toLowerCase().equals("quit")) {
            System.out.println("Guessed Numbers: " + guessedNumbers);
            System.out.println("Generated number: XXXXXX");
            keyboardInput = console.readLine("Enter your guess number (0 to 999999): ");

            if (keyboardInput.toLowerCase().equals("quit")) {
                System.out.println("Thank you for playing! :)");
                break;
            }

        // Validate and process the guess
        Integer guessValue;
        try {
            guessValue = Integer.parseInt(keyboardInput);  // Convert the input into an integer
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number between 0 and 999999.");
            continue;  // Skip the rest of the loop if the input is not valid and go back to the start to prompt player to try again.
        }

        if (guessValue < min || guessValue > max) {
            System.out.println("Please enter a number within the valid range (0 to 999999).");
            continue;
        }

            // assuming input all digits (no error) --> for intelligence part
            // Integer guessValue = Integer.parseInt(keyboardInput);
            guessedNumbers.add(guessValue);
            Collections.sort(guessedNumbers);

            // if (guessValue > lowerBound) 
            //     if (guessValue != randomNumber)
            //         if (guessValue < randomNumber)
            //             lowerBound = guessValue;

            // if (guessValue > randomNumber)
            //     if (guessValue < higherBound)
            //         higherBound = guessValue;

            if (guessValue < randomNumber) {
                lowerBound = Math.max(guessValue, lowerBound);
                System.out.println("Correct number is higher. " + lowerBound + " and " + higherBound);
                
            } else if (guessValue > randomNumber) {
                higherBound = Math.min(guessValue, higherBound);
                System.out.println("Correct number is lower. " + lowerBound + " and " + higherBound);
                
            } else {
                System.out.println("Bingo!!!");
                userWin = true;
            }

            // restart game
            if (userWin) {
                userWin = false;
                lowerBound = min;
                higherBound = max;
                randomNumber = random.nextInt(max - min + 1) + min;
                guessedNumbers.clear();
            }
            

        }

    }


    public static void Task9a() {   // using recursion method, works exactly the same as Task 9
        int min = 0;
        int max = 999999;
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1);

        List<Integer> guessedNumbers = new ArrayList<>();

        Integer lowerBound = min;
        Integer higherBound = max;

        // Start the recursive guessing process
        guessNumber(randomNumber, lowerBound, higherBound, guessedNumbers);
    }

        public static void guessNumber(int randomNumber, int lowerBound, int higherBound, List<Integer> guessedNumbers) {
        // Create a console for input
        Console console = System.console();
        String keyboardInput = "";

        // Show the current state of the game
        System.out.println("Generated number: XXXXXX");
        System.out.println("Guessed Numbers: " + guessedNumbers);

        // Prompt the user for their guess
        keyboardInput = console.readLine("Enter your guess number (0 to 999999) or type 'quit' to exit: ");

        if (keyboardInput.toLowerCase().equals("quit")) {
            System.out.println("Thank you for playing! :)");
            return; // Exit the game
        }

        // Validate and process the guess
        Integer guessValue;
        try {
            guessValue = Integer.parseInt(keyboardInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number between 0 and 999999.");
            guessNumber(randomNumber, lowerBound, higherBound, guessedNumbers); // Retry
            return;
        }

        if (guessValue < lowerBound || guessValue > higherBound) {
            System.out.println("Please enter a number within the valid range (" + lowerBound + " to " + higherBound + ").");
            guessNumber(randomNumber, lowerBound, higherBound, guessedNumbers); // Retry
            return;
        }

        guessedNumbers.add(guessValue); // Store the guess
        Collections.sort(guessedNumbers); // Sort the guesses for display

        // Provide hints based on the guess
        if (guessValue < randomNumber) {
            lowerBound = Math.max(guessValue, lowerBound);
            System.out.println("Correct number is higher. " + lowerBound + " and " + higherBound);
        } else if (guessValue > randomNumber) {
            higherBound = Math.min(guessValue, higherBound);
            System.out.println("Correct number is lower. " + lowerBound + " and " + higherBound);
        } else {
            System.out.println("Bingo!!! You guessed the correct number: " + randomNumber);
            return; // Player has won
        }

        // Continue the guessing process
        guessNumber(randomNumber, lowerBound, higherBound, guessedNumbers);
    }
    

    public static void Task10() {
        Random random = new Random();
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numberList.add(random.nextInt(1, 100));
        }

        Console console = System.console();
        Boolean userInput = true;
        String guess = "";
        int currentPos = 1;
        List<String> correctList = new ArrayList<>();

        System.out.printf("\r\nFirst Number: %d\r\n", numberList.get(0));
        correctList.add("-");
        while (userInput) {
            guess = console.readLine("\r\nGuess the next number is higher 'H' or lower 'L':");

            String answer = "";
            String correct = "0";
            if (numberList.get(currentPos - 1) < numberList.get(currentPos)) 
                answer = "h";
            else 
                answer = "l";

            if (guess.trim().toLowerCase().equals(answer))
                correct = "1";
            else 
                correct = "0";
            correctList.add(correct);

            // print out the results
            for(int a = 0; a <= currentPos; a++) {
                if (a == 0){ 
                    System.out.printf("\r\n%d", numberList.get(a));
                }
                else {
                    System.out.printf("\t%d:%s", numberList.get(a), correctList.get(a));
                }

            }

            // program terminates/ends
            if (currentPos == 9) {
                userInput = false;
            }

            currentPos++;
        }
        System.out.println("\r\n");
    }

  

    public static void Task10a() {   // higher intelligence version
       
        Console console = System.console();
        boolean playAgain = true;

        while (playAgain) {
            playGame();  // Call the game logic

            // Ask if the user wants to play again
            String response = "";
            boolean validResponse = false;
            while (!validResponse) {
                response = console.readLine("\r\nDo you want to play again? (Y/N):").trim().toLowerCase();
                switch (response) {
                    case "y" -> {
                        validResponse = true;  // Restart the game
                        playAgain = true;
                    }
                    case "n" -> {
                        validResponse = true;  // Exit the loop, end the game
                        playAgain = false;
                    }
                    default -> System.out.println("Invalid input: please enter 'Y' for Yes or 'N' for No.");
                }
            }
        }
        System.out.println("Thanks for playing! :)");
    }
       
        public static void playGame() {

        
        Random random = new Random();
        List<Integer> numberList = new ArrayList<>();
        
        // Generate 10 random numbers between 1 and 100
        for (int i = 0; i < 10; i++) {
            numberList.add(random.nextInt(1, 100));
        }

        Console console = System.console();
        boolean userInput = true;
        String guess = "";
        int currentPos = 1;
        List<String> correctList = new ArrayList<>();

        System.out.printf("\r\nFirst Number: %d\r\n", numberList.get(0));
        correctList.add("-");

        
        while (userInput) {
            boolean validInput = false;

            // Prompt user for input until valid
            while (!validInput) {
                guess = console.readLine("\r\nGuess the next number is higher 'H' or lower 'L' or type 'quit' to exit: : ").trim().toLowerCase();
                if (guess.toLowerCase().equals("quit")) {
                    System.out.println("Thank you for playing! :)");
                    return; // Exit the game
                }
                
                // Check if the input is valid
                if (guess.equals("h") || guess.equals("l")) {
                    validInput = true; // Break the loop if input is valid
                } else {
                    System.out.println("Invalid input: please enter 'H' for higher or 'L' for lower.");
                }
            }

            String answer = "";
            String correct = "0";

            if (numberList.get(currentPos - 1) < numberList.get(currentPos)) 
                answer = "h";
            else 
                answer = "l";

            // Check if the guess is correct
            if (guess.trim().toLowerCase().equals(answer))
                correct = "1";
            else 
                correct = "0";
            correctList.add(correct);
    


            // Count remaining numbers greater and less than or equal to the last displayed number
            int lastNumber = numberList.get(currentPos);
            int greaterCount = 0;
            int lessEqualCount = 0;

            for (int i = currentPos + 1; i < numberList.size(); i++) {
                if (numberList.get(i) > lastNumber) {
                    greaterCount++;
                } else {
                    lessEqualCount++;
                }
            }

            // Print out the results
            for (int a = 0; a <= currentPos; a++) {
                if (a == 0) { 
                    System.out.printf("\r\n%d", numberList.get(a));
                } else {
                    System.out.printf("\t%d:%s", numberList.get(a), correctList.get(a));
                }
            }

            // Display counts of remaining numbers
            System.out.printf("\r\nRemaining numbers greater than %d: %d", lastNumber, greaterCount);
            System.out.printf("\r\nRemaining numbers less than or equal to %d: %d", lastNumber, lessEqualCount);

            // Program terminates/ends
            if (currentPos == 9) {
                userInput = false;
            }

            currentPos++;
        }
        System.out.println("\r\n");
    }
    

    public static void Task11() {
        String[] suits = {"SPADE", "HEARTS", "CLUBS", "DIAMOND"};
        String[] ranks = {"1","2","3","4","5","6","7","8","9","10","J","Q","K"};
        List<String> deck = new ArrayList<>();

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }

        Collections.shuffle(deck);

        for (String card: deck) {
            System.out.println(card);
        }

    }        

    public static void Task11a() {
        List<Card> deck = new ArrayList<>();

        for(int i = 0; i < 13; i++) {
            CardValue v = CardValue.values()[i];
            for (int j = 0; j < 4; j++) {
                Suit s = Suit.values()[j];
                
                Card c = new Card(v, s);
                deck.add(c);
            }
        }

        Collections.shuffle(deck);

        for(Card card: deck) {
            System.out.println(card.toString());
        }
    }

    
    // countdown with pause example (just for fun)
    public static void countDown(int number) {
           
        try {
            Thread.sleep(1000);  // Pause for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();  // Handle any interruptions
        }

        if (number == 0) {
                // Base case: When we reach zero, stop the recursion
                System.out.println("Done!");
            } else {
                // Recursive call: Print the number and count down by 1
                System.out.println(number);
                countDown(number - 1);  // Call the method again with a smaller number
            }
    }

    public static void Task12() {
        
        List<Card> deck = createDeck();
        Collections.shuffle(deck);
        System.out.println(deck);
        
        List<Card> playerHand = new ArrayList<>();
        List<Card> bankerHand = new ArrayList<>();

        for (int i = 0; i < 4; i++) {  // Draw 4 cards total (2 for player, 2 for banker)
            if (i % 2 == 0) {  // Even index, draw for player
                playerHand.add(drawCard(deck));
            } else {  // Odd index, draw for banker
                bankerHand.add(drawCard(deck));
            }
        }

        int playerTotalPoints = 0;
        for (Card pCard : playerHand) {
            playerTotalPoints += pCard.getValue().getCardValue();
        }

        int bankerTotalPoints = 0;
        for (Card bCard : bankerHand) {
            bankerTotalPoints += bCard.getValue().getCardValue();
        }

        System.out.println("Banker's Cards: " + bankerHand);
        System.out.println("Player's Cards: " + playerHand);
        
        System.out.println("Banker's Points: " + bankerTotalPoints);
        System.out.println("Player's Points: " + playerTotalPoints);

    }

        // List<Card> deck = new ArrayList<>();

        // for (int i = 0; i<13; i++) {
        //     CardValue v = CardValue.values()[i];
        //     for (int j = 0; j < 4; j++) {
        //         Suit s = Suit.values()[j];
            
        //         Card c = new Card(v, s);
        //         deck.add(c);
        //     }
        // }

        // Collections.shuffle(deck);

        // Card bankerFirstCard = deck.get(1);
        //     Card bankerSecondCard = deck.get(3);
        //     Card playerFirstCard = deck.get(0);
        //     Card playerSecondCard = deck.get(2);
        //     int playerTotalPoints = playerFirstCard.getValue().getCardValue() + playerSecondCard.getValue().getCardValue();
        //     System.out.println("Here are your cards: " + playerFirstCard + ", " + playerSecondCard);
        //     System.out.println("Total points: " + playerTotalPoints);
        //     // System.out.println("Total points: " + sum(playerFirstCard.getValue(), playerSecondCard.getValue())); -> another approach
        //     // but this apporach need define the sum method below to allow u to use the sum functn on cardvalues.
        //     // the getValue() here is just to simply retrieve the cardvalues using the Card objs (playerFirstCard and playerSecondCard).
        //     // public static int sum(CardValue a, CardValue b) {
        //     //     return a.getCardValue() + b.getCardValue();    --> defining the sum method
        //     // }
    
        
        public static List<Card> createDeck() {
            List<Card> deck = new ArrayList<>();
            for (CardValue v : CardValue.values()) {
                for (Suit s : Suit.values()) {
                    deck.add(new Card(v, s));
                }
            }
            return deck;
        }

        public static Card drawCard(List<Card> deck) {
            if (deck.isEmpty()) {
                throw new IllegalStateException("No more cards in the deck");
            }
            return deck.remove(0);  // Draw the next card
        }

        

        

        
        
            


            

            
            
    }





        

    
    
    
    

    




