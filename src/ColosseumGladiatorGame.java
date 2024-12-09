
import java.util.Random;
import java.util.Scanner;


public class ColosseumGladiatorGame {
    private Scanner scan;
    private Colosseum arena = new Colosseum();
    private Random rng = new Random();
    private Money balance;

    public ColosseumGladiatorGame(Scanner scan, Money balance){
        this.scan = scan;
        this.balance = balance;
    }

    public void startColosseum() {
        String[] names = {"Marcus", "Lucius", "Titus", "Julian", "Cassius", "Gaius", "Rufus", "Varius"};
        Gladiator gladiator1 = new Gladiator(getRandomName(names), getRandomStat(), getRandomStat(), getRandomStat(), getRandomStat());
        Gladiator gladiator2 = new Gladiator(getRandomName(names), getRandomStat(), getRandomStat(), getRandomStat(), getRandomStat());

        System.out.println("Your current balance: $" + String.format("%.2f", balance.getMoney()));
        System.out.println("Today's match:");
        System.out.println(gladiator1.toString());
        System.out.printf("Strength: %d, Agility: %d, Armor: %d\n", gladiator1.getStrength(), gladiator1.getAgility(), gladiator1.getArmor());
        System.out.println(gladiator2.toString());
        System.out.printf("Strength: %d, Agility: %d, Armor: %d\n", gladiator2.getStrength(), gladiator2.getAgility(), gladiator2.getArmor());
        System.out.printf("\n%.2f odds on %s\n", arena.calculateOdds(gladiator1, gladiator2), gladiator1.getName());
        System.out.printf("%.2f odds on %s\n", arena.calculateOdds(gladiator2, gladiator1), gladiator2.getName());


        System.out.print("\nEnter your bet (1 for first gladiator, 2 for second gladiator): ");
        int choice = scan.nextInt();
        scan.nextLine();


        if (choice != -1) {
            System.out.print("Enter your bet amount ($0 to skip betting): $");
            double betAmount = Double.parseDouble(scan.nextLine());
            while (betAmount > 0 && betAmount > balance.getMoney()){
                System.out.println("Insufficient funds. Your maximum bet is $" + String.format("%.2f", balance.getMoney()));
                System.out.print("Enter your bet amount ($0 to skip betting): $");
                betAmount = Double.parseDouble(scan.nextLine());
            }


            System.out.println("\nThe crowd cheers as the gates open...");
            boolean winner = arena.simulateFight(gladiator1, gladiator2);
            double payout = winner ? arena.calculateOdds(gladiator1, gladiator2) : arena.calculateOdds(gladiator2, gladiator1);


            System.out.println("\nThe battle ends. The winner is: " + (winner ? gladiator1.getName() : gladiator2.getName()));


            if ((winner && choice == 1) || (!winner && choice == 2)) {
                System.out.println("Congratulations, you won!");
                balance.add((int) (100 * betAmount * payout)/100.0);
                System.out.printf("Your payout is $%.2f\n", betAmount * payout);
                System.out.println("Your current balance is : " + balance.getMoney());
            } else {
                System.out.println("Sorry, you lost.");
                balance.subtract(betAmount);
                System.out.println("Your current balance is : " + balance.getMoney());
            }
        }


        System.out.println("\nFinal standings:");
        System.out.println(gladiator1.toString());
        System.out.println(gladiator2.toString());
    }


    private String getRandomName(String[] names) {
        return names[rng.nextInt(names.length)];
    }

    private int getRandomStat() {
        return rng.nextInt(50) + 20; // Random stat between 20 and 70
    }
}
