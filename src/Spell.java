
public class Spell
{
    private String name;
    private int mp;
    private int attack;
    public Spell(String text, int i, int j)
    {
        name = text;
        mp = i;
        attack = j;
    }
    public String getName(){return name;}
    public int getMp(){return mp;}
    public int getDamage(){return attack;}
}