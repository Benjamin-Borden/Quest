// tile which boosts player strength
public class KoulouTile extends BoostTile {
    public void addBoost(Hero h) {
        originalSkill = h.getStrength();
        int boost = originalSkill;
        boost *= (1 + percentBoost);
        h.setStrengh(boost);
    }
    public void removeBoost(Hero h) {
        h.setStrengh(originalSkill);
    }
}