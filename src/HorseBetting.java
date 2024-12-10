import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HorseBetting {

    private String[] horses = {"🟡🐎", "🔴🐎","🟢🐎","🔵🐎"};
    private Scanner scan;
    private Money balance;

    public HorseBetting(Scanner scan, Money balance) {
        this.scan = scan;
        this.balance = balance;
    }

    public void startHorseBetting() {
        String yellowHorse ="🟡🐎|                                 |";
        String redHorse =  "🔴🐎|                                 |";
        String greenHorse = "🟢🐎|                                 |";
        String blueHorse = "🔵🐎|                                 |";
        boolean winner = false;
        printQuestion(
                yellowHorse + "\n" + redHorse + "\n" + greenHorse + "\n" + blueHorse);
        System.out.println("Your current balance: $" + String.format("%.2f", balance.getMoney()));
        System.out.print("Enter your bet amount ($0 to skip betting): $");
        double bet = scan.nextDouble();
        bet = (int)(bet*100)/100.0;
        scan.nextLine();

        while (bet > balance.getMoney() || bet < 0) {
            System.out.println("Invalid value");
            System.out.print("Enter the amount of money you would like to bet: $");
            bet = scan.nextDouble();
            scan.nextLine();
        }
        yellowHorse = "|🟡🐎                                 |";
        redHorse = "|🔴🐎                                 |";
        greenHorse = "|🟢🐎                                 |";
        blueHorse = "|🔵🐎                                 |";


        printQuestion("Which horse do you want to bet r/g/b/y (red/green/blue/yellow)");
        String answer = scan.nextLine();
        if (answer.equals("r")) {
            answer = "Red";
        } else if (answer.equals("g")) {
            answer = "Green";
        } else if (answer.equals("b")) {
            answer = "Blue";
        } else if (answer.equals("y")) {
            answer = "Yellow";
        }
        int yellowHorseIndex = yellowHorse.indexOf("🐎");
        int redHorseIndex = redHorse.indexOf("🐎");
        int greenHorseIndex =greenHorse.indexOf("🐎");
        int blueHorseIndex = blueHorse.indexOf("🐎");

        while (!winner) {
            printQuestion(yellowHorse + "\n" + redHorse + "\n" + greenHorse + "\n" + blueHorse);
            String redHorsedistance = "";
            String yellowHorsedistance = "";
            String greenHorsedistance = "";
            String blueHorsedistance = "";

            int redHorseStep = (int) (Math.random() * 3);
            int yellowHorseStep = (int) (Math.random() * 3);
            int greenHorseStep = (int) (Math.random() * 3);
            int blueHorseStep = (int) (Math.random() * 3);

            for (int i=1; i<=yellowHorseStep; i++){
                yellowHorsedistance += " ";
            }
            for (int i=1; i<=redHorseStep; i++){
                redHorsedistance += " ";
            }
            for (int i=1; i<=greenHorseStep; i++){
                greenHorsedistance += " ";
            }
            for (int i=1; i<=blueHorseStep; i++){
                blueHorsedistance += " ";
            }
            if ((yellowHorse.indexOf("🐎") + 2 + yellowHorseStep) > yellowHorse.length()) {
                yellowHorse = "|                                  🟡🐎";
            } else {
                if (yellowHorseIndex == yellowHorse.length()-3) {
                    yellowHorse = "|                                  🟡🐎";
                } else {
                    yellowHorse = yellowHorse.substring(0,yellowHorse.indexOf("🟡"))  + yellowHorsedistance + "🟡🐎" + yellowHorse.substring(yellowHorse.indexOf("🐎") + 2 + yellowHorseStep);
                }
            }

            if ((redHorse.indexOf("🐎") + 2 + redHorseStep) > redHorse.length()) {
                redHorse = "|                                  🔴🐎";
            } else {
                if (redHorseIndex == redHorse.length() -3) {
                    redHorse = "|                                  🔴🐎";
                } else {
                    redHorse = redHorse.substring(0, redHorse.indexOf("🔴")) + redHorsedistance + "🔴🐎" + redHorse.substring(redHorse.indexOf("🐎") + 2 + redHorseStep);
                }
            }

            if ((blueHorse.indexOf("🐎") + 2 + blueHorseStep) > blueHorse.length()) {
                blueHorse = "|                                  🔵🐎";
            } else {
                if (blueHorseIndex == blueHorse.length()-3) {
                    blueHorse = "|                                  🔵🐎";
                } else {
                    blueHorse = blueHorse.substring(0,blueHorse.indexOf("🔵"))  +  blueHorsedistance + "🔵🐎" + blueHorse.substring(blueHorse.indexOf("🐎")+ 2 +blueHorseStep);
                }
            }
            if ((greenHorse.indexOf("🐎") + 2 + greenHorseStep) > greenHorse.length()) {
                greenHorse = "|                                  🟢🐎";
            } else {
                if (greenHorseIndex == greenHorse.length()-3) {
                    greenHorse = "|                                  🟢🐎";
                } else {
                    greenHorse = greenHorse.substring(0,greenHorse.indexOf("🟢"))  +  greenHorsedistance + "🟢🐎" + greenHorse.substring(greenHorse.indexOf("🐎")+ 2 + greenHorseStep);
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println("Interrupted: " + e.getMessage());
            }

            yellowHorseIndex = yellowHorse.indexOf("🐎");
            redHorseIndex = redHorse.indexOf("🐎");
            greenHorseIndex =greenHorse.indexOf("🐎");
            blueHorseIndex = blueHorse.indexOf("🐎");

            int[] indexsNumbers = {yellowHorseIndex,redHorseIndex,greenHorseIndex,blueHorseIndex};
            List<String> winners = new ArrayList<>();

            for (int i = 0; i < indexsNumbers.length; i++) {
                if (indexsNumbers[i] == yellowHorse.length()-2 || indexsNumbers[i] == yellowHorse.length()-1 ) {
                    switch (i) {
                        case 0:
                            winners.add("Yellow");
                            break;
                        case 1:
                            winners.add("Red");
                            break;
                        case 2:
                            winners.add("Green");
                            break;
                        case 3:
                            winners.add("Blue");
                            break;
                        default:
                            break;
                    }
                }
            }
            if (!winners.isEmpty()) {
                printQuestion(yellowHorse + "\n" + redHorse + "\n" + greenHorse + "\n" + blueHorse);
                switch (winners.size()) {
                    case 1:
                        System.out.println("The winner is " + winners.getFirst());
                        if (winners.getFirst().equals(answer)) {
                            System.out.println("Your " + answer + " horse won! you gained $" + (bet*3));
                            balance.add(bet*3);
                            System.out.println("Your current balance is : " + balance.getMoney());
                            break;
                        } else {
                            System.out.println("Better luck next time");
                            balance.subtract(bet);
                            System.out.println("Your current balance is : " + balance.getMoney());
                            break;
                        }
                    case 2:
                        System.out.println("The winner is " + winners.getFirst() + " and " + winners.getLast());
                        if (winners.getFirst().equals(answer) || winners.getLast().equals(answer)) {
                            System.out.println("Your " + answer + " horse tied! you gained $" + (bet));
                            balance.add(bet);
                            System.out.println("Your current balance is : " + balance.getMoney());
                            break;
                        } else {
                            System.out.println("Better luck next time");
                            balance.subtract(bet);
                            System.out.println("Your current balance is : " + balance.getMoney());
                            break;
                        }
                    case 3:
                        System.out.println("The winner is " + winners.getFirst() + " and " + winners.get(1) + " and " + winners.getLast());
                        if (winners.getFirst().equals(answer) || winners.getLast().equals(answer) || winners.get(1).equals(answer)) {
                            System.out.println("Your " + answer + " horse tied! you gained $" + (bet*0.5));
                            balance.add(bet*0.5);
                            System.out.println("Your current balance is : " + balance.getMoney());
                            break;
                        } else {
                            System.out.println("Better luck next time");
                            balance.subtract(bet);
                            System.out.println("Your current balance is : " + balance.getMoney());
                            break;
                        }
                    case 4:
                        System.out.println("Every horse won! \n no one profits from this!");
                        System.out.println("Your current balance is : " + balance.getMoney());
                        break;
                }
                break;
            }
        }
    }
    private void printQuestion(String question) {
        System.out.println("-------------------------------------------------------------------");
        System.out.println(question);
        System.out.println("-------------------------------------------------------------------");
    }
}