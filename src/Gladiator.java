public class Gladiator {
    private String name;
    private int health;
    private int strength;
    private int agility;
    private int armor;

    public Gladiator(String name, int health, int strength, int agility, int armor) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.armor = armor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getAgility() {
        return agility;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public void takeDamage(int damage) {
        int effectiveDamage = Math.max(0, damage - armor);
        health = Math.max(0, health - effectiveDamage);
    }

    @Override
    public String toString() {
        return String.format("%s (Health: %d)", name, health);
    }
}
