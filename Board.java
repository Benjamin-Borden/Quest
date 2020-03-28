

import java.util.Arrays;
//represents a game board

public class Board {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private final double PERCENT_MARKET = 0.3, PERCENT_OBSTRUCT = 0.2;
    //tiles will construct a valid game board
    protected Tile[][] boardState;

    //the number of tiles vertically
    protected int boardHeight;

    //the number of board length wise
    protected int boardWidth;

    //used to adjust the tile shape to fit numbers
    private int lengthOfTiles;

    private int[][] playerLocs;

    private Game game;
    //create a board of dynamic size
    public Board(int h, int w, Game g){
        boardHeight = h;
        boardWidth = w;
        boardState = new Tile[h][w];
        game = g;
        playerLocs = new int[g.getPlayers().length][2];
        Object[] temp = Input.flatten(new Item[][]{g.getPotions(),g.getArmor(),g.getWeapons(),g.getSpells()}).toArray();
        Item[] items = Arrays.copyOf(temp,temp.length,Item[].class);
        Monster[] monsters = g.getMonsters();
        for(int hi = 0; hi<boardHeight;hi++){
            for(int wid = 0; wid<boardWidth;wid++) {
                //boardState[hi][wid]=new Tile((hi*boardWidth)+(wid+1));
                double tileType = Math.random();
                if(tileType<PERCENT_OBSTRUCT){
                    boardState[hi][wid] = new BlockingTile();
                }else if(tileType<PERCENT_OBSTRUCT+PERCENT_MARKET){
                      //BenArrays.concatenate(g.getArmor(),g.getPotions());
                    boardState[hi][wid] = new Market(items);
                }else{
                    boardState[hi][wid] = new CommonTile(monsters);
                }


            }
        }
        lengthOfTiles = Integer.toString(boardHeight*boardWidth).length();

        for(int i = 0;i<playerLocs.length;i++){
            int h1;
            int w1;
            do{
                h1 = (int)(Math.random()*10000)%boardHeight;
                w1 = (int)(Math.random()*10000)%boardWidth;
            }while(!boardState[h1][w1].passable()); //this needs to be edited if more players are added
            playerLocs[i][0] = h1;
            playerLocs[i][1] = w1;

        }
    }


    //represent the board
    public String toString(){

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
        return output;

    }
    public void move(int offseth, int offsetw){
        moveTo(playerLocs[0][0]+offseth,playerLocs[0][1]+offsetw);
    }
    private boolean moveTo(int h,int w){
        if(h<0 || w<0 || h>boardHeight-1 || w> boardWidth-1 || !boardState[h][w].passable()){
            System.out.println("You can not move here.");
            return false;
        }
        playerLocs[0][0]=h;
        playerLocs[0][1]=w;
        //System.out.println(this);
        boardState[h][w].steppedOn(game.getPlayers()[0]);
        return true;
    }
    //check if a string a number
    public boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }


}