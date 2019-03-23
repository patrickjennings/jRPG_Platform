
public class Item
{
    private String item;
    private int attack;
    public Item(String name, int number)
    {
        item = name;
        attack = number;
    }
    public String getItem(){return item;}
    public int getAttack(){return attack;}
}