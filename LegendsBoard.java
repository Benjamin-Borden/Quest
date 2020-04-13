import java.util.Arrays;
import java.util.Random;

public class LegendsBoard extends Board{
    private final int NUM_LANES = 3;
    private final int LANE_WIDTH = 2;
    private final double PERCENT_BUSH = 0.1, PERCENT_CAVE = 0.1, PERCENT_KOULOU = 0.1;
    private int HERO_NEXUS_ROW;
    private int MONSTER_NEXUS_ROW = 0;
    private int[][] monsterLocs = {{-1,-1},{-1,-1},{-1,-1}};

    public LegendsBoard(Game g) {
        boardState = new Tile[8][8];
        boardHeight = 8;
        boardWidth = 8;
        HERO_NEXUS_ROW = boardHeight-1;
        game = g;
        generateBoardState();
        playerLocs = new int[][]{{0,0},{3,0},{6,0}};
    }
    private void generateBoardState(){
        Object[] temp = Input.flatten(new Item[][]{game.getPotions(),game.getArmor(),game.getWeapons(),game.getSpells()}).toArray();
        Item[] items = Arrays.copyOf(temp,temp.length,Item[].class);
        for (int row=0; row<boardHeight; row++) {
            for (int lane=0; lane<NUM_LANES; lane++) {
                for (int offset=0; offset<LANE_WIDTH; offset++) {
                    int col = lane*(LANE_WIDTH+1)+offset;
                    if (row==MONSTER_NEXUS_ROW) {
                        boardState[row][col] = new Nexus(items, "monster");
                    }else if (row==HERO_NEXUS_ROW) {
                        boardState[row][col] = new Nexus(items, "hero");
                    } else {
                        boardState[row][col] = getRandomTile();
                    }
                }
                // add nonaccessible tiles between lanes
                if (lane!=(NUM_LANES-1)) {
                    boardState[row][lane*(LANE_WIDTH+1)+LANE_WIDTH] = new BlockingTile();
                }
            }
        }
    }

    private <T extends BoostTile> T getRandomTile() {
        // returns random boostTile
        T ret;
        double perc = Math.random();
        if(perc<PERCENT_BUSH){
            ret = (T) new BushTile();
        }else if(perc<PERCENT_CAVE+PERCENT_BUSH){
            ret = (T) new CaveTile();
        }else if(perc<PERCENT_KOULOU+PERCENT_CAVE+PERCENT_BUSH) {
            ret = (T) new KoulouTile();
        }else{
            ret = (T) new PlainTile();
        }
        return ret;
    }

    public void teleport(int heroID) {
        boolean accepted = false;
        int col;
        int row;
        do {
            System.out.println("Which column would you like to teleport to? (from 0 to " + (boardWidth-1) + ")");
            col = Input.getInt(0,boardWidth-1);
            System.out.println("Which row would you like to teleport to? (from 0 to " + (boardHeight-1) + ")");
            row = Input.getInt(0,boardHeight-1);
            if (validTeleport(row, col, heroID)) { accepted=true;}
            else {System.out.println("Remember, you cannot teleport within your own lane, and you cannot teleport past the last monster in your target lane. Try again.");}
        } while (accepted == false);
        moveTo(row, col, heroID);
    }

    public void back(int heroID) {
        // moves hero back to the nexus
        // randomizes which nexus spot it is
        Random random = new Random();

        int randomCell = random.nextInt(LANE_WIDTH);
        int[] position = playerLocs[heroID];

        int nexusCell = ((getLane(position[0])-1)*(LANE_WIDTH+1)) + randomCell;
        System.out.println(""+HERO_NEXUS_ROW+nexusCell+heroID);
        moveTo(HERO_NEXUS_ROW, nexusCell, heroID);
    }

    public boolean validTeleport(int row, int col, int heroID) {
        boolean ret = true;
        int[] position = playerLocs[heroID];
        if (!validMove(row, col)){
            ret = false;
        }
        // can't teleport into same lane

        else if (getLane(position[1])==getLane(col)) {
            ret = false;
        }
        return ret;
    }

    public boolean validMove(int row, int col) {
        boolean ret = true;
        Tile moveToTile = boardState[row][col];
        if (!moveToTile.passable()) {ret = false;}
        // can't go past monster in lane
        else if (row<monsterLoc(getLane(col))) {ret = false;}
        return ret;

    }

    // start counting at 1, 2, etc.
    public int getLane(int col) {
        int ret = (col / (LANE_WIDTH+1)) + 1; // integer division
        return ret;
    }
    public int[][] getMonsterLocs() {
        return monsterLocs;
    }

    public void setMonsterLocs(int[][] monsterLocs) {
        this.monsterLocs = monsterLocs;
    }
    public void monsterActions(){
        for(int i =0;i< monsterLocs.length;i++){
            Monster m = boardState[monsterLocs[i][0]][monsterLocs[i][1]].getMonst();
            int heroH=-1, heroW =-1;
            if(boardState[monsterLocs[i][0]][monsterLocs[i][1]].hasHero()){
                heroH = monsterLocs[i][0];
                heroW = monsterLocs[i][1];
            }else if(monsterLocs[i][1]+1 != (NUM_LANES*LANE_WIDTH+(NUM_LANES-1)) && boardState[monsterLocs[i][0]][monsterLocs[i][1]+1].hasHero()){
                heroH = monsterLocs[i][0];
                heroW = monsterLocs[i][1]+1;
            }else if(monsterLocs[i][1] != 0 && boardState[monsterLocs[i][0]][monsterLocs[i][1]-1].hasHero()){
                heroH = monsterLocs[i][0];
                heroW = monsterLocs[i][1]-1;
            }else if(monsterLocs[i][0]+1 != (NUM_LANES*LANE_WIDTH+(NUM_LANES-1)) && boardState[monsterLocs[i][0]+1][monsterLocs[i][1]].hasHero()){
                heroH = monsterLocs[i][0]+1;
                heroW = monsterLocs[i][1];
            }
            if(heroH != -1){
                int oldHealth, newHealth;
                oldHealth = boardState[heroH][heroW].getHero().getCurrentHealth();
                boardState[heroH][heroW].getHero().receiveDamage(boardState[monsterLocs[i][0]][monsterLocs[i][1]].getMonst().regularDamage());
                newHealth = boardState[heroH][heroW].getHero().getCurrentHealth();
                System.out.println(boardState[monsterLocs[i][0]][monsterLocs[i][1]].getMonst().getName()+" attacked "+boardState[heroH][heroW].getHero().getName()+" for "+(-1*(newHealth-oldHealth))+" points of damage!\n"+boardState[heroH][heroW].getHero().getName()+" is at "+ boardState[heroH][heroW].getHero().getCurrentHealth()+" HP!");
            }else if(!boardState[monsterLocs[i][0]+1][monsterLocs[i][1]].occupied(m)){
                boardState[monsterLocs[i][0]+1][monsterLocs[i][1]].setMonst(boardState[monsterLocs[i][0]][monsterLocs[i][1]].getMonst());
                boardState[monsterLocs[i][0]][monsterLocs[i][1]].setMonst(null);
                monsterLocs[i][0]=monsterLocs[i][0]+1;
                if(boardState[monsterLocs[i][0]][monsterLocs[i][1]] instanceof Nexus){
                    System.out.println("THE MONSTERS HAVE WON, GAME OVER.");
                }
            }
        }
    }
    public int monsterLoc(int lane) {
        // given lane, returns row of last monster (closest to hero nexus)
        // if no monsters in lane, returns -1
        int ret = -1;
        for (int i=0; i<boardHeight; i++) {
            for (int j=0; j<LANE_WIDTH; j++) {
                if (boardState[i][j].hasMonster()) {ret = i;}
            }
        }
        return ret;
    }

    public String toString(){


        String output = "";
        for(int i = 0;i<boardState.length;i++){
            String out1 ="";
            String out2 = "";
            for(int o =0;o<boardState[i].length;o++){
                Tile t = boardState[i][o];
                out1+= t.getSymbol()+" - "+t.getSymbol()+" - "+t.getSymbol()+"  ";
            }
            for(int o = 0; o<boardState[i].length;o++){
                Tile t = boardState[i][o];
                if(!t.passable()){
                    out2+="| X X X |  ";
                }else{
                    out2+="| ";
                    if(t.hasHero()){
                        out2+=ANSI_YELLOW+"H"+(t.getHero().getHeroID()+1)+ANSI_RESET+" ";
                    }else{
                        out2+="   ";
                    }

                    if(t.hasMonster()){
                        out2+=ANSI_RED+"M"+(t.getMonst().getLane()+1)+ANSI_RESET+" ";
                    }else{
                        out2+="   ";
                    }
                    out2+="|  ";
                }

            }

            output+=out1+"\n"+out2+"\n"+out1+"\n\n";
        }
        return output;

    }

    public boolean monsterWin() {
        return checkWinFor("monster");
    }

    public boolean heroWin() {
        return checkWinFor("hero");
    }

    private boolean checkWinFor(String team) {
        if (!team.equals("hero") || !team.equals("monster")) {
            throw new IllegalArgumentException("Team must be either hero or monster.");
        }
        boolean ret = false;
        int row = HERO_NEXUS_ROW;
        int col;

        if (team.equals("hero")) {row = MONSTER_NEXUS_ROW;}
        else if (team.equals("monster")) { row = HERO_NEXUS_ROW; }
        
        for (int lane = 0; lane<NUM_LANES; lane++) {
            for (int offset=0; offset<LANE_WIDTH; offset++) {
                col = (lane*(LANE_WIDTH+1))+offset;
                Nexus curr;
                if(boardState[row][col] instanceof Nexus){
                    curr = (Nexus) boardState[row][col];
                    if (team.equals("hero")) {
                        if (curr.heroWin()) {ret = true;}
                    }
                    else if (team.equals("monster")){
                        if (curr.monsterWin()) {ret = true;}
                    }
                }


            }
        }
        return ret;
    }

    public void spawnMonsters() {
        Monster[] mons = new Monster[NUM_LANES];
        int monsterLevel = game.getPlayers()[0].getHighestLevel();
        int numMonsters = game.getPlayers()[0].getParty().length;
        Monster[] temp = game.monsters.clone();
        Random random = new Random();

        for(int i = 0;i<numMonsters;i++){
            for(int o = 0;o<temp.length;o++){
                if(temp[o] != null && temp[o].getLevel()==monsterLevel){

                    if(temp[o].getClass().toString().equals("class Exosleleton")){
                        mons[i] = new Exosleleton(temp[o].getName(),temp[o].getLevel(),temp[o].getDamage(),temp[o].getDefense(),temp[o].getDodge());
                    } else if(temp[o].getClass().toString().equals("class Spirit")){
                        mons[i] = new Spirit(temp[o].getName(),temp[o].getLevel(),temp[o].getDamage(),temp[o].getDefense(),temp[o].getDodge());
                    }else if(temp[o].getClass().toString().equals("class Dragon")){
                        mons[i] = new Dragon(temp[o].getName(),temp[o].getLevel(),temp[o].getDamage(),temp[o].getDefense(),temp[o].getDodge());
                    }else{
                        mons[i] = null;
                    }
                    temp[o]=null;
                    break;
                }
            }
        }
        int counter = 0;
        for(Monster m: mons){
            m.setLane(counter);
            monsterLocs[counter]=new int[]{MONSTER_NEXUS_ROW,random.nextInt(2)+(counter*(LANE_WIDTH+1))};
            boardState[monsterLocs[counter][0]][monsterLocs[counter][1]].setMonst(m);
            counter++;
        }
    }
}
