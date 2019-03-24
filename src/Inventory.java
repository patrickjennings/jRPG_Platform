
public class Inventory
{
    private Item[] item;
    private int equip;
    public Inventory()
    {
        item = new Item[10];
        item[0] = new Item("Sword", 1);
        item[1] = new Item("Knife",2);
        item[2] = new Item("Sword of 1000 Truths",200);
        for(int i = 3; i < item.length; i++)
        {
            item[i] = new Item("Empty", 1);
        }
        equip = 0;
    }
    public boolean checkEquip(int number)
    {
        if(item[number].getItem().equalsIgnoreCase("empty"))
            return false;
        return true;
    }
    public void equip(int number)
    {
        equip = number;
    }
    public Item[] getItems(){return item;}
    public int getAttack(){return item[equip].getAttack();}
    public int getCapacity(){return item.length;}
}