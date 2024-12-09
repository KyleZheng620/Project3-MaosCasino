import java.util.Random;

public class Colosseum {
    private Random rng = new Random();

    public double calculateOdds(Gladiator gladiator1, Gladiator gladiator2) {
        // Simplified odds calculation based on total stats
        double totalStats = gladiator1.getStrength() + gladiator1.getAgility() + gladiator1.getArmor() +
                gladiator2.getStrength() + gladiator2.getAgility() + gladiator2.getArmor();
        return (double) (gladiator1.getStrength() + gladiator1.getAgility() + gladiator1.getArmor()) / totalStats;
    }

    public boolean simulateFight(Gladiator gladiator1, Gladiator gladiator2) {
        while (gladiator1.getHealth() > 0 && gladiator2.getHealth() > 0) {
            // Gladiator 1 attacks
            int attackRoll = rng.nextInt(gladiator1.getAgility()) + 1;
            int defenseRoll = rng.nextInt(gladiator2.getAgility()) + 1;
            if (attackRoll > defenseRoll) {
                int damage = rng.nextInt(gladiator1.getStrength()) + 1;
                gladiator2.takeDamage(damage);
                System.out.printf("%s hits %s for %d damage!\n", gladiator1.getName(), gladiator2.getName(), damage);
            } else {
                System.out.printf("%s misses %s!\n", gladiator1.getName(), gladiator2.getName());
            }

            // Check if fight is over
            if (gladiator2.getHealth() <= 0) break;

            // Gladiator 2 attacks
            attackRoll = rng.nextInt(gladiator2.getAgility()) + 1;
            defenseRoll = rng.nextInt(gladiator1.getAgility()) + 1;
            if (attackRoll > defenseRoll) {
                int damage = rng.nextInt(gladiator2.getStrength()) + 1;
                gladiator1.takeDamage(damage);
                System.out.printf("%s hits %s for %d damage!\n", gladiator2.getName(), gladiator1.getName(), damage);
            } else {
                System.out.printf("%s misses %s!\n", gladiator2.getName(), gladiator1.getName());
            }
        }

        return gladiator1.getHealth() > 0;
    }
}