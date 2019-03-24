
import java.awt.Image;
import java.util.Random;
public class Enemy
{
    private int maxhp;
    private int hp;
    private int attack;
    private Random rand;
    private double hit;
    private double multiplier;
    private Image image;
    public Enemy()
    {
        rand = new Random();
        multiplier = 2.5;
        attack = 5;
        hit = .75;
        hp = rand.nextInt(25) + 75;
        maxhp = hp;
        image = MediaPack.enemies[rand.nextInt(MediaPack.enemies.length)];
    }
    public int attack()
    {
        if(Math.random() < hit)
            return (int)((rand.nextInt(attack - 1) + 1) * multiplier);
        return 0;
    }
    public void hit(int attack)
    {
        hp -= attack;
    }
    public int getHp(){return hp;}
    public int getMaxHp(){return maxhp;}
    public Image getImage(){return image;}
}
