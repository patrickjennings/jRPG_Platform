
import java.awt.*;
public class InvMenu
{
    private MenuItem[] menu;
    private MenuItem title;
    private int selected;
    private int[] attack;
    private int equip;
    private int level, hp, mp, exp;
    public InvMenu()
    {
        level = 0;
        hp = 0;
        mp = 0;
        exp = 0;
        equip = 0;
        selected = 0;
        title = new MenuItem("Inventory","times new roman",20, 250, 250, 250);
    }
    public void setArray(Item[] array)
    {
        menu = new MenuItem[array.length];
        attack = new int[array.length];
        for(int i = 0; i < menu.length; i++)
        {
            menu[i] = new MenuItem(array[i].getItem(),"times new roman",12);
            attack[i] = array[i].getAttack();
        }
        menu[selected].setSelected();
        menu[equip].setColor(250, 0, 0);
    }
    public void equip()
    {
        equip = selected;
        menu[equip].setColor(250, 0, 0);
    }
    public int getSelected(){return selected;}
    public void moveDown()
    {
        selected++;
        if(selected > menu.length-1)
            selected = menu.length-1;
        menu[selected].setSelected();
    }
    public void moveUp()
    {
        selected--;
        if(selected < 0)
            selected = 0;
        menu[selected].setSelected();
    }
    public void setStats(int lvl, int hp1, int mp1, int experience)
    {
        level = lvl;
        hp = hp1;
        mp = mp1;
        exp = experience;
    }
    public void draw(Graphics g, int width, int height)
    {
        g.setColor(Color.blue);
        g.fillRect(0,0,width / 2, height);
        g.setColor(Color.white);
        g.drawString("Level: " + level, (width / 4) - 20, 50);
        g.drawString("HP: " + hp, (width / 4) - 20, 65);
        g.drawString("MP: " + mp, (width / 4) - 20, 80);
        g.drawString("Exp: " + exp, (width / 4) - 20, 95);
        g.setFont(title.getFont());
        g.setColor(title.getColor());
        g.drawString(title.getText(), (width - (width / 4)) - 20, 20);
        g.drawString("Stats:", width / 4 - 20, 20);
        int number = 60;
        for(int i = 0; i < menu.length; i++)
        {
            g.setColor(menu[i].getColor());
            g.setFont(menu[i].getFont());
            g.drawString(menu[i].getText(), width - 125, number);
            number += 25;
        }
        if(!menu[selected].getText().equalsIgnoreCase("empty"))
        {
            g.setColor(Color.red);
            g.drawString("Attack: " + attack[selected], width / 2 + 10,60);
        }
    }
}