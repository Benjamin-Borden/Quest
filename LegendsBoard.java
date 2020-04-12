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
