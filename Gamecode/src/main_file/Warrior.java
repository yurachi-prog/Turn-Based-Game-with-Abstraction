package main_file;

public class Warrior extends GameCharacter {

    private static final int ATTACK_DAMAGE = 20;
    private static final int SPECIAL_DAMAGE = 40;

    public Warrior(String name) {
        super(name);
    }

    @Override
    public String attack(GameCharacter opponent) {
        opponent.takeDamage(ATTACK_DAMAGE);
        return name + " attacks " + opponent.getName() + " for " + ATTACK_DAMAGE + " damage.";
    }

    @Override
    public String specialMove(GameCharacter opponent) {
        opponent.takeDamage(SPECIAL_DAMAGE);
        return name + " uses powerful Sword Slash on " + opponent.getName() + " for " + SPECIAL_DAMAGE + " damage.";
    }
}