public class BlockingTile extends Tile {


    @Override
    public boolean passable() {
        return false;
    }

    public void steppedOn(Player p) {

    }

    @Override
    public String getSymbol() {
        return ANSI_RED+'X'+ANSI_RESET;
    }
}
