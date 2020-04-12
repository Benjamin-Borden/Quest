// tile which boosts player agility
public class CaveTile extends BoostTile {
    public void addBoost(Hero h) {
        originalSkill = h.getAgility();
        int boost = originalSkill;
        boost *= (1 + percentBoost);
        h.setAgility(boost);
    }
    public void removeBoost(Hero h) {
        h.setAgility(originalSkill);
    }
}