
public class SpellList
{
    private Spell[] spell;
    public SpellList()
    {
        spell = new Spell[4];
        spell[0] = new Spell("Ice",5,15);
        spell[1] = new Spell("Fire",15,25);
        for(int i = 2; i < spell.length; i++)
        {
            spell[i] = new Spell("Empty",0,0);
        }
    }
    public Spell[] getSpells(){return spell;}
    public int getMp(int number){return spell[number].getMp();}
    public int getDamage(int number){return spell[number].getDamage();}
}