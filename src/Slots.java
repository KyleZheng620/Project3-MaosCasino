import java.util.Scanner;

public class Slots {
    private Scanner scan;
    private String[] slot = {"🍋", "🍇", "🍒", "🍍","🍎","🍉"};
    private Money balance;


    public Slots(Scanner scan, Money balance) {
        this.scan = scan;
        this.balance = balance;
    }

    public void startSlotMachine() {
        System.out.println("Your current balance: $" + String.format("%.2f", balance.getMoney()));
        printQuestion("Enter the amount of money you would like to put into the machine: ");
        double money = scan.nextDouble();
        money = (int)(money*100)/100.0;
        scan.nextLine();
        while (money > balance.getMoney() || money < 0) {
            System.out.println("Invalid value");
            printQuestion("Enter the amount of money you would like to put into the machine: ");
            money = scan.nextDouble();
            scan.nextLine();
        }
        balance.subtract(money);
        while (money>0) {
            printQuestion("Your current balance in the machine: " + money  + "\nHow much money do you want to spend? Enter -1 if you want to stop");
            double bet = scan.nextDouble();
            scan.nextLine();
            if (bet == -1) {
                break;
            }
            bet = (int)(bet*100)/100.0;
            while (bet > money || bet <= 0) {
                System.out.println("Invalid value");
                printQuestion("Enter the amount of money you would like to bet: ");
                bet = scan.nextDouble();
                scan.nextLine();
            }
            money -= bet;
            for (int i = 1; i <= 20; i++) {
                System.out.println("-------------");
                System.out.println(slot[(int)(Math.random()*6)] + " | "  + slot[(int)(Math.random()*6)] + " | " + slot[(int)(Math.random()*6)]);
                System.out.println("-------------");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    System.err.println("Interrupted: " + e.getMessage());
                }
            }
            String fruit1 = slot[(int)(Math.random()*6)];
            String fruit2 = slot[(int)(Math.random()*6)];
            String fruit3 = slot[(int)(Math.random()*6)];
            System.out.println("-------------");
            System.out.println(fruit1 + " | "  + fruit2 + " | " + fruit3);
            System.out.println("-------------");

            if (fruit1.equals(fruit2) && fruit1.equals(fruit3)) {
                System.out.println("Jackpot!");
                money += (int)(bet*3*100)/100.0;
                System.out.println("You won $" + (int)(bet*3*100)/100.0);
            } else if (fruit1.equals(fruit2) || fruit2.equals(fruit3) || fruit1.equals(fruit3)) {
                System.out.println("You only got 2 fruits");
                money += (int)(bet*0.5*100)/100.0;
                System.out.println("You won $" + (int)(bet*0.5*100)/100.0);
            } else {
                System.out.println("You lost $" + bet);
                System.out.println("Better luck next time");
            }
        }
        balance.add(money);
    }
    private void printQuestion(String question) {
        System.out.println("-------------------------------------------------------------------");
        System.out.println(question);
        System.out.println("-------------------------------------------------------------------");
    }
}