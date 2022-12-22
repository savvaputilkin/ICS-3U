package assingments;

import java.util.Scanner;
import java.util.logging.Handler;
public class GoFish2 {

    static Scanner in = new Scanner(System.in);



    private static final int WIN = 10;
    private static final String HEARTS = "H";
    private static final String CLUBS = "C";
    private static final String SPADES = "S";
    private static final String DIAMONDS = "D";
    private static final int NUM_VALUES = 13;
    private static final String JACK = "J";
    private static final String ACE = "A";
    private static final String QUEEN = "Q";
    private static final String KING = "K";
    private static final int NUM_SUITS = 4;
    private static int PLAYER1_SCORE = 0;
    private static int PLAYER2_SCORE = 0;
    private static int PLAYER3_SCORE = 0;
    private static int PLAYER4_SCORE = 0;
    private static String PLAYER1HAND_UPDATE = "";
    private static String PLAYER2HAND_UPDATE = "";
    private static String PLAYER3HAND_UPDATE = "";
    private static String PLAYER4HAND_UPDATE = "";
    private static boolean PLAYER1_GO_FISH = false;

    private static final String CARD_VALUES = "2345678910JQKAT";


    public static void main(String args[]) throws InterruptedException {
        String player1Hand = getCard() + " " + getCard() + " " + getCard() + " " + getCard() + " " + getCard() + " ";
        String player2Hand = getCard() + " " + getCard() + " " + getCard() + " " + getCard() + " " + getCard();
        String player3Hand = getCard() + " " + getCard() + " " + getCard() + " " + getCard() + " " + getCard();
        String player4Hand = getCard() + " " + getCard() + " " + getCard() + " " + getCard() + " " + getCard();

        displayHand(player1Hand, false, "Your Hand: ");

        displayHand(player2Hand, true, "Player 2's Hand: ");

        displayHand(player3Hand, true, "Player 3's Hand: ");

        displayHand(player4Hand, true, "Player 4's Hand: ");


        int scoreBefore = 0;
        PLAYER1HAND_UPDATE = player1Hand.trim().toLowerCase().replace("t","10");
        PLAYER2HAND_UPDATE = player2Hand.trim().toLowerCase().replace("t","10");
        PLAYER3HAND_UPDATE = player3Hand.trim().toLowerCase().replace("t","10");
        PLAYER4HAND_UPDATE = player4Hand.trim().toLowerCase().replace("t","10");
        player1Hand = getCardsValue(player1Hand,1);
        if(PLAYER1_SCORE > scoreBefore) {
            System.out.println("Pairs found, Hand updated.");
            displayHand(player1Hand, false, "Your Hand: ");
        }
        player1Hand = player1Hand.toLowerCase().replace("t","10").toUpperCase();
        while(PLAYER1_SCORE < 10 && PLAYER2_SCORE < 10 && PLAYER3_SCORE < 10 && PLAYER4_SCORE < 10) {
            // this is the main loop that runs while the game is being played


            // run player 1 turn
            player1Hand = PLAYER1HAND_UPDATE;
            player2Hand = PLAYER2HAND_UPDATE;
            player3Hand = PLAYER3HAND_UPDATE;
            player4Hand = PLAYER4HAND_UPDATE;
            guessCards(player1Hand, player2Hand, player3Hand, player4Hand);
            player1Hand = PLAYER1HAND_UPDATE;
            player2Hand = PLAYER2HAND_UPDATE;
            player3Hand = PLAYER3HAND_UPDATE;
            player4Hand = PLAYER4HAND_UPDATE;
            if(PLAYER1_GO_FISH) {
                player1Hand = player1Hand + getCard() + " ";
                player1Hand = getCardsValue(player1Hand,1);
            }

            displayScores();
            System.out.println("It is player 2's turn.");
            Thread.sleep(1500);
            computerGuessCards(player1Hand,player2Hand,player3Hand,player4Hand,2);
            player1Hand = PLAYER1HAND_UPDATE;
            player2Hand = PLAYER2HAND_UPDATE;
            player3Hand = PLAYER3HAND_UPDATE;
            player4Hand = PLAYER4HAND_UPDATE;
            Thread.sleep(1500);
            computerGuessCards(player1Hand,player2Hand,player3Hand,player4Hand,3);
            player1Hand = PLAYER1HAND_UPDATE;
            player2Hand = PLAYER2HAND_UPDATE;
            player3Hand = PLAYER3HAND_UPDATE;
            player4Hand = PLAYER4HAND_UPDATE;
            Thread.sleep(1500);
            computerGuessCards(player1Hand,player2Hand,player3Hand,player4Hand,4);
            player1Hand = PLAYER1HAND_UPDATE;
            player2Hand = PLAYER2HAND_UPDATE;
            player3Hand = PLAYER3HAND_UPDATE;
            player4Hand = PLAYER4HAND_UPDATE;
            Thread.sleep(1500);

            System.out.println("scores:");

            player1Hand = getCardsValue(player1Hand,1);
            player1Hand = fillHand(player1Hand);
            player2Hand = getCardsValue(player2Hand,2);
            player2Hand = fillHand(player2Hand);
            player3Hand = getCardsValue(player3Hand,3);
            player3Hand = fillHand(player3Hand);
            player4Hand = getCardsValue(player4Hand,4);
            player4Hand = fillHand(player4Hand);
            displayScores();
            Thread.sleep(1500);
            displayHand(player1Hand, false, "Your Hand: ");

            displayHand(player2Hand, true, "Player 2's Hand: ");

            displayHand(player3Hand, true, "Player 3's Hand: ");

            displayHand(player4Hand, true, "Player 4's Hand: ");
        }
        System.out.println("game over! final scores!");
        displayScores();


    }

    private static String fillHand(String hand) {
        if(hand.replace(" ","").length() < 2) {
            hand = getCard() + " " + getCard() + " " + getCard() + " " + getCard() + " " + getCard();
        }
        return hand;
    }

    private static void displayScores() {
        System.out.println("Player 1 score: " + PLAYER1_SCORE);
        System.out.println("Player 2 score: " + PLAYER2_SCORE);
        System.out.println("Player 3 score: " + PLAYER3_SCORE);
        System.out.println("Player 4 score: " + PLAYER4_SCORE);
    }


    private static String randomFromAllowed(String allowedRandoms) {
        allowedRandoms = allowedRandoms.replace("10","t");
        int randomPick = (int) (Math.random() * CARD_VALUES.length());
        if(!allowedRandoms.contains("" + CARD_VALUES.charAt(randomPick))) return randomFromAllowed(allowedRandoms.replace("t","10"));
        return ("" + CARD_VALUES.charAt(randomPick)).replace("t","10").toUpperCase();
    }

    private static int randomPlayer(int blacklistedPlayer) {
        int randomPick = (int) (Math.random() * 4) + 1;
        if(randomPick == blacklistedPlayer) return randomPlayer(blacklistedPlayer);
        return randomPick;
    }

    private static void computerGuessCards(String player1Hand, String player2Hand, String player3Hand, String player4Hand, int player) {
        player1Hand = player1Hand.replace("10","t");

        if(player == 2) {
            int playerToChooseFrom = randomPlayer(2);
            String player2HandSuitless = "";
            for(int i = 0; i < player2Hand.length(); i++) {
                if(CARD_VALUES.contains("" + player2Hand.charAt(i))) {
                    // real card value
                    player2HandSuitless += player2Hand.charAt(i);
                }
            }
            String randomChoice = randomFromAllowed(player2HandSuitless);
            System.out.println("Player 2 asks player " + playerToChooseFrom + " if they have any: " + randomChoice + "s.");
            if(playerToChooseFrom == 1) {
                if(player1Hand.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 1 has a pair!");
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER1HAND_UPDATE.toLowerCase().charAt(PLAYER1HAND_UPDATE.indexOf(randomChoice) + 1);
                    PLAYER1HAND_UPDATE = PLAYER1HAND_UPDATE.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER1HAND_UPDATE.toLowerCase().charAt(PLAYER1HAND_UPDATE.indexOf(randomChoice) + 1))," ").toUpperCase();
                    PLAYER2HAND_UPDATE = PLAYER2HAND_UPDATE+ " " + pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER2HAND_UPDATE = PLAYER2HAND_UPDATE + " " + getCard();
                }
            } else if(playerToChooseFrom == 3) {
                if(player3Hand.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 1 has a pair!");
                    PLAYER3HAND_UPDATE = PLAYER3HAND_UPDATE.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER3HAND_UPDATE.toLowerCase().charAt(PLAYER3HAND_UPDATE.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER3HAND_UPDATE.toLowerCase().charAt(PLAYER3HAND_UPDATE.indexOf(randomChoice) + 1);
                    PLAYER2HAND_UPDATE = PLAYER2HAND_UPDATE + " " + pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER2HAND_UPDATE = PLAYER2HAND_UPDATE + " " + getCard();
                }
            } else if(playerToChooseFrom == 4) {
                if(player4Hand.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 1 has a pair!");
                    PLAYER4HAND_UPDATE = PLAYER4HAND_UPDATE.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER4HAND_UPDATE.toLowerCase().charAt(PLAYER4HAND_UPDATE.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER4HAND_UPDATE.toLowerCase().charAt(PLAYER4HAND_UPDATE.indexOf(randomChoice) + 1);
                    PLAYER2HAND_UPDATE = PLAYER4HAND_UPDATE + " " + pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER4HAND_UPDATE = PLAYER4HAND_UPDATE + " " + getCard() ;
                }
            }
        } else if(player == 3) {
            int playerToChooseFrom = randomPlayer(3);
            String player3HandSuitless = "";
            for(int i = 0; i < player3Hand.length(); i++) {
                if(CARD_VALUES.contains("" + player3Hand.charAt(i))) {
                    // real card value
                    player3HandSuitless += player3Hand.charAt(i);
                }
            }
            String randomChoice = randomFromAllowed(player3HandSuitless);
            System.out.println("Player 3 asks player " + playerToChooseFrom + " if they have any: " + randomChoice + "s.");
            if(playerToChooseFrom == 1) {
                if(player1Hand.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 1 has a pair!");
                    PLAYER1HAND_UPDATE = PLAYER1HAND_UPDATE.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER1HAND_UPDATE.toLowerCase().charAt(PLAYER1HAND_UPDATE.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER1HAND_UPDATE.toLowerCase().charAt(PLAYER1HAND_UPDATE.indexOf(randomChoice) + 1);
                    PLAYER3HAND_UPDATE = PLAYER3HAND_UPDATE + " " +pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER3HAND_UPDATE = PLAYER3HAND_UPDATE + " " + getCard();
                }
            } else if(playerToChooseFrom == 2) {
                if(player2Hand.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 2 has a pair!");
                    PLAYER2HAND_UPDATE = PLAYER2HAND_UPDATE.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER2HAND_UPDATE.toLowerCase().charAt(PLAYER2HAND_UPDATE.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER2HAND_UPDATE.toLowerCase().charAt(PLAYER2HAND_UPDATE.indexOf(randomChoice) + 1);
                    PLAYER3HAND_UPDATE = PLAYER3HAND_UPDATE + " " +pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER3HAND_UPDATE = PLAYER3HAND_UPDATE + " " + getCard();
                }
            } else if(playerToChooseFrom == 4) {
                if(player4Hand.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 4 has a pair!");
                    PLAYER4HAND_UPDATE = PLAYER4HAND_UPDATE.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER4HAND_UPDATE.toLowerCase().charAt(PLAYER4HAND_UPDATE.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER4HAND_UPDATE.toLowerCase().charAt(PLAYER4HAND_UPDATE.indexOf(randomChoice) + 1);
                    PLAYER3HAND_UPDATE = PLAYER3HAND_UPDATE + " " + pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER3HAND_UPDATE = PLAYER3HAND_UPDATE + " " + getCard();
                }
            }
        } else if(player == 4) {
            int playerToChooseFrom = randomPlayer(4);
            String player4HandSuitless = "";
            for(int i = 0; i < player4Hand.length(); i++) {
                if(CARD_VALUES.contains("" + player4Hand.charAt(i))) {
                    // real card value
                    player4HandSuitless += player4Hand.charAt(i);
                }
            }
            String randomChoice = randomFromAllowed(player4HandSuitless);
            System.out.println("Player 4 asks player " + playerToChooseFrom + " if they have any: " + randomChoice + "s.");
            if(playerToChooseFrom == 1) {
                if(player1Hand.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 1 has a pair!");
                    PLAYER1HAND_UPDATE = PLAYER1HAND_UPDATE.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER1HAND_UPDATE.toLowerCase().charAt(PLAYER1HAND_UPDATE.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER1HAND_UPDATE.toLowerCase().charAt(PLAYER1HAND_UPDATE.indexOf(randomChoice) + 1);
                    PLAYER4HAND_UPDATE = PLAYER4HAND_UPDATE + " " + pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER4HAND_UPDATE = PLAYER4HAND_UPDATE + " " + getCard();
                }
            } else if(playerToChooseFrom == 2) {
                if(player2Hand.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 2 has a pair!");
                    PLAYER2HAND_UPDATE = PLAYER2HAND_UPDATE.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER2HAND_UPDATE.toLowerCase().charAt(PLAYER2HAND_UPDATE.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER2HAND_UPDATE.toLowerCase().charAt(PLAYER2HAND_UPDATE.indexOf(randomChoice) + 1);
                    PLAYER4HAND_UPDATE = PLAYER4HAND_UPDATE + " " +pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER4HAND_UPDATE = PLAYER4HAND_UPDATE + " " + getCard() + " ";
                }
            } else if(playerToChooseFrom == 3) {
                if(player4Hand.toLowerCase().contains(randomChoice.toLowerCase())) {
                    System.out.println("Player 3 has a pair!");
                    PLAYER3HAND_UPDATE = PLAYER3HAND_UPDATE.toLowerCase().replace(randomChoice.toLowerCase() + ("" + PLAYER3HAND_UPDATE.toLowerCase().charAt(PLAYER3HAND_UPDATE.indexOf(randomChoice) + 1)),"").toUpperCase();
                    char pick = randomChoice.charAt(0);
                    char after = PLAYER3HAND_UPDATE.toLowerCase().charAt(PLAYER3HAND_UPDATE.indexOf(randomChoice) + 1);
                    PLAYER4HAND_UPDATE = PLAYER4HAND_UPDATE + " " +  pick + after + " ";
                } else {
                    System.out.println("Go fish!");
                    PLAYER4HAND_UPDATE = PLAYER4HAND_UPDATE + " " + getCard();
                }
            }
        }
        PLAYER1HAND_UPDATE = PLAYER1HAND_UPDATE.toUpperCase().replace("T","10");
        PLAYER2HAND_UPDATE = PLAYER2HAND_UPDATE.toUpperCase().replace("T","10");
        PLAYER3HAND_UPDATE = PLAYER3HAND_UPDATE.toUpperCase().replace("T","10");
        PLAYER4HAND_UPDATE = PLAYER4HAND_UPDATE.toUpperCase().replace("T","10");

    }

    private static void guessCards(String player1Hand, String player2Hand, String player3Hand, String player4Hand){
        PLAYER1_GO_FISH = false;
        System.out.println("Who's cards are you guessing for? (You are Player1) (Player 2, Player 3, P4, etc.): ");
        String playerGuess = in.nextLine().toLowerCase();

        //player 2 guessing
        if (playerGuess.equals("player 2") || playerGuess.equals("p2")) {
            System.out.println("What cards do you have, " + playerGuess + "? (i.e 2, J, etc) : ");
            String cardPairs = in.nextLine().toUpperCase();
            int selectedCard = player2Hand.indexOf(cardPairs);
            int selectedCardSelf = player1Hand.indexOf(cardPairs);


            if (CARD_VALUES.indexOf(cardPairs) == -1) {
                System.out.println("Invalid Input, Please guess a card in your own hand ( 2,3,4,J,K,Q,A)");
                guessCards(player2Hand, player2Hand, player3Hand, player4Hand);

                return;

            } else if (selectedCard == -1 && selectedCardSelf == -1) {
                System.out.println("Please select the cards in your own hand. ");
                guessCards(player1Hand, player2Hand, player3Hand, player4Hand);
                return;
            } else if (selectedCard == -1) {
                System.out.println("go fish!");
                PLAYER1_GO_FISH = true;
                return;
                // start other players turn
            }

            if (CARD_VALUES.indexOf(cardPairs) != -1) {
                String p1NewHand = player1Hand + player2Hand.substring(selectedCard, (selectedCard + 2)) + " ";
                String beforeNewCard = player2Hand.substring(0, selectedCard);

                String afterNewCard = player2Hand.substring(selectedCard + 3);

                String p2NewHand = beforeNewCard + afterNewCard;

                player2Hand = p2NewHand;

                player1Hand = p1NewHand;

                System.out.println(playerGuess + " Has a pair!");
                player2Hand = player2Hand + " " + getCard();

//                PLAYER1_SCORE++;
                System.out.println("Your Score: " + PLAYER1_SCORE);
                displayHand(player1Hand, false, "Your New Hand: ");
                PLAYER2HAND_UPDATE = player2Hand;
                PLAYER1HAND_UPDATE = player1Hand;
                //displayHand(player2Hand, false, "p2 new hand ");
            }
            // player 3 guessing
        } else if (playerGuess.equals("player 3") || playerGuess.equals("p3")){
            System.out.println("What cards do you have, " + playerGuess + "? (i.e 2, J, etc) : ");
            String cardPairsP3 = in.nextLine().toUpperCase();
            int selectedCardP3= player3Hand.indexOf(cardPairsP3);
            int selectedCardSelfP3 = player1Hand.indexOf(cardPairsP3);


            if (CARD_VALUES.indexOf(cardPairsP3) == -1) {
                System.out.println("Invalid Input, Please guess a card in your own hand ( 2,3,4,J,K,Q,A");
                guessCards(player2Hand, player2Hand, player3Hand, player4Hand);

                return;

            }

            else if (selectedCardP3 == -1 && selectedCardSelfP3 == -1){
                System.out.println("Please select the cards in your own hand. ");
                guessCards(player1Hand, player2Hand, player3Hand, player4Hand);
                return;
            }

            else if (selectedCardP3 == -1) {
                System.out.println("go fish!");
                PLAYER1_GO_FISH = true;
                return;
                // start other players turn
            }

            if (CARD_VALUES.indexOf(cardPairsP3)!= -1){
                String p1NewHand = player1Hand + player3Hand.substring(selectedCardP3, (selectedCardP3 + 2)) + " ";
                String beforeNewCard = player3Hand.substring(0, selectedCardP3);
                System.out.println("selectedcardp3: " + selectedCardP3 + " sout " + player3Hand );
                String afterNewCard = player3Hand.substring(selectedCardP3 + 3);

                String p3NewHand = beforeNewCard + afterNewCard;

                player3Hand = p3NewHand;

                player1Hand = p1NewHand;

                System.out.println(playerGuess + " Has a pair!");
                player3Hand = player3Hand + " " + getCard();
                displayHand(player1Hand, false, "Your New Hand: ");
                System.out.println("Your Score: " + PLAYER1_SCORE);
                PLAYER3HAND_UPDATE = p3NewHand;
                PLAYER1HAND_UPDATE = p1NewHand;
                //displayHand(player2Hand, false, "p2 new hand ");
            }
        }
        //player 4 guessing
        else if (playerGuess.equals("player 4") || playerGuess.equals("p4")){
            System.out.println("What cards do you have, " + playerGuess + "? (i.e 2, J, etc) : ");
            String cardPairsP4 = in.nextLine().toUpperCase();
            int selectedCardP4= player3Hand.indexOf(cardPairsP4);
            int selectedCardSelfP4 = player1Hand.indexOf(cardPairsP4);


            if (CARD_VALUES.indexOf(cardPairsP4) == -1) {
                System.out.println("Invalid Input, Please guess a card in your own hand ( 2,3,4,J,K,Q,A");
                guessCards(player2Hand, player2Hand, player3Hand, player4Hand);

                return;

            }

            else if (selectedCardP4 == -1 && selectedCardSelfP4 == -1){
                System.out.println("Please select the cards in your own hand. ");
                guessCards(player1Hand, player2Hand, player3Hand, player4Hand);
                return;
            }

            else if (selectedCardP4 == -1) {
                System.out.println("go fish!");
                PLAYER1_GO_FISH = true;
                // start other players turn
                return;
            }

            if (CARD_VALUES.indexOf(cardPairsP4) != -1){
                String p1NewHand = player1Hand + player3Hand.substring(selectedCardP4, (selectedCardP4 + 2)) + " ";
                String beforeNewCard = player3Hand.substring(0, selectedCardP4);
                if(selectedCardP4 + 3 > player3Hand.length()) guessCards(player1Hand,player2Hand,player3Hand,player4Hand);
                String afterNewCard = player3Hand.substring(selectedCardP4 + 3);

                String p4NewHand = beforeNewCard + afterNewCard;

                player4Hand = p4NewHand;

                player1Hand = p1NewHand;

                System.out.println(playerGuess + " Has a pair!");
                player4Hand = player4Hand + " " + getCard();
                displayHand(player1Hand, false, "Your New Hand: ");
                PLAYER4HAND_UPDATE = p4NewHand;
                PLAYER1HAND_UPDATE = p1NewHand;
                System.out.println("Your Score: " + PLAYER1_SCORE);
                //displayHand(player2Hand, false, "p2 new hand ");
            }






        }

        else {
            System.out.println("Please select a valid player, You Are Player 1, Avaliable Players: 2, 3, 4");
            guessCards(player1Hand, player2Hand, player3Hand, player4Hand);
        }
    }


    private static String removeCardType(String hand, char cardSuitless) {

        hand = hand.toLowerCase();
        // max of 2 pairs
        String pair1Type = "PLACEHOLDER";
        String pair2Type = "PLACEHOLDER";
        int removedCardsOfType = 0;
        int maxRemovedCardsOfType = 2;
        for(int i = 0; i < hand.length() - 1; i++) {
            if(!(i + 1 > hand.length())) {
                char currentChar = hand.charAt(i);
                char nextChar = hand.charAt(i + 1);
                if(pair1Type.length() > 3) {
                    if (currentChar == cardSuitless && removedCardsOfType < maxRemovedCardsOfType) {
                        pair1Type = "" + currentChar + nextChar + " ";
                        // has to be a match
                        removedCardsOfType++;
                    }
                } else {
                    if(currentChar == cardSuitless && removedCardsOfType < maxRemovedCardsOfType) {
                        pair2Type = "" + currentChar + nextChar + " ";
                        // has to be a match
                        removedCardsOfType++;
                    }
                }
            }
        }
        hand = hand.replace(pair1Type,"");
        hand = hand.replace(pair2Type,"");
        return hand.toUpperCase();
    }


    private static int increaseScore(int player) {
        int score = 0;
        if (player == 1) {
            score = PLAYER1_SCORE + 1;
            PLAYER1_SCORE++;
        }
        if (player == 2) {
            score = PLAYER2_SCORE + 1;
            PLAYER2_SCORE++;
        } else if (player == 3) {
            score = PLAYER3_SCORE + 1;
            PLAYER3_SCORE++;
        } else if (player == 4) {
            score = PLAYER4_SCORE + 1;
            PLAYER4_SCORE++;
        }
        return score;
    }

    //finds pairs and keeps score
    // also removes them from the deck and draws a new one
    private static String getCardsValue(String cards, int player) {
        cards = cards.toLowerCase();
        // replace 10 with t
        cards = cards.replace("10","t");
        int pairs = 0;
        boolean alreadyDoneFirstPair = false;
        boolean alreadyDoneSecondPair = false;
        boolean increasedScore = false;
        int score = 0;
        String alreadyUsedCards = "";
        String cardTypesToRemove = "";
        for (int i = 0; i < cards.length(); i++) {
            for(int j = 0; j < cards.length(); j++) {
                if(i == j) continue;
                char currentChar = cards.toLowerCase().charAt(i);
                char comparativeChar = cards.toLowerCase().charAt(j);
                if(CARD_VALUES.toLowerCase().indexOf(currentChar) != -1 && CARD_VALUES.toLowerCase().indexOf(comparativeChar) != -1) {
                    if(alreadyUsedCards.contains("" + currentChar) || alreadyUsedCards.contains("" + comparativeChar)) continue;
                    if(currentChar == comparativeChar) {
                        pairs++;
                        alreadyUsedCards = alreadyUsedCards + currentChar + comparativeChar;
                        cardTypesToRemove += currentChar;
                    }

                    if (pairs == 1 && !alreadyDoneFirstPair && !increasedScore) {
                        alreadyDoneFirstPair = true;
                        increasedScore = true;
                        score = increaseScore(player);
                    }
                    if (pairs == 2 && !alreadyDoneSecondPair) {
                        alreadyDoneSecondPair = true;
                        increasedScore = true;
                        score = increaseScore(player);
                    }


                }
            }

        }
        cards = cards.toUpperCase();
        if(cards.charAt(cards.length() - 1) != ' ') cards = cards + " ";
        if(cardTypesToRemove.length() > 1) {
            cards = removeCardType(cards,cardTypesToRemove.charAt(1));
            cards = removeCardType(cards,cardTypesToRemove.charAt(0));
            return cards.replace("T","10");
        } else if(cardTypesToRemove.length() == 1) {
            cards = removeCardType(cards, cardTypesToRemove.charAt(0));
            return cards.replace("T", "10");
        }
        return cards.replace("T","10");
    }


    private static void displayHand(String cards, boolean isHidden, String label) {
        cards = cards.toLowerCase().replace("t","10");
        cards = cards.toUpperCase();
        String result = "";
        if (isHidden)
            result += label + "XX " + "XX " + "XX " + "XX " + "XX ";
        else
            result += label + cards;

        System.out.println(result);
    }



    public static String getCard() {
        return getValue() + getSuit();
    }

    private static String getSuit() {
        int iSuit = (int) (Math.random() * NUM_SUITS) + 1;

        if (iSuit == 1)
            return HEARTS;
        else if (iSuit == 2)
            return SPADES;
        else if (iSuit == 3)
            return CLUBS;
        else
            return DIAMONDS;

    }


    private static String getValue() {
        int iValue = (int) (Math.random() * NUM_VALUES) + 1;

        if (iValue == 1)
            return ACE;
        else if (iValue == 11)
            return JACK;
        else if (iValue == 12)
            return QUEEN;
        else if (iValue == 13)
            return KING;
        else
            return "" + iValue;
    }


}


