// abstract class representing tile that boosts some hero skill

public abstract class BoostTile extends CommonTile {
    protected double percentBoost = 0.1; // meaning 10% boost
    protected int originalSkill; // saves original skill value for removeBoost()
    public BoostTile(){
        super();

    }
    public BoostTile(Monster[] mons) {
        super(mons);
    }

    public abstract void addBoost(Hero h);
    public abstract void removeBoost(Hero h);
}