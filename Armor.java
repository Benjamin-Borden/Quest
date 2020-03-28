public class Armor extends Item {

    private int damageReduction;

    public Armor(String n, int c, int r, int dam) {
        super(n, c, r);
        setDamageReduction(dam);
    }

    public int getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(int damageReduction) {
        this.damageReduction = damageReduction;
    }
}
