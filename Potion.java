public class Potion extends Item {

    private int attributeIncrease;
    private String[] attributesIncreased;



    public Potion(String n, int c, int r, int i, String[] arr) {
        super(n, c, r);
        setAttributeIncrease(i);
        setAttributesIncreased(arr);
    }

    public int getAttributeIncrease() {
        return attributeIncrease;
    }

    public void setAttributeIncrease(int attributeIncrease) {
        this.attributeIncrease = attributeIncrease;
    }

    public String[] getAttributesIncreased() {
        return attributesIncreased;
    }

    public void setAttributesIncreased(String[] attributesIncreased) {
        this.attributesIncreased = attributesIncreased;
    }

    public void takeAffect(Hero hero){
        for(int i = 0;i<attributesIncreased.length;i++){
            if(attributesIncreased[i].equals("Strength")){
                hero.setStrength((int) (hero.getStrength()+hero.getStrength()*(attributeIncrease/100.00)));
            }else if(attributesIncreased[i].equals("Dexterity")){
                hero.setDexterity((int) (hero.getDexterity()+hero.getDexterity()*(attributeIncrease/100.00)));
            }else if(attributesIncreased[i].equals("Agility")){
                hero.setAgility((int) (hero.getAgility()+hero.getAgility()*(attributeIncrease/100.00)));
            }else if(attributesIncreased[i].equals("Health")){
                hero.setCurrentHealth((int) (hero.getCurrentHealth()+hero.getCurrentHealth()*(attributeIncrease/100.00)));
            }else if(attributesIncreased[i].equals("Mana")){
                hero.setCurrentMana((int) (hero.getCurrentMana()+hero.getCurrentMana()*(attributeIncrease/100.00)));
            }else if(attributesIncreased[i].equals("Money")){
                hero.setMoney((int) (hero.getMoney()+hero.getMoney()*(attributeIncrease/100.00)));
            }else if(attributesIncreased[i].equals("Experience")){
                hero.setCurrentExperience((int) (hero.getCurrentExperience()+hero.getCurrentExperience()*(attributeIncrease/100.00)));
            }
        }
    }
}
