
public class Teleporter
{
    private Wall[] wall;
    private TeleList list;
    private int level;
    public Teleporter(int x_pos, int y_pos, int w, int h)
    {
        level = 1;
        list = new TeleList();
        copyArray();
    }
    public void changeLevel(int number)
    {
        level = number;
        list.changeLevel(level);
        copyArray();
    }
    public void copyArray()
    {
        wall = new Wall[list.getSize()];
        System.arraycopy(list.getArray(), 0, wall, 0, list.getSize());
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
}
