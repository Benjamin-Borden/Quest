public class Paladin extends Hero {

    public Paladin(String n, int l, int tm, int s, int a, int dex, int mo, int ex) {
        super(n, l, tm, s, a, dex, mo, ex);
    }

    public void LevelUp() {
        super.LevelUp();
        setDexterity((int) Math.round(getDexterity()*1.05));
        setStrength((int) Math.round(getStrength()*1.05));
    }

    public String getName(){
        return ANSI_CYAN+super.getName()+ANSI_RESET;
    }
}
