import java.util.ArrayList;
public class Market extends Tile {

    private final int INVENTORY_SIZE = 10;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private final String[][] shopkeeps = {{"human","Jiang","Anton","Dona","Hahpet","Frath","Nulara","Hama","Westra","Igan","Dorn","Darvin","Malark","Helm","Randal","Evendur","Kerri","Miri","Mival"},
                                          {"elven","Varis","Himo","Thamior","Rolen","Carric","Quarion","Mindartis","Erdan","Theren","Shava","Thia","Sariel","Althaea","Drusilia","Birel","Mialee","Xanaphia"},
                                          {"dwarven","Adrik","Ulfgar","Orsik","Delg","Fargrim","Dain","Brotter","Helja","Vistra","Liftrasa","Diesa","Eldeth","Gunnloda","Dagnal","Kathra","Torgga","Travok","Tordek"}};
    private String keeper;

    public Market(Item[] inv){
        for(int i = 0; i<INVENTORY_SIZE;i++){
            inventory.add(inv[((int)(Math.random()*100000)%inv.length)]);
        }
        int race = ((int)(Math.random()*1000))  % shopkeeps.length;
        keeper = shopkeeps[race][(((int)(Math.random()*1000)) % (shopkeeps[race].length-1))+1]+", the "+shopkeeps[race][0]+" shopkeep";
    }
    public void displayInventory(){
        int i = 1;
        for(Item it : inventory){
            System.out.println(i++ +": "+it);
        }
    }

    @Override
    public boolean passable() {
        return true;
    }

    public void steppedOn(Player p) {
        System.out.println("You enter the doorway, and are greeted by "+keeper+". They say:");
        System.out.println(keeper+": Welcome to my market!");
        boolean contin = true;
        do{
            System.out.println(keeper+": Would someone in your party like to buy or sell something? (y/n)");
            char input;
            if(Character.toUpperCase(Input.getChar(new char[]{'Y','y','N','n'})) =='Y'){
                System.out.println(keeper+": Who would like to buy/sell?");
                p.displayParty();
                int input2 = Input.getInt(1,p.getParty().length);
                Hero h = p.getParty()[input2-1];
                System.out.println(keeper+": Hello "+h.getName()+", would you like to buy or sell something? (b/s)");

                if(Character.toUpperCase(Input.getChar(new char[]{'B','b','S','s'})) =='B'){
                    displayInventory();
                    System.out.println(keeper+": Which item would you like to purchase? (Enter the number to the left of the item, enter 0 to not buy anything)");
                    input2 = Input.getInt(0,inventory.size());
                    if(input2 != 0 && inventory.get(input2-1).getRequiredLevel()<=h.getLevel() && inventory.get(input2-1).getCost()<= h.getMoney()){
                        h.getBackpack().add(inventory.get(input2-1));
                        h.setMoney(h.getMoney()-inventory.get(input2-1).getCost());
                        inventory.remove(input2-1);
                        System.out.println(keeper+": Thank you for your purchase!");

                    }else{
                        if(input2!=0){
                            System.out.println(keeper+": I'm sorry, either you are to low leveled for this item or you do not have enough money.");
                        }
                    }
                }else{
                    System.out.println(keeper+": What are you looking to sell? (Enter the number to the left of the item, enter 0 to not sell anything)");
                    input2 = Input.getInt(0,inventory.size());
                    if(input2 != 0 ){
                        inventory.add(h.getBackpack().get(input2-1));
                        h.setMoney((h.getBackpack().get(input2-1).getCost()/2)+h.getMoney());
                        h.getBackpack().remove(input2-1);

                        System.out.println(keeper+": We have a deal!");

                    }
                }
            }else{
                contin = false;
            }
        }while(contin);

    }

    @Override
    public String getSymbol() {
        return ANSI_BLUE+'Ϻ'+ANSI_RESET;
    }


}
