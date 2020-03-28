import java.util.Objects;

public abstract class Item extends Entity{
    private int cost, requiredLevel;

    public Item(String n, int c, int r){
        super(n);
        setCost(c);
        setRequiredLevel(r);
    }

    @Override
    public String toString() {
        return getName();
    }

    public int getCost(){return cost;}

    public int getRequiredLevel(){return requiredLevel;}


    public void setCost(int i){cost = i;}

    public void setRequiredLevel(int i){requiredLevel = i;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getName().equals(item.getName());
    }


}
