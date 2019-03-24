import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class RPG extends Applet implements Runnable
{
	private static final long serialVersionUID = 1L;
	Graphics b;
    Image backbuffer;
    volatile Thread t;
    boolean up, down, left, right, battle, inventory, random, next, end;
    Map back;
    Player player;
    Battle bat;
    InvMenu inv;
    SoundManager sound;
    int speed, width, height, playerHeight, playerWidth, timer;
    MediaPack m;
    
    public void init()
    {
        width = 800;
        height = 800;
        setSize(width,height);
        speed = 3;
        playerHeight = 23;
        playerWidth = 16;
        battle = false;
        inventory = false;
        next = false;
        end = false;
        m = new MediaPack();
        player = new Player(55 , 55);
        back = new Map(0,0,1400,1200);
        bat = new Battle();
        inv = new InvMenu();
        sound = new SoundManager();
        backbuffer = createImage(width, height);
        b = backbuffer.getGraphics();
    }
    
    public void updateGame()
    {
        if(!random)
        {
            bat.newRandom();
            random = true;
        }
        if(bat.checkBattle(timer))
        {
            timer = 0;
            battle = true;
            sound.changeBackground(battle);
            bat.newBattle(back.getLevel());
        }
        if(left && back.checkCollision(player.getXPos() - speed, player.getYPos(), playerWidth, playerHeight) == false){
            if(player.getX() < 75){
                player.movePosX(-speed);
                back.moveLeft(speed);}
            else
                player.moveLeft(speed);
                timer += 1;
            }
        if(right && back.checkCollision(player.getXPos() + speed, player.getYPos(), playerWidth, playerHeight)  == false){
            if(player.getX() > width - 75){
                player.movePosX(speed);
                back.moveRight(speed);}
            else
                player.moveRight(speed);
                timer += 1;
            }
        if(up && back.checkCollision(player.getXPos(), player.getYPos() - speed, playerWidth, playerHeight)  == false){
            if(player.getY() < 75){
                player.movePosY(-speed);
                back.moveUp(speed);}
            else
                player.moveUp(speed);
                timer += 1;
            }
        if(down && back.checkCollision(player.getXPos(), player.getYPos() + speed, playerWidth, playerHeight)  == false){
            if(player.getY() > height - 125){
                player.movePosY(speed);
                back.moveDown(speed);}
            else
                player.moveDown(speed);
                timer += 1;
            }
       if(back.teleCollision(player.getXPos(), player.getYPos(), playerWidth, playerHeight)){
                next = true;}
            else{
                next = false;}
        b.drawImage(back.getMap(),-1 * back.getX(),-1 * back.getY(),this);
        player.draw(b);
        if(next){
            b.setColor(Color.red);
            b.drawString("Next Map?", width / 2, height / 2);}
    }

    public void updateBattle()
    {
        if(player.getHp() <= 0)
        {
            endBattle();
            end = true;
        }
        bat.updateBattle(player.getSpells());
        if(bat.Attacking() && !sound.isPlaying())
        {
            bat.enemyAttack(this);   
        }
        if(bat.run())
            endBattle();
        bat.draw(this, b, width, height);
    }
    
    public void endBattle()
    {
        battle = false;
        sound.changeBackground(battle);
    }
    
    public void showInventory()
    {
        inv.setArray(player.getItems());
        inv.setStats(player.getLevel(), player.getHp(), player.getMp(), player.getExp());
        inv.draw(b,width,height);
    }
    
    public void equip()
    {
        if(player.checkEquip(inv.getSelected()))
        {
            inv.equip();
            player.equip(inv.getSelected());
        }
    }
    
    public void end()
    {
        b.setColor(Color.red);
        b.drawString("THE END", width / 2, height / 2);
    }
    
    public boolean keyDown(Event e, int key){
        if(key=='w')up=true;
        if(key=='s')down=true;
        if(key=='a')left=true;
        if(key=='d')right=true;
        if(key=='1'){back.changeMap(0); player.movePlayer(55 , 55);}
        if(key=='2'){back.changeMap(1); player.movePlayer(55 , 55);}
        if(key=='3'){back.changeMap(2); player.movePlayer(55 , 55);}
        if(key=='4'){back.changeMap(3); player.movePlayer(55 , 55);}
        if(key=='5'){back.changeMap(4); player.movePlayer(55 , 55);}
        if(key=='0' && !sound.isPlaying()){sound.play(2);}
        if(key=='b' && !inventory){bat.newBattle(back.getLevel()); battle=!battle; sound.changeBackground(battle);}
        if(key=='i' && !battle)inventory=!inventory;
        if(key==KeyEvent.VK_SPACE && battle && bat.tryVictory()){endBattle();}
        return true;
    }
    
    public boolean keyUp(Event e, int key){
        if(key=='w')up=false;
        if(key=='s')down=false;
        if(key=='a')left=false;
        if(key=='d')right=false;
        if(key=='w' && battle && !bat.tryVictory())bat.menuUp();
        if(key=='s' && battle && !bat.tryVictory())bat.menuDown();
        if(key=='w' && inventory)inv.moveUp();
        if(key=='s' && inventory)inv.moveDown();
        if(key==KeyEvent.VK_SPACE && battle && !sound.isPlaying()){bat.enter(this);}
        if(key==KeyEvent.VK_SPACE && next){back.nextMap(); player.movePlayer(55 , 55);}
        if(key==KeyEvent.VK_SPACE && inventory){equip();}
        return true;
    }

    public void start(){t=new Thread(this);t.start();}
    
    public void run(){
        while(t != null){
            b.setColor(Color.black);
            b.fillRect(0,0,width,height);
            if(inventory)
                showInventory();
            else if(battle)
                updateBattle();
            else if(end)
                end();
            else
                updateGame();
            repaint();
            try{Thread.sleep(25);}
            catch(Exception e){}
        }
        sound.end();
    }
    
    public void stop(){t=null;}
    
    public void paint(Graphics g)
    {
        g.drawImage(backbuffer,0,0,this);
    }
    
    public void update(Graphics g)
    {
        paint(g);
    }
}