
import java.awt.Color;
import java.awt.Graphics;
public class BattleMenu
{
    private MenuItem[] menu;
    private MenuItem[] menuSpells;
    private MenuItem[] mp;
    private int selected;
    private int row;
    private int r2Selected;
    private boolean run, attack;
    private int number;
    public BattleMenu()
    {
        run = false;
        row = 1;
        selected = 0;
        r2Selected = 0;
        //setMenu();
        number = 0;
        attack = false;
    }
    public void setMenu(Spell[] array)
    {
        menu = new MenuItem[3];
        menu[0] = new MenuItem("Attack","times new roman",12);
        menu[1] = new MenuItem("Spells","times new roman",12);
        menu[2] = new MenuItem("Run","times new roman",12);
        menu[selected].setSelected();
        if(row == 2)
            setSpells(array);
    }
    public void setSpells(Spell[] array)
    {
        number = 0;
        for(int i = 0; i < array.length; i++)
        {
            if(!array[i].getName().equalsIgnoreCase("Empty"))
                number++;
        }
        menuSpells = new MenuItem[number+1];
        mp = new MenuItem[number+1];
        for(int j = 0; j < number; j++)
        {
            menuSpells[j] = new MenuItem(array[j].getName(),"times new roman",12);
            mp[j] = new MenuItem(Integer.toString(array[j].getMp()),"times new roman",12);
        }
        menuSpells[number] = new MenuItem("Back","times new roman",12);
        mp[number] = new MenuItem("","times new roman",12);
        menuSpells[r2Selected].setSelected();
    }
    public void moveDown()
    {
        if(row == 1 && selected+1 < menu.length){
            selected++;
            //selected = menu.length - 1;
            menu[selected].setSelected();}
        if(row == 2 && r2Selected+1 < menuSpells.length){
            r2Selected++;
            //r2Selected = menuSpells.length - 1;
            menuSpells[r2Selected].setSelected();}
    }
    public void moveUp()
    {
        if(row == 1 && selected-1 >= 0){
            selected--;
            //selected = 0;
            menu[selected].setSelected();}
        if(row == 2 && r2Selected-1 >= 0){
            r2Selected--;
            //r2Selected = 0;
            menuSpells[r2Selected].setSelected();}
    }
    public void enter(RPG rpg, Enemy enemy)
    {
        if(row == 1)
        {
            if(selected == 0){attack(rpg, enemy);}
            else if(selected == 1){row = 2;}
            else if(selected == 2){run = true;}
        }
        else if(row == 2)
        {
            if(r2Selected == menuSpells.length - 1){row = 1;}
            else{spell(rpg, enemy);}
        }
    }
    public void attack(RPG rpg, Enemy enemy)
    {
        enemy.hit(rpg.player.attack());
        rpg.sound.play(1);
        if(enemy.getHp() > 0)
            attack = true;
        else
            rpg.player.gainExp((rpg.back.getLevel()+1)*25);
    }
    public void spell(RPG rpg, Enemy enemy)
    {
        if(rpg.player.getMp() >= rpg.player.getSpellMp(r2Selected))
        {
            enemy.hit(rpg.player.spellDamage(r2Selected));
            rpg.player.useMp(rpg.player.getSpellMp(r2Selected));
            rpg.sound.play(0);
        }
        if(enemy.getHp() > 0)
            attack = true;
        else
            rpg.player.gainExp((rpg.back.getLevel()+1)*25);
    }
    public void setRun(boolean set){run = set;}
    public boolean run(){return run;}
    public boolean Attacking(){return attack;}
    public void setAttack(boolean b){attack = b;}
    public void draw(Graphics g, int width, int height)
    {
        g.setColor(Color.blue);
        g.drawRect(130, height - 115, 50, 75);
        int number = height - 100;
        for(int i = 0; i < menu.length; i++)
        {
            g.setColor(menu[i].getColor());
            g.setFont(menu[i].getFont());
            g.drawString(menu[i].getText(), 135, number);
            number += 15;
        }
        if(row == 2)
        {
            g.drawRect(200, height - 115, 65, 85);
            number = height - 100;
            for(int j = 0; j < menuSpells.length; j++)
            {
                g.setColor(menuSpells[j].getColor());
                g.setFont(menuSpells[j].getFont());
                g.drawString(menuSpells[j].getText(), 205, number);
                g.drawString(mp[j].getText(),250,number);
                number += 15;
            }
        }
    }
}