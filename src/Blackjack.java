import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;


public class Blackjack{
    private Scanner scan;
    private ArrayList<String> deck;
    private ArrayList<String> playerHand;
    private ArrayList<String> dealerHand;
    private BlackjackLogic blackjack;
    private Money balance;
    private double betAmount;

    public Blackjack(Scanner scan, Money balance){
        blackjack = new BlackjackLogic();
        deck = new ArrayList<>();
        this.balance = balance;
        this.scan = scan;
    }
    public void startBlackjack(){
        deck = blackjack.initializeDeck();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        Collections.shuffle(deck);
        dealCards();
        System.out.println("_______________________________");
        System.out.println("Your current balance: $" + String.format("%.2f", balance.getMoney()));
        System.out.print("Enter your bet amount ($0 to skip betting): $");
        betAmount = scan.nextDouble();
        scan.nextLine();
        while (betAmount > balance.getMoney() || betAmount < 0){
            System.out.println("Invalid value");
            System.out.print("Enter your bet amount: $");
            betAmount = Double.parseDouble(scan.nextLine());
        }

        System.out.println("Your hand: " + playerHand + " (Total: " + blackjack.calculateHand(playerHand) + ")");
        System.out.println("Dealer's first card: " + dealerHand.getFirst());
        playerTurn();
        if (blackjack.calculateHand(playerHand)<=21){
            dealerTurn();
            if (blackjack.calculateHand(dealerHand) > 21){
                System.out.println("Dealer busted");
            }
        }
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.err.println("Interrupted: " + e.getMessage());
        }
        determineWinner();
    }

    private void playerTurn(){
        int choice = 0;
        while (choice!= 2 && blackjack.calculateHand(playerHand)<21){
            System.out.println();
            System.out.print("Do you want to hit (enter 1) or stand (enter 2)? ");
            choice = scan.nextInt();
            if (choice == 1){
                playerHand.add(deck.removeFirst());
                System.out.println("Your hand: " + playerHand + " (Total: " + blackjack.calculateHand(playerHand) + ")");
                if (blackjack.calculateHand(playerHand)>21){
                    System.out.println("You busted!");
                }
            } else if (choice == 2){
            } else {
                System.out.print("Invalid choice try again: ");
                choice = scan.nextInt();
            }
        }
    }

    private void dealCards() {
        playerHand.add(deck.removeFirst());
        playerHand.add(deck.removeFirst());
        dealerHand.add(deck.removeFirst());
        dealerHand.add(deck.removeFirst());
    }

    private void dealerTurn(){
        System.out.println();
        System.out.println("Dealer's turn");
        System.out.println("Dealer hand: " + dealerHand + " (Total: " + blackjack.calculateHand(dealerHand) + ")");
        while(blackjack.calculateHand(dealerHand)<17){
            System.out.println();
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                System.err.println("Interrupted: " + e.getMessage());
            }
            dealerHand.add(deck.removeFirst());
            System.out.println("Dealer draws a card");
            System.out.println("Dealer hand: " + dealerHand + " (Total: " + blackjack.calculateHand(dealerHand) + ")");
        }
    }

    private void determineWinner(){
        int playerHandTotal = blackjack.calculateHand(playerHand);
        int dealerHandTotal = blackjack.calculateHand(dealerHand);
        System.out.println("______________________________________");
        System.out.println("Game Over");
        System.out.println("Your hand: " + playerHand + " (Total: " + playerHandTotal + ")");
        System.out.println("Dealer hand: " + dealerHand + " (Total: " + dealerHandTotal + ")");
        if (playerHandTotal>21 || (playerHandTotal<dealerHandTotal && dealerHandTotal<=21)){
            System.out.println("Dealer Wins");
            balance.subtract(betAmount);
            System.out.println("Your current balance: $" + String.format("%.2f", balance.getMoney()));
        } else if (dealerHandTotal==playerHandTotal){
            System.out.println("Tie");
            System.out.println("Your current balance: $" + String.format("%.2f", balance.getMoney()));
        } else{
            System.out.println("You Win");
            balance.add(betAmount);
            System.out.println("Your current balance: $" + String.format("%.2f", balance.getMoney()));
        }
    }
}