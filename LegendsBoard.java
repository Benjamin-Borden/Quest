public class LegendsBoard extends Board{

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

    public String toString(){
        /*
        String output = "";
        String size ="";
        String sizeSpace = "";
        for (int i = 0;i<lengthOfTiles+1;i++){
            size+="-";
            sizeSpace+=" ";
        }
        System.out.println();
        for(int o = 0;o<=boardState.length; o++){
            if(o == boardState.length){
                output+="+";
            }else{
                output += "+";
                for (int i = 0; i < boardState[0].length; i++) {
                    output += size+"+";
                }
                output += "\n";
                output+="|";
            }

            for(int i = 0;i<boardState[0].length;i++){
                if(o == boardState.length){
                    output+=size+"+";
                }else{
                    String out;
                    if(o==playerLocs[0][0]&&i==playerLocs[0][1]){//change if you want multiple players
                        out = ANSI_YELLOW+'H'+ANSI_RESET;
                    }else{
                        out = boardState[o][i].getSymbol();
                    }
                    output+=" "+out+sizeSpace.substring(0,lengthOfTiles-1)+"|";
                }
            }
            output+="\n";

        }
        return output;*/

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
                    if(t.hasPlayer()){
                        out2+="H"+t.getPlay().getPlayerID()+" ";
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
