import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class LegendsGame extends Game<LegendsBoard> {
    static final int ROUNDS_BETWEEN_MONSTER_SPAWNS = 8;


    public LegendsGame() throws FileNotFoundException {
        super();
    }

    @Override
    protected void startGame() {
        players = createPlayer();
        setPlayers(players);

        LegendsBoard board = new LegendsBoard(this);

        players[0].setHeroTurn(0);
        board.back(players[0].getHeroTurn());
        players[0].setHeroTurn(1);
        board.back(players[0].getHeroTurn());
        players[0].setHeroTurn(2);
        board.back(players[0].getHeroTurn());

        System.out.println("Welcome to Quest of Legends! In Quest of Legends you are trying to prevent the enemy monsters from reach your nexus!");
        System.out.println("You win by reaching the opposing nexus! You may fight the monster, drink potions, equip armor, or buy things from the nexus.");
        System.out.println();
        System.out.println("Use wasd to move around! the i key will open your inventory and e will let you drink a potion or equip a weapon or armor!");
        System.out.println("Red I's are unpassable, white P's are plain tiles, purple C's are caves which boost your agility, green B's are bushes which boost\nyour dexterity, and turquoise K's are koulous which boost your strength!");
        System.out.println("Your heroes are the gold H!");
        System.out.println();
        System.out.println("| o __   | o __   | o __ ");
		System.out.println("+/|-)_)  +/|-)_)  +/|-)_)");
		System.out.println(" / \\      / \\      / \\   ");
        System.out.println();

        int roundCounter = ROUNDS_BETWEEN_MONSTER_SPAWNS;
        boolean continuePlaying = true;
        do{
            if(roundCounter++%ROUNDS_BETWEEN_MONSTER_SPAWNS==0) {
                board.spawnMonsters();
                System.out.println(board);
                System.out.println();
            }
            board.monsterActions();
            boolean monstWin = board.checkWinFor("monster");

            // iterate through each hero in party for actions
            for(int i=0; i<players[0].getParty().length; i++) {
                players[0].setHeroTurn(i);
                System.out.println(board);
                boolean validAction = false;
                System.out.println("What would " + players[0].getParty()[i].getName() + " like to do?");
                System.out.println("(WASD for movement, E for interacting with inventory, I for hero information, T to teleport to a different lane, or B to return back to the nexus.)");
                boolean monsterCloseBy = board.monsterCloseBy(i);
                char input;
                if(monsterCloseBy){
                    System.out.println("There's a monster close to you! You can attack it!\nYou can enter 1 for a regular attack, or 2 to cast a spell!");
                    input = Input.getChar(new char[]{'W','w','A','a','S','s','D','d','I','i','Q','q','e','E', 't', 'T', 'b', 'B','1','2'});
                }else{
                    input = Input.getChar(new char[]{'W','w','A','a','S','s','D','d','I','i','Q','q','e','E', 't', 'T', 'b', 'B'});
                }

                if(Character.toUpperCase(input)=='Q'){
                    continuePlaying = false;
                    validAction = true;
                    break;
                }else if(Character.toUpperCase(input)=='I'){
                    players[0].displayParty();
                    validAction = false;
                }else if(Character.toUpperCase(input)=='W'){
                    validAction = board.move(-1,0,i);
                }else if(Character.toUpperCase(input)=='A'){
                    validAction = board.move(0,-1,i);
                }else if(Character.toUpperCase(input)=='S'){
                    validAction = board.move(1,0,i);
                }else if(Character.toUpperCase(input)=='D'){
                    validAction = board.move(0,1,i);
                }else if(Character.toUpperCase(input)=='E'){
                    validAction = equipOrDrink(i);
                }else if(Character.toUpperCase(input)=='T') {
                    validAction = board.teleport(i);
                }else if(Character.toUpperCase(input)=='B') {
                    validAction = board.back(i);
                }else if(Character.toUpperCase(input)=='1') {
                    board.attack(i);
                    validAction = true;
                }else if(Character.toUpperCase(input)=='2') {
                    board.spellAttack(i);
                    validAction = true;
                }
                if(players[0].isWinner()){
                    System.out.println(board);
                    System.out.println("CONGRATULATIONS! YOU HAVE WON QUEST OF LEGENDS");
                    System.out.println();
                    printEnd("win");
                    continuePlaying= false;
                    break;
                }else{
                    if(!validAction){
                        i--;
                    }
                }
            }
            if(monstWin){
                System.out.println("THE MONSTERS HAVE WON, GAME OVER.");
                System.out.println();
                printEnd("loss");
                continuePlaying = false;
            }else{
                Fight.regenerateHeroes(players[0].getParty());
            }


        }while(continuePlaying);
    }


    // overload method
    public boolean equipOrDrink(int hero){
        System.out.println("Do you want to equip something or drink a potion? (e/d)");
        char input2 = Input.getChar(new char[]{'d','D','e','E'});
        if(Character.toUpperCase(input2)=='D'){
            return drinkPotion((players[0].getParty()[hero]));
        }else{
            return equipWeaponOrArmor((players[0].getParty()[hero]));
        }
    }


    public static void printEnd(String winOrLoss) {
        System.out.println("        ,     \\    /      ,        ");
        System.out.println("       / \\    )\\__/(     / \\       ");
        System.out.println("      /   \\  (_\\  /_)   /   \\      ");
        System.out.println(" ____/_____\\__\\@  @/___/_____\\____ ");
        System.out.println("|             |\\../|              |");
        System.out.println("|              \\VV/               |");
        if (winOrLoss.equals("win")) { System.out.println("|          Y O U  W O N !         |");}
        else {System.out.println("|          Y O U  L O S T         |");}
        System.out.println("|_________________________________|");
        System.out.println(" |    /\\ /      \\\\       \\ /\\    | ");
        System.out.println(" |  /   V        ))       V   \\  | ");
        System.out.println(" |/     `       //        '     \\| ");
        System.out.println(" `              V                '");
    }
}