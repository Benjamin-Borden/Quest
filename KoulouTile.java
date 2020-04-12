// tile which boosts player strength
public class KoulouTile extends BoostTile {
    public KoulouTile(Monster[] mons) {
        super(mons);
    }
    public KoulouTile(){
        super();

    }
    public void addBoost(Hero h) {
        originalSkill = h.getStrength();
        int boost = originalSkill;
        boost *= (1 + percentBoost);
        h.setStrength(boost);
    }
    public void removeBoost(Hero h) {
        h.setStrength(originalSkill);
    }

    @Override
    public String getSymbol() {
        return ANSI_CYAN+"K"+ANSI_RESET;
    }
}