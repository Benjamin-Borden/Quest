import java.util.Random;

public class LegendsBoard extends Board{

    private final int NUM_LANES = 3;
    private final int LANE_WIDTH = 2;

    public LegendsBoard(Game g) {
        boardState = new Tile[8][8];
        for(int i=0;i<8;i++){
            if(i==0||i==7){
                boardState[i]= new Tile[]{new BlockingTile(),new BlockingTile(),new BlockingTile(),new BlockingTile(),new BlockingTile(),new BlockingTile(),new BlockingTile(),new BlockingTile()};
            }else{
                boardState[i]= new Tile[]{new BlockingTile(),new BlockingTile(),new BlockingTile(),new BlockingTile(),new BlockingTile(),new BlockingTile(),new BlockingTile(),new BlockingTile()};
            }
        }
    }

    public void teleport(int heroID) {
        // asks player where they want hero to teleport to and moves them there if valid
        // todo: complete me (with validTeleport() function)
    }

    public void back(int heroID) {
        // moves hero back to the nexus
        // randomizes which nexus spot it is
        Random random = new Random();
        int randomCell = random.nextInt(LANE_WIDTH);
        int position = playerLocs[heroID];
        int nexusCell = ((getLane(position[1])-1)*NUM_LANES) + randomCell;
        moveTo(boardHeight-1, nexusCell, heroID);
    }

    public boolean validTeleport(int row, int col, int heroID) {
        boolean ret = true;
        if !validMove(row, col) {
            ret = false;
        }
        // can't teleport into same lane
        int[] position = playerLocs[heroID];
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

    public int getLane(int col) {
        int ret = (col / 3) + 1; // integer division
        return ret;
    }

    public int monsterLoc(int lane) {
        // given lane, returns row of last monster (closest to hero nexus)
        // if no monsters in lane, returns -1
        int ret = -1
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
                        out2+="H"+t.getHero().getHeroID()+" ";
                    }else{
                        out2+="   ";
                    }

                    if(t.hasMonster()){
                        out2+="M"+t.getMonst().getMonstInt()+" ";
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

}
