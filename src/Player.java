

import java.awt.Graphics;
import java.awt.Image;
public class Player
{
    private int x, y, x_pos, y_pos;
    private int run;
    private int hp, totalhp, power, mp, level, experience;
    private double multiplier;
    private int imagex;
    private Inventory inv = new Inventory();
    private SpellList spell;
    Image player;
    Image battle;
    boolean levelup;
    public Player(int x1, int y1)
    {
        totalhp = 100;
        mp = 100;
        hp = totalhp;
        x = x1;
        y = y1;
        x_pos = x1;
        y_pos = y1;
        run = 2;
        imagex = 0;
        power = 2;
        multiplier = 2.5;
        level = 1;
        levelup = false;
        experience = level * 100;
        spell = new SpellList();
        battle = MediaPack.battle;
        setImage(MediaPack.player[0][0]);
    }
    public void moveLeft(int speed)
    {
        changeImage(3, x%run);
        x -= speed;
        x_pos -= speed;
    }
    public void moveRight(int speed)
    {
        changeImage(1, x%run);
        x += speed;
        x_pos += speed;
    }
    public void moveUp(int speed)
    {
        changeImage(2 , y%run);
        y -= speed;
        y_pos -= speed;
    }
    public void moveDown(int speed)
    {
        changeImage(0 , y%run);
        y += speed;
        y_pos += speed;
    }
    public void movePosX(int speed)
    {
        x_pos += speed;
        if(speed > 0)
            changeImage(1, x_pos%run);
        else
            changeImage(3, x_pos%run);
    }
    public void movePosY(int speed)
    {
        y_pos += speed;
        if(speed > 0)
            changeImage(0, y_pos%run);
        else
            changeImage(2, y_pos%run);
    }
    public void changeImage(int i, int j)
    {
        if(j == 0)
            changeImageX();
        setImage(MediaPack.player[i][imagex]);
    }
    public void changeImageX()
    {
        imagex += 1;
        if(imagex > 2)
            imagex = 0;
    }
    public void setImage(Image image)
    {
        player = image;
    }
    public int attack()
    {
        return (int)((power * multiplier)*2 + ((inv.getAttack()) * level) + (Math.random() * 10));
    }
    public int spellDamage(int i)
    {
        return spell.getDamage(i);
    }
    public void hit(int n)
    {
        hp -= n;
    }
    public void useMp(int number)
    {
        mp -= number;
    }
    public void gainExp(int number)
    {
        levelup = false;
        experience -= number;
        if(experience <= 0)
            levelUp();
    }
    public void levelUp()
    {
        level++;
        experience = level * 100;
        totalhp += 15;
        hp = totalhp;
        levelup = true;
    }
    public int getLevel(){return level;}
    public boolean getLevelUp(){return levelup;}
    public int getExp(){return experience;}
    public int getSpellMp(int number){return spell.getMp(number);}
    public int getX(){return x;}
    public int getY(){return y;}
    public int getXPos(){return x_pos;}
    public int getYPos(){return y_pos;}
    public Image getImage(){return battle;}
    public int getTotalHp(){return totalhp;}
    public int getHp(){return hp;}
    public int getMp(){return mp;}
    public void movePlayer(int x1, int y1)
    {
        x = x1;
        y = y1;
        x_pos = x1;
        y_pos = y1;
    }
    public Item[] getItems(){return inv.getItems();}
    public Spell[] getSpells(){return spell.getSpells();}
    public boolean checkEquip(int number){return inv.checkEquip(number);}
    public void equip(int number){inv.equip(number);}
    public void drawStats(Graphics g, RPG rpg){}
    public void draw(Graphics g)
    {
        g.drawImage(player, x, y, null);
    }
}