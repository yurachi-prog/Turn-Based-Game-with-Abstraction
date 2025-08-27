package main_file;

public abstract class GameCharacter {

    protected String name;
    protected int hp;
    protected final int maxHp = 100;

    public GameCharacter(String name) {
        this.name = name;
        this.hp = maxHp;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public abstract String attack(GameCharacter opponent);

    public abstract String specialMove(GameCharacter opponent);

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public boolean isAlive() {
        return hp > 0;
    }
}