public class Sorcerer extends Hero {

    public Sorcerer(String n, int l, int tm, int s, int a, int dex, int mo, int ex) {
        super(n, l, tm, s, a, dex, mo, ex);
    }

    @Override
    public void LevelUp() {
        super.LevelUp();
        setDexterity((int) Math.round(getDexterity()*1.05));
        setAgility((int) Math.round(getAgility()*1.05));
    }

    @Override
    public String getName() {
        return ANSI_PURPLE+super.getName()+ANSI_RESET;
    }
}
