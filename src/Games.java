import java.util.Scanner;

public class Games {
    Money balance;
    Scanner scan;
    Blackjack blackjack;
    ColosseumGladiatorGame colosseumGame;
    Slots slots;
    HorseBetting horseBetting;

    public Games(){
        balance = new Money();
        scan = new Scanner(System.in);
        blackjack = new Blackjack(scan, balance);
        slots = new Slots(scan, balance);
        colosseumGame = new ColosseumGladiatorGame(scan, balance);
        horseBetting = new HorseBetting(scan, balance);
    }

    public void startGames(){
        System.out.println("Welcome to Mao's Casino. You have a starting balance of $1000.0 dollars.");
        int choice = 0;
        while (balance.getMoney()> 0 && choice!=-1){
            System.out.println();
            System.out.println("---------------------------------------");
            System.out.println("Choose to play \n1.) Blackjack \n2.) Slot Machines \n3.) Colosseum Betting \n4.) Horse Betting \n(-1 to quit)");
            choice = scan.nextInt();
            if (choice == 1){
                System.out.println("Welcome to Blackjack");
                blackjack.startBlackjack();
            }
            else if (choice == 2){
                System.out.println("Welcome to slots");
                slots.startSlotMachine();
            }
            else if (choice == 3){
                System.out.println("Welcome to the Colosseum");
                colosseumGame.startColosseum();
            }
            else if (choice == 4){
                System.out.println("Welcome to horse betting");
                horseBetting.startHorseBetting();
            }
            else if (choice == -1){
                System.out.println("We hope you had fun at Mao's Casino");
                System.out.println("You left with $" + balance.getMoney());
            } else {
                System.out.println("Invalid Choice");
                System.out.println("Choose to play \n1.) Blackjack \n2.) Slot Machines \n3.) Colosseum Betting \n4.) Horse Betting \n(-1 to quit)");
                choice = scan.nextInt();
                scan.nextLine();
            }
        }
        if (balance.getMoney()<= 0){
            System.out.println("The money has been given to the CCP. You went bankrupt :(. ");
        }
    }
}
