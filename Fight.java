import java.util.ArrayList;

public class Fight {
    private Player player;
    private Hero[] heros;
    private Monster[] monsters;
    private final float PERCENT_REGEN = .1; // percent health/mana heroes regenerate after fighting

    //exp and gold bonus from combat
    public static final int GOLD_BONUS = 150;
    public static final int EXP_BONUS = 2;
    public Fight(Player p, Monster[] monsterEncounter) {
        setPlayer(p);
        setHeros(p.getParty());
        setMonsters(monsterEncounter);
    }

    public Fight() {

    }

    //getters and setters

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Hero[] getHeros() {
        return heros;
    }

    public void setHeros(Hero[] heros) {
        this.heros = heros;
    }

    public Monster[] getMonsters() {
        return monsters;
    }

    public void setMonsters(Monster[] monsters) {
        this.monsters = monsters;
    }

    //returns true if heroes won, false if monsters won
    public boolean instigate(){
        System.out.println("You have entered combat!");
        boolean isOver = false;
        do{
            int pos =0;
            if(isOver)
                break;
            for(Hero currentHero: heros){
                if(currentHero.getCurrentHealth()>0){
                    turn(currentHero,pos++);
                }
                if(allHerosDead()||allMonstersDead()){
                    isOver=true;
                    break;
                }
            }
            if(isOver)
                break;
            pos = -1;
            for(Monster currentMonster: monsters){
                pos++;
                if(currentMonster.getCurrentHealth()>0){
                    boolean validTarget = false;
                    Hero target = null;
                    do{
                        if(heros[pos].getCurrentHealth()>0){
                            target = heros[pos];
                            validTarget = true;
                        }else{
                            pos = (pos+1)%getHeros().length;
                        }
                    }while(!validTarget);

                     target.receiveDamage(currentMonster.regularDamage());
                     System.out.println(currentMonster.getName()+" attacked "+target.getName()+"!");
                    if(allHerosDead()||allMonstersDead()){
                        isOver=true;
                        break;
                    }

                }

            }
            if(isOver)
                break;
            System.out.println("All heroes regenerated 5% of their health and mana!");
            for(Hero h: heros){
                h.setCurrentHealth((int) (h.getCurrentHealth()*1.05));
                h.setCurrentMana((int) (h.getCurrentMana()*1.05));
            }

        }while(!isOver);

        if(allMonstersDead()){
            System.out.println("All the monsters have been slain!");
            for(Hero h: heros){
                if(h.getCurrentHealth()>0){
                    System.out.println(h.getName()+" received "+GOLD_BONUS+" Gold and "+EXP_BONUS+" exp!");
                    h.setMoney(h.getMoney()+GOLD_BONUS);
                    h.setCurrentExperience(h.getCurrentExperience()+EXP_BONUS);
                    if(h.isReadyToLevelUp()){
                        h.LevelUp();
                        System.out.println(h.getName()+" leveled up!");
                    }
                }else{
                    System.out.println(h.getName()+" was revived with half health and half mana!");
                    h.setCurrentHealth(h.getTotalHealth()/2);
                    h.setCurrentMana(h.getTotalMana()/2);
                }

            }
        }else{
            System.out.println("All the heroes have been slain!\nEveryone was brought back up to half health and half mana, all members lost half their gold");
            for(Hero h: heros){
                h.setCurrentHealth(h.getTotalHealth()/2);
                h.setCurrentMana(h.getTotalMana()/2);
                h.setMoney(h.getMoney()/2);
            }
        }
        boolean rtrn = allMonstersDead();
        for(Monster m : monsters){
            m.setCurrentHealth(m.getCurrentHealth());
        }
        return rtrn;
    }

    public boolean allMonstersDead(){
        for(Monster m: monsters){
            if(m.getCurrentHealth()>0)
                return false;
        }
        return true;
    }

    public boolean allHerosDead(){
        for(Hero h: heros){
            if(h.getCurrentHealth()>0)
                return false;
        }
        return true;
    }

    public void turn(Hero hero, int position){
        System.out.println("\nIt's "+hero.getName()+"'s turn!");

        boolean validTarget = false;
        Monster target = null;
        int pos = position;
        do{
            if(getMonsters()[pos].getCurrentHealth()>0){
                target = getMonsters()[pos];
                validTarget = true;
            }else{
                pos = (pos+1)%getHeros().length;
            }
        }while(!validTarget);


        boolean validAction = false;

        do {
            System.out.println("\nWould you like to:\n\t1) Regular Attack\n\t2) Use a Potion\n\t3) Cast a Spell\n\t4) Equip a weapon or armor\n\tH) View Hero Info (does not take up turn)\n\tM) View Monster info (does not take up turn)");
            char swi = Input.getChar(new char[]{'1','2','3','4','h','H','m','M'});
            swi= Character.toUpperCase(swi);
            switch (swi) {
                case '1':
                    validAction = regularAttack(hero, target);
                    break;
                case '2':
                    validAction = drinkPotion(hero);
                    break;
                case '3':
                    validAction = castSpell(hero, target);
                    break;
                case '4':
                    validAction = equipWeaponOrArmor(hero);
                    break;
                case 'H':
                    displayMemberOfGroup(heros);
                    break;
                case 'M':
                    displayMemberOfGroup(monsters);
                    break;
            }
        }while(!validAction);

        if(target.getCurrentHealth()<=0){
            System.out.println("You slew the "+target.getName());
        }
    }

    public boolean regularAttack(LivingEntity attacker, LivingEntity defender){

        if(defender.isDodged()){
            System.out.println("Oh No! You Missed!");
        }else{
            System.out.println("You hit "+defender.getName()+"!");
            defender.receiveDamage(attacker.regularDamage());
        }

        return true;
    }

    public boolean drinkPotion(Hero hero){

        ArrayList<Potion> potions = new ArrayList<Potion>();

        for(int i = 0;i<hero.getBackpack().size();i++){
            if(hero.getBackpack().get(i) instanceof Potion){
                potions.add((Potion) hero.getBackpack().get(i));
            }
        }
        if(potions.size()==0){
            System.out.println("You have no potions");
            return false;
        }
        System.out.println("What potion would you like to use?:");
        int count = 1;
        for(Potion p: potions){

            System.out.println(""+ count++ +": "+ p);
        }
        int input = Input.getInt(1,potions.size());
        potions.get(input-1).takeAffect(hero);
        System.out.println("You drank a "+potions.get(input-1).getName()+"!");
        return true;
    }

    public boolean castSpell(Hero hero, Monster defender){
        ArrayList<Spell> spells = new ArrayList<Spell>();

        for(int i = 0;i<hero.getBackpack().size();i++){
            if(hero.getBackpack().get(i) instanceof Spell){
                spells.add((Spell) hero.getBackpack().get(i));
            }
        }
        if(spells.size()==0){
            System.out.println("You have no spells!");
            return false;
        }
        System.out.println("What spell would you like to use?:");
        int count = 1;
        for(Spell s: spells){

            System.out.println(""+ count++ +": "+ s);
        }
        int input = Input.getInt(1,spells.size());

        if(spells.get(input-1).getManaCost()>hero.getCurrentMana()){
            System.out.println("You dont have enough Mana to cast that spell!");
            return false;
        }

        hero.setCurrentMana(hero.getCurrentMana()-spells.get(input-1).getManaCost());
        defender.receiveDamage((int) (spells.get(input-1).getDamage()+spells.get(input-1).getDamage()*(hero.getDexterity()/10000.0)));

        if(spells.get(input-1).getType().equals("Fire")){
            defender.setDefense((int) (defender.getDefense()*0.9));
        }else if(spells.get(input-1).getType().equals("Ice")){
            defender.setDefense((int) (defender.getDamage()*0.9));
        }else if(spells.get(input-1).getType().equals("Lightning")){
            defender.setDefense((int) (defender.getDamage()*0.9));
        }
        return true;
    }

    public boolean equipWeaponOrArmor(Hero hero){
        ArrayList<Item> equipment = new ArrayList<Item>();

        for(int i = 0;i<hero.getBackpack().size();i++){
            if(hero.getBackpack().get(i) instanceof Armor || hero.getBackpack().get(i) instanceof Weaponry){
                equipment.add( hero.getBackpack().get(i));
            }
        }

        if(equipment.size()==0){
            System.out.println("You have no weapons or armor!");
            return false;
        }

        System.out.println("What armor would you like to equip?:");
        int count = 1;
        for(Item it: equipment){

            System.out.println(""+ count++ +": "+ it);
        }
        int input = Input.getInt(1,equipment.size());

        if(equipment.get(input-1).equals(hero.getArmor())||equipment.get(input-1).equals(hero.getWeapon())){
            System.out.println("You already have this equipped!");
            return false;
        }

        if(equipment.get(input-1) instanceof Weaponry){
            hero.setWeapon((Weaponry) equipment.get(input-1));
        }else if(equipment.get(input-1) instanceof Armor){
            hero.setArmor((Armor) equipment.get(input-1));
        }
        return true;
    }

    public boolean displayMemberOfGroup(LivingEntity[] e){
        System.out.println("Which one would you like to view?");
        for(int i =0;i<e.length;i++){
            System.out.println((i+1)+": "+e[i].getName());
        }
        int ind = Input.getInt(1,e.length);
        System.out.println(e[ind-1]);
        return false;
    }

    public static void regenerateHeroes(Hero[] heroes) {
        System.out.println("All heroes regenerated " + (100*PERCENT_REGEN) + " of their health and mana!");
        for(Hero h: heroes){
            h.setCurrentHealth((int) (h.getCurrentHealth()*(1+PERCENT_REGEN)));
            h.setCurrentMana((int) (h.getCurrentMana()*(1+PERCENT_REGEN)));
        }
    }
}
