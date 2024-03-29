public abstract class Tile {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private Hero hero = null;
    private Monster monst = null;

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Monster getMonst() {
        return monst;
    }

    public void setMonst(Monster monst) {
        this.monst = monst;
    }

    public boolean hasHero(){
        return hero != null;
    }

    public boolean hasMonster(){
        return monst != null;
    }

    public boolean occupied(LivingEntity e){
        if(e instanceof Hero){
            return hasHero();
        }else if(e instanceof Monster){
            return hasMonster();
        }
        return false;
    }
    public abstract boolean passable();

    public void steppedOn(Player p){};

    public void steppedOff(Player p){
        setHero(null);
    }
    public abstract String getSymbol();


}
