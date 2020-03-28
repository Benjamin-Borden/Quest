import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private Hero[] heroes;
    private Monster[] monsters;
    private Weaponry[] weapons;
    private Potion[] potions;
    private Armor[] armor;
    private Spell[] spells;
    private Player[] players;
    private Board board;

    public Game() throws FileNotFoundException {
        importData();
        startGame();
    }

    private void startGame() {
        players = createPlayer();
        setPlayers(players);
        Board board = new Board(8,8,this);



        boolean continuePlaying = true;
        do{
            System.out.println(board);
            char input = Input.getChar(new char[]{'W','w','A','a','S','s','D','d','I','i','Q','q'});

            if(Character.toUpperCase(input)=='Q'){
                continuePlaying = false;
                break;
            }else if(Character.toUpperCase(input)=='I'){
                displayEntities(heroes);
            }else if(Character.toUpperCase(input)=='W'){
                board.move(-1,0);
            }else if(Character.toUpperCase(input)=='A'){
                board.move(0,-1);
            }else if(Character.toUpperCase(input)=='S'){
                board.move(1,0);
            }else if(Character.toUpperCase(input)=='D'){
                board.move(0,1);
            }
            //System.out.println(board);
        }while(continuePlaying);
    }

    private Player[] createPlayer() {
        System.out.println( "Hello Adventurer, and welcome to Quest! In this game you can travel a map, battle monsters, \n" +
                            "go to markets, and level up your character. First, you must chose a group of heroes to lead!\n");
        System.out.println("What is your name? (No spaces)");
        String name = Input.getString();
        ArrayList<Hero> temp = new ArrayList<Hero>();
        boolean done = false;
        int counter = 0;
        while(!done){
            int i = 1;
            for(Hero h:heroes){
                if(h != null){
                    System.out.println(i +": "+ h.getName() +" "+ h.getClass().toString().split(" ")[1]);
                }
                i++;
            }

            System.out.println("What hero do you want, enter the hero's number or 0 if you want no more heroes.");
            int inp = Input.getInt(0,heroes.length);
            if(inp==0){
                if(temp.size()==0){
                    System.out.println("You must have at least one hero!");
                }else{
                    done = true;
                }
            }else{
                if(heroes[inp-1]==null){
                    System.out.println("Hero already chosen, chose a different hero.");
                }else {
                    temp.add(heroes[inp - 1]);
                    counter++;
                    heroes[inp - 1] = null;
                }
            }
            if(counter ==3){
                done = true;
            }

        }
        return new Player[]{new Player(name,Arrays.copyOf(temp.toArray(),temp.toArray().length,Hero[].class))};
    }

    public String[] splitted(String s){
        ArrayList<String> temp = new ArrayList<String>(Arrays.asList(s.split(" ")));
        for(int i = 0;i<temp.size();i++){
            if(temp.get(i).equals("")){
                temp.remove(i);
                i--;
            }
        }
        String[] tempo = new String[temp.size()];
        for(int i = 0; i<tempo.length;i++){
            tempo[i]= temp.get(i);
        }
        return tempo;
    }

    public void displayEntities(Entity[] e){
        for(Entity ele: e){
            System.out.println(ele);
        }
    }

    public void importData() throws FileNotFoundException {

        ArrayList<Entity> temp = new ArrayList<Entity>();
        File f = new File("Paladins.txt");
        Scanner s = new Scanner(f);
        s.nextLine();
        while(s.hasNextLine()){
            String[] arr = splitted(s.nextLine());//.split(" ");
            temp.add(new Paladin(arr[0],1,Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),Integer.parseInt(arr[5]),Integer.parseInt(arr[6])));
        }

        f = new File("Sorcerers.txt");
        s = new Scanner(f);
        s.nextLine();
        while(s.hasNextLine()){
            String[] arr = splitted(s.nextLine());
            temp.add(new Sorcerer(arr[0],1,Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),Integer.parseInt(arr[5]),Integer.parseInt(arr[6])));
        }

        f = new File("Warriors.txt");
        s = new Scanner(f);
        s.nextLine();
        while(s.hasNextLine()){
            String[] arr = splitted(s.nextLine());
            temp.add(new Warrior(arr[0],1,Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),Integer.parseInt(arr[5]),Integer.parseInt(arr[6])));
        }

        heroes = Arrays.copyOf(temp.toArray(),temp.toArray().length,Hero[].class);
        temp.clear();


        f = new File("Dragons.txt");
        s = new Scanner(f);
        s.nextLine();
        int o =0;
        while(s.hasNextLine()){
            String[] arr = splitted(s.nextLine());
            temp.add(new Dragon(arr[0],Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4])));
        }

        f = new File("Exoskeletons.txt");
        s = new Scanner(f);
        s.nextLine();
        while(s.hasNextLine()){
            String[] arr = splitted(s.nextLine());
            temp.add(new Exosleleton(arr[0],Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4])));
        }

        f = new File("Spirits.txt");
        s = new Scanner(f);
        s.nextLine();
        while(s.hasNextLine()){
            String[] arr = splitted(s.nextLine());
            temp.add(new Spirit(arr[0],Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4])));
        }

        monsters = Arrays.copyOf(temp.toArray(),temp.toArray().length,Monster[].class);
        temp.clear();

        f = new File("Weaponry.txt");
        s = new Scanner(f);
        s.nextLine();
        while(s.hasNextLine()){
            String[] arr = splitted(s.nextLine());
            temp.add(new Weaponry(arr[0],Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4])));
        }

        weapons = Arrays.copyOf(temp.toArray(),temp.toArray().length,Weaponry[].class);
        temp.clear();

        f = new File("Potions.txt");
        s = new Scanner(f);
        s.nextLine();
        while(s.hasNextLine()){
            String[] arr = splitted(s.nextLine());
            String[] atrs;
            if(arr[0].contains("Heal")){
                atrs = new String[]{"Health"};
            }else if(arr[1].contains("Magic")){
                atrs = new String[]{"Mana"};
            }else if(arr[1].contains("Strength")){
                atrs = new String[]{"Strength"};
            }else{
                atrs = new String[]{"Health","Mana","Strength","Dexterity","Agility"};
            }
            temp.add(new Potion(arr[0],Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),atrs));
        }

        potions = Arrays.copyOf(temp.toArray(),temp.toArray().length,Potion[].class);
        temp.clear();


        f = new File("Armory.txt");
        s = new Scanner(f);
        s.nextLine();
        while(s.hasNextLine()){
            String[] arr = splitted(s.nextLine());
            temp.add(new Armor(arr[0],Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3])));
        }

        armor = (Armor[]) Arrays.copyOf(temp.toArray(),temp.toArray().length,Armor[].class);
        temp.clear();

        f = new File("FireSpells.txt");
        s = new Scanner(f);
        s.nextLine();
        while(s.hasNextLine()){
            String[] arr = splitted(s.nextLine());
            temp.add(new Spell(arr[0],Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),"Fire"));
        }

        f = new File("IceSpells.txt");
        s = new Scanner(f);
        s.nextLine();
        while(s.hasNextLine()){
            String[] arr = splitted(s.nextLine());
            temp.add(new Spell(arr[0],Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),"Ice"));
        }

        f = new File("LightningSpells.txt");
        s = new Scanner(f);
        s.nextLine();
        while(s.hasNextLine()){
            String[] arr = splitted(s.nextLine());
            temp.add(new Spell(arr[0],Integer.parseInt(arr[1]),Integer.parseInt(arr[2]),Integer.parseInt(arr[3]),Integer.parseInt(arr[4]),"Lightning"));
        }
        
        spells = Arrays.copyOf(temp.toArray(),temp.toArray().length,Spell[].class);

    }


    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Hero[] getHeroes() {
        return heroes;
    }

    public void setHeroes(Hero[] heroes) {
        this.heroes = heroes;
    }

    public Monster[] getMonsters() {
        return monsters;
    }

    public void setMonsters(Monster[] monsters) {
        this.monsters = monsters;
    }

    public Weaponry[] getWeapons() {
        return weapons;
    }

    public void setWeapons(Weaponry[] weapons) {
        this.weapons = weapons;
    }

    public Potion[] getPotions() {
        return potions;
    }

    public void setPotions(Potion[] potions) {
        this.potions = potions;
    }

    public Armor[] getArmor() {
        return armor;
    }

    public void setArmor(Armor[] armor) {
        this.armor = armor;
    }

    public Spell[] getSpells() {
        return spells;
    }

    public void setSpells(Spell[] spells) {
        this.spells = spells;
    }
}
