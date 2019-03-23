
import java.util.Random;
import java.awt.*;
public class Battle
{
    private BattleMenu menu;
    private int seconds;
    private int level;
    private boolean victory;
    private Random rand;
    private Enemy enemy;
    public Battle()
    {
        rand = new Random();
    }
    // Random battle
    public void newRandom()
    {
        seconds = (rand.nextInt(200) + 500);
    }
    public boolean checkBattle(int number)
    {
        if(seconds <= number)
            return true;
        return false;
    }
    // Battle system
    public void newBattle(int lvl)
    {
        enemy = new Enemy();
        menu = new BattleMenu();
        victory = false;
        level = lvl + 1;
    }
    public void updateBattle(Spell[] array)
    {
        menu.setMenu(array);
        if(enemy.getHp() < 0)
            victory = true;
    }
    public void attack(int attack){enemy.hit(attack);}
    public void menuUp(){menu.moveUp();}
    public void menuDown(){menu.moveDown();}
    public void enter(RPG rpg)
    {
        menu.enter(rpg, enemy);
    }
    public boolean run()
    {
        if(menu.run())
            return tryRun();
        return false;
    }
    public boolean tryRun()
    {
        double chance = .80;
        if(Math.random() <= chance)
            return true;
        menu.setRun(false);
        menu.setAttack(true);
        return false;
    }
    public void enemyAttack(RPG rpg)
    {
        int n = enemy.attack();
        if(n > 0)
        {
            rpg.player.hit(n);
            rpg.sound.play(2);
        }
        menu.setAttack(false);
    }
    public boolean Attacking(){ return menu.Attacking();}
    public boolean tryVictory(){ return victory;}
    public void draw(RPG rpg, Graphics g, int width, int height)
    {
        g.setColor(Color.blue);
        g.drawRect(0,height - 125, width - 2, 125 - 2);
        g.drawImage(rpg.player.getImage(), width - 100, height / 4, null);
        g.drawImage(enemy.getImage(), 25, height / 4, null);
        g.setColor(Color.white);
        // enemy hp bar
        g.drawString("Enemy", 25, 25);
        g.drawRect(25, 30, enemy.getMaxHp() + 1, 10);
        // your hp bar
        g.drawString("HP:", 10, height - 105);
        g.drawRect(10, height - 100, 50 + 1, 10);
        //you mp bar
        g.drawString("MP:", 10, height - 75);
        g.drawRect(10, height - 70, 100 / 2 + 1, 10);
        g.setColor(Color.green);
        g.fillRect(26, 31, enemy.getHp(), 9);
        g.fillRect(11, height - 99, (rpg.player.getHp() / 2) - ((rpg.player.getTotalHp() / 2) - 50), 9);
        g.setColor(Color.blue);
        g.fillRect(11, height - 69, rpg.player.getMp() / 2, 9);
        menu.draw(g, width, height);
        if(victory)
        {
            if(rpg.player.getLevelUp())
            {
                g.setColor(Color.red);
                g.drawString("Level up!", width / 2, height - 180);
            }
            g.setColor(Color.white);
            g.drawString("Victory!", width / 2, height - 165);
            g.drawString("Experience: " + (level * 25), width / 2, height - 150);
            g.drawString("Press Space bar...", width / 2, height - 135);
        }
    }
}