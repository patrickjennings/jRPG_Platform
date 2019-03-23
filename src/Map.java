
import java.awt.*;
public class Map
{
    private int x;
    private int y;
    private Wall[] wall;
    private Levels levels;
    private int level;
    Teleporter tele;
    private Image map;
    public Map(int x_pos, int y_pos, int w, int h)
    {
        tele = new Teleporter(x_pos, y_pos, w, h);
        level = 0;
        x = x_pos;
        y = y_pos;
        setMap();
        levels = new Levels();
        copyArray();
    }
    public void changeMap(int number)
    {
        x = 0;
        y = 0;
        level = number;
        setMap();
        levels.changeLevel(level + 1);
        copyArray();
        tele.changeLevel(level + 1);
    }
    public void nextMap()
    {
        x = 0;
        y = 0;
        level++;
        setMap();
        levels.changeLevel(level + 1);
        copyArray();
        tele.changeLevel(level + 1);
    }
    public void setMap()
    {
        map = MediaPack.map[level];
    }
    public Image getMap(){return map;}
    public void copyArray()
    {
        wall = new Wall[levels.getSize()];
        System.arraycopy(Levels.wall, 0, wall, 0, levels.getSize());
    }
    public boolean checkCollision(int x_pos, int y_pos, int width, int height)
    {
        for(int i = 0; i < wall.length; i++)
        {
            if(wall[i].isCollided(x_pos, y_pos, x_pos+width, y_pos+height))
                return true;
        }
        return false;
    }
    public boolean teleCollision(int x_pos, int y_pos, int width, int height){return tele.checkCollision(x_pos, y_pos, width, height);}
    public int getLevel(){return level;}
    public void moveLeft(int speed)
    {
        x -= speed;
    }
    public void moveRight(int speed)
    {
        x += speed;
    }
    public void moveUp(int speed)
    {
        y -= speed;
    }
    public void moveDown(int speed)
    {
        y += speed;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void move(int x_pos, int y_pos)
    {
        x = x_pos;
        y = y_pos;
    }
}
