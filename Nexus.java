import java.util.Random;

public class Nexus extends Market {
    private String team; // can be hero or monster nexus

    public Nexus(Item[] inv, String team) {
        super(inv);
        try {
            if (!team.equals("hero")&&!team.equals("monster")) {throw new IllegalArgumentException("Team must be hero or monster.");}
            this.team = team;
        }
        catch(Exception e) {
            System.out.println("Team must be either hero or monster.");
        }
        this.team = team;
    }

    public boolean monsterWin() {
        boolean ret = false;
        if (team.equals("hero")) {
            if (this.hasMonster()) {ret = true;}
        }
        return ret;
    }

    public boolean heroWin() {
        boolean ret = false;
        if (team.equals("monster")) {
            if (this.hasMonster()) {ret = true;}
        }
        return ret;
    }

    public void spawnMonster(Monster[] monsters) {
        if (!this.hasMonster()) {
            Random random = new Random();
            int randomCell = random.nextInt(monsters.length);
            this.setMonst(monsters[randomCell]);
        }
    }

    public void spawnHero(Hero h) {
        if (!this.hasHero()) {
            this.setHero(h);
        }
    }

    @Override
    public String getSymbol() {
        return ANSI_BLUE+'N'+ANSI_RESET;
    }
}