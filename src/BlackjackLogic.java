import java.util.ArrayList;

public class BlackjackLogic{

    public BlackjackLogic(){}

    /**
     * Initializes a deck of cards
     *
     * @return ArrayList with all 52 cards of the deck
     */

    public ArrayList<String> initializeDeck(){
        //all suits in a deck of cards
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        //all ranks in a deck of cards
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        ArrayList<String> deck = new ArrayList<>();
        for (String i:suits){
            for (String j:ranks){
                //adds them all to an arraylist in the format [rank] of [suit] (ex. 5 of diamonds)
                deck.add(j + " of " + i);
            }
        }
        return deck;
    }

    /**
     *
     * @param hand Either the dealer's hand or the player's hand
     * @return The total value of the hand
     */

    public int calculateHand(ArrayList<String> hand){
        int total = 0;
        int aces = 0;
        for (String card: hand){
            String rank = card.split (" ")[0];
            //takes the first word of the card
            //if the first word is Jack, Queen, or King, it adds 10 to the total
            //if the first word is Ace it adds 11 to the total
            //otherwise it adds the number to the total
            if ("Jack Queen King".contains(rank)){
                total+=10;
            } else if (rank.equals("Ace")){
                total+=11;
                aces++;
            } else {
                total+=Integer.parseInt(rank);
            }
        }
        //if the hand has an ace and is over 21, it will convert the value of the ace to 1
        while (total>21 && aces > 0){
            total-=10;
            aces--;
        }
        return total;
    }
}
