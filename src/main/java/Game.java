import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private final String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
    private final String[] rank = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private final int[] value = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    public Deck deck;
    private ArrayList<Card> discardPile;
    private Player player1;
    private Player player2;
    private Scanner scanner;
    private int maxScore;
    private Player currentPlayer;
    private int turnCount;

    public Game(String player1Name, String player2Name, int maxScore) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.discardPile = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.maxScore = maxScore;
        this.deck = new Deck(rank, suits, value);
        this.currentPlayer = player1;
    }
// method to deal cards to each player and start the game
    public void dealCards() {
        for (int i = 0; i < 7; i++) {
            player1.addCard(deck.deal());
            player2.addCard(deck.deal());
        }
        discardPile.add(deck.deal());
    }
// allows the player to see what card they have in their hand in what order so that they can draw and discard
    public void displayHand(Player player) {
        ArrayList<Card> hand = player.getHand();
        System.out.println(player.getName() + "'s hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i));
        }
    }
// a players hand will be calculated based on the value of each card to help determine a winner of each round ---> each game
    public int calculateHandPoints(ArrayList<Card> hand) {
        int points = 0;
        for (Card card : hand) {
            points += card.getValue();
        }
        return points;
    }
// a method to check if a player has 3 cards of the same rank
    public boolean checkForThreeOfAKind(ArrayList<Card> hand) {
        for (int i = 0; i < hand.size(); i++) {
            int count = 0;
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).getRank().equals(hand.get(j).getRank())) {
                    count++;
                }
            }
            if (count >= 3) {
                return true;
            }
        }
        return false;
    }
// a method to check if a player has four cards of the same rank
    public boolean checkForFourOfAKind(ArrayList<Card> hand) {
        for (int i = 0; i < hand.size(); i++) {
            int count = 0;
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(i).getRank().equals(hand.get(j).getRank())) {
                    count++;
                }
            }
            if (count >= 4) {
                return true;
            }
        }
        return false;
    }
// this method checks to see if either player has a valid "run" in their hand
    public boolean isValidRun(ArrayList<Card> cards) {
        if (cards.size() < 3) {
            return false;
        }


        for (int i = 0; i < cards.size() - 1; i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                if (cards.get(i).getValue() > cards.get(j).getValue()) {
                    Card temp = cards.get(i);
                    cards.set(i, cards.get(j));
                    cards.set(j, temp);
                }
            }
        }


        String suit = cards.get(0).getSuit();
        for (int i = 0; i < cards.size(); i++) {
            if (!cards.get(i).getSuit().equals(suit)) {
                return false;
            }
            if (i > 0 && cards.get(i).getValue() != cards.get(i - 1).getValue() + 1) {
                return false;
            }
        }
        return true;
    }
// if a player has a winning hand, this method will check and know
    public boolean checkForWinningHand(Player player) {
        ArrayList<Card> hand = player.getHand();


        if (hand.size() == 0) {
            return true;
        }

        if (checkForThreeOfAKind(hand) && hand.size() <= 4) {
            return true;
        }

        if (checkForFourOfAKind(hand) && hand.size() <= 5) {
            return true;
        }

        return false;

    }
// this method allows for a player to make their turn, including drawing and discarding
    public void playerTurn(Player player) {
        System.out.println("\n" + player.getName() + "'s turn!");
        displayHand(player);


        System.out.println("Top card on discard pile: " + discardPile.get(discardPile.size() - 1));
        System.out.println("Draw from deck or discard pile? (d for deck, p for pile)");
        String choice = scanner.nextLine().toLowerCase();

        if (choice.equals("p")) {
            player.addCard(discardPile.remove(discardPile.size() - 1));
            System.out.println("Drew from discard pile");
        } else {
            Card drawnCard = deck.deal();
            if (drawnCard != null) {
                player.addCard(drawnCard);
                System.out.println("Drew from deck");
            } else {
                System.out.println("Deck is empty!");
            }
        }


        displayHand(player);


        System.out.println("Which card do you want to discard? (enter index)");
        int discardIndex = scanner.nextInt();
        scanner.nextLine();

        if (discardIndex >= 0 && discardIndex < player.getHand().size()) {
            Card discarded = player.removeCard(discardIndex);
            discardPile.add(discarded);
            System.out.println("Discarded: " + discarded);
        } else {
            System.out.println("Invalid choice!");
        }
    }
// when the first player is done, change the user to the second player
    public void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    // displays both players scores
    public void displayScores() {
        System.out.println("\n SCORES: ");
        System.out.println(player1.getName() + ": " + player1.getPoints());
        System.out.println(player2.getName() + ": " + player2.getPoints());
    }
// plays a round of the game, resets most of the variables
    public void playRound() {
        System.out.println("\nNEW ROUND");


        player1.clearHand();
        player2.clearHand();
        discardPile.clear();


        deck = new Deck(rank, suits, value);


        dealCards();
        currentPlayer = player1;


        boolean roundOver = false;
        turnCount = 0;
        int maxTurns = 20;
        while (!roundOver && !deck.isEmpty() && turnCount < maxTurns) {
            playerTurn(currentPlayer);
            turnCount++;

            if (checkForWinningHand(currentPlayer)) {
                System.out.println("\n" + currentPlayer.getName() + " wins this round");
                int handPoints = calculateHandPoints(currentPlayer.getHand());
                currentPlayer.addPoints(handPoints);
                roundOver = true;
            }

            if (!roundOver) {
                switchPlayer();
            }
        }

        if (turnCount >= maxTurns) {
            System.out.println("Turn limit reached! Round is a draw");
        } else if (deck.isEmpty()) {
            System.out.println("Deck ran out of cards. Round is a draw");
        }
    }
// shows rules and calls methods for playing rounds and displaying scores
    public void playGame() {
        System.out.println("Welcome to Rummy!");
        System.out.println(player1.getName() + " vs " + player2.getName());
        System.out.println("First to " + maxScore + " points wins\n");

        while (player1.getPoints() < maxScore && player2.getPoints() < maxScore) {
            playRound();
            displayScores();

            if (player1.getPoints() < maxScore && player2.getPoints() < maxScore) {
                System.out.println("\nContinue to next round? (y/n)");
                String cont = scanner.nextLine().toLowerCase();
                if (!cont.equals("y")) {
                    break;
                }
            }
        }

        displayScores();
        if (player1.getPoints() >= maxScore) {
            System.out.println("\n" + player1.getName() + " WINS THE GAME:)");
        } else if (player2.getPoints() >= maxScore) {
            System.out.println("\n" + player2.getName() + " WINS THE GAME:)");
        }
    }
// creates the game and runs it
    public static void main(String[] args) {
        Game g = new Game("Player 1", "Player 2", 50);
        g.playGame();
    }
}