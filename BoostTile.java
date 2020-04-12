// abstract class representing tile that boosts some hero skill

public abstract class BoostTile extends CommonTile {
    protected int percentBoost = 0.1; // meaning 10% boost
    protected int originalSkill; // saves original skill value for removeBoost()
    public abstract void addBoost(Hero h);
    public abstract void removeBoost(Hero h);
}