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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void steppedOn(Player p){
        setHero(p.getParty()[p.getHeroTurn()]);
        if(heroWin()){

            p.setWinner(true);
        }else {
            System.out.println("Would "+p.getParty()[p.getHeroTurn()].getName()+" like to open the market?(y/n)");
            if(Character.toUpperCase(Input.getChar(new char[]{'Y','y','N','n'})) =='Y'){
                Player temp = new Player(new Hero[]{p.getParty()[p.getHeroTurn()]});
                super.steppedOn(temp);
            }

        }
    }

    public boolean heroWin() {
        boolean ret = false;
        if (team.equals("monster")) {
            if (this.hasHero()) {ret = true;}
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