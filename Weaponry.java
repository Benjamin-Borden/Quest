public class Weaponry extends Item{

    private int damage, requiredHands;
    public Weaponry(String n, int c, int r, int dam, int h) {
        super(n, c, r);
        setDamage(dam);
        setRequiredHands(h);
    }

    public int getDamage(){return damage;}

    public int getRequiredHands(){return requiredHands;}

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setRequiredHands(int requiredHands) {
        this.requiredHands = requiredHands;
    }
}
