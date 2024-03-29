public class Player extends Entity {
    private int playerID;
    private int heroTurn;



    private boolean isWinner = false;

    private Hero[] party;


    public Player(String n, int id, Hero[] h) {
        super(n);
        setPlayerID(id);
        setParty(h); //set later
    }

    public Player(String n, Hero[] h){
        this(n,(int)Math.random()*100000,h);
    }
    public Player(Hero[] h){
        this("Guest",h);
    }

    public int getHeroTurn() {
        return heroTurn;
    }

    public void setHeroTurn(int heroTurn) {
        this.heroTurn = heroTurn;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public Hero[] getParty() {
        return party;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public int getHighestLevel(){
        int max = 0;
        for(Hero h: getParty()){
            if(h.getLevel()>max){
                max = h.getLevel();
            }
        }

        return max;
    }
    public void setParty(Hero[] party) {
        this.party = party;
    }
    public void displayParty(){
        int i = 1;
        for(Hero h: getParty()){
            System.out.println(""+(i++) +" - "+h+"\n");
        }
    }

    public void displayNames(){
        int i = 1;
        for(Hero h: getParty()){
            System.out.println(""+(i++) +" - "+h.getName()+"\n");
        }
    }
}
