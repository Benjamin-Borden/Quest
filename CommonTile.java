public class CommonTile extends Tile {

    private Monster[] monsters;
    private final double CHANCE_OF_ENCOUNTER = 1;
    public CommonTile(Monster[] mons){
        setMonsters(mons);
    }
    public void steppedOn(Player p) {
        int monsterLevel = p.getHighestLevel();
        int numMonsters = p.getParty().length;
        Monster[] temp = monsters.clone();
        Monster[] monsterEncounter = new Monster[numMonsters];
        for(int i = 0;i<numMonsters;i++){
            for(int o = 0;o<temp.length;o++){
                if(temp[o] != null && temp[o].getLevel()==monsterLevel){
                    monsterEncounter[i] = temp[o];
                    temp[o]=null;
                    break;
                }
            }
        }
        if(Math.random()<CHANCE_OF_ENCOUNTER){

            for(Monster m: monsterEncounter)
                m.setCurrentHealth(m.getTotalHealth());
            Fight f = new Fight(p, monsterEncounter);

            f.instigate();
        }

    }

    @Override
    public String getSymbol() {
        return ANSI_GREEN+'Δ'+ANSI_RESET;
    }

    @Override
    public boolean passable() {
        return true;
    }

    public Monster[] getMonsters() {
        return monsters;
    }

    public void setMonsters(Monster[] monsters) {
        this.monsters = monsters;
    }
}
