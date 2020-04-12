// tile which boosts player dexterity
public class BushTile extends BoostTile {
    public void addBoost(Hero h) {
        originalSkill = h.getDexterity();
        int boost = originalSkill;
        boost *= (1 + percentBoost);
        h.setDexterity(boost);
    }
    public void removeBoost(Hero h) {
        h.setDexterity(originalSkill);
    }

    public String getSymbol(){
        return ANSI_GREEN+"B"+ANSI_RESET;
    }
}