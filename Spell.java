public class Spell extends Item {

    private int damage, manaCost;
    private String type;

    public Spell(String n, int c, int r, int dam, int man, String typ) {
        super(n, c, r);
        setDamage(dam);
        setManaCost(man);
        setType(typ);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
