import java.io.FileNotFoundException;

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
        System.out.println(board);

        System.out.println("Use wasd to move around! the i key will open your inventory and e will let you drink a potion or equip a weapon or armor!");
        System.out.println("Red I's are unpassable, Green triangles are common tiles where you might meet monsters, and blue M's are markets where you can buy things");
        System.out.println("Your heroes are the gold H!");

        int roundCounter = ROUNDS_BETWEEN_MONSTER_SPAWNS;
        boolean continuePlaying = true;
        do{
            if(roundCounter++%ROUNDS_BETWEEN_MONSTER_SPAWNS==0)
                board.spawnMonsters();
            board.monsterActions();

            // iterate through each hero in party for actions
            for(int i=0; i<players[0].getParty().length; i++) {
                players[0].setHeroTurn(i);
                System.out.println(board);

                char input = getHeroAction(i);
                // don't consume an action when player asks for input
                while(Character.toUpperCase(input)=='I'){
                    players[0].displayParty();
                    input = getHeroAction(i);
                }
                if(Character.toUpperCase(input)=='Q'){
                    continuePlaying = false;
                    break;
                }else if(Character.toUpperCase(input)=='W'){
                    board.move(-1,0,i);
                }else if(Character.toUpperCase(input)=='A'){
                    board.move(0,-1,i);
                }else if(Character.toUpperCase(input)=='S'){
                    board.move(1,0,i);
                }else if(Character.toUpperCase(input)=='D'){
                    board.move(0,1,i);
                }else if(Character.toUpperCase(input)=='E'){
                    equipOrDrink(i);
                }else if(Character.toUpperCase(input)=='T') {
                    board.teleport(i);
                }else if(Character.toUpperCase(input)=='B') {
                    board.back(i);
                }else if(Character.toUpperCase(input)=='1') {
                    board.attack(i);
                }else if(Character.toUpperCase(input)=='2') {
                    board.spellAttack(i);
                }
                if(players[0].isWinner()){
                    System.out.println(board);
                    System.out.println("CONGRATULATIONS! YOU HAVE WON QUEST OF LEGENDS");
                    continuePlaying= false;
                    break;
                }
            }

        }while(continuePlaying);
    }

    private char getHeroAction(int i) {
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
        return input;
    }

    // overload method
    public void equipOrDrink(int hero){
        System.out.println("Do you want to equip something or drink a potion? (e/d)");
        char input2 = Input.getChar(new char[]{'d','D','e','E'});
        if(Character.toUpperCase(input2)=='D'){
            drinkPotion((players[0].getParty()[hero]));
        }else{
            equipWeaponOrArmor((players[0].getParty()[hero]));
        }
    }
}