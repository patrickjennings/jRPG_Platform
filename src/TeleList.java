
public class TeleList
{
    private static Wall[] wall;
    public TeleList()
    {
        wall = new Wall[8];
        wall[0] = new Wall(954.0,257.0,974.0,246.0);
        wall[1] = new Wall(974.0,246.0,981.0,230.0);
        wall[2] = new Wall(981.0,230.0,971.0,204.0);
        wall[3] = new Wall(971.0,204.0,949.0,198.0);
        wall[4] = new Wall(949.0,198.0,928.0,208.0);
        wall[5] = new Wall(928.0,208.0,921.0,228.0);
        wall[6] = new Wall(921.0,231.0,931.0,251.0);
        wall[7] = new Wall(931.0,251.0,954.0,258.0);
    }
    public void changeLevel(int level)
    {
        if(level == 1){
            wall = new Wall[8];
            wall[0] = new Wall(954.0,257.0,974.0,246.0);
            wall[1] = new Wall(974.0,246.0,981.0,230.0);
            wall[2] = new Wall(981.0,230.0,971.0,204.0);
            wall[3] = new Wall(971.0,204.0,949.0,198.0);
            wall[4] = new Wall(949.0,198.0,928.0,208.0);
            wall[5] = new Wall(928.0,208.0,921.0,228.0);
            wall[6] = new Wall(921.0,231.0,931.0,251.0);
            wall[7] = new Wall(931.0,251.0,954.0,258.0);}
        else if(level == 2){
            wall = new Wall[8];
            wall[0] = new Wall(206.0,988.0,220.0,988.0);
            wall[1] = new Wall(220.0,988.0,232.0,967.0);
            wall[2] = new Wall(232.0,967.0,232.0,955.0);
            wall[3] = new Wall(232.0,955.0,215.0,933.0);
            wall[4] = new Wall(215.0,933.0,204.0,935.0);
            wall[5] = new Wall(204.0,935.0,193.0,957.0);
            wall[6] = new Wall(193.0,957.0,193.0,966.0);
            wall[7] = new Wall(193.0,966.0,205.0,988.0);}
        else if(level == 3){
            wall = new Wall[8];
            wall[0] = new Wall(1311.0,83.0,1331.0,71.0);
            wall[1] = new Wall(1331.0,71.0,1335.0,46.0);
            wall[2] = new Wall(1335.0,46.0,1318.0,29.0);
            wall[3] = new Wall(1318.0,29.0,1297.0,28.0);
            wall[4] = new Wall(1297.0,28.0,1282.0,40.0);
            wall[5] = new Wall(1282.0,40.0,1279.0,65.0);
            wall[6] = new Wall(1279.0,65.0,1291.0,79.0);
            wall[7] = new Wall(1291.0,79.0,1311.0,85.0);}
        else if(level == 4){
            wall = new Wall[1];
            wall[0] = new Wall(1307.0,28.0,1207.0,28.0);}
        else if(level == 5){
            wall = new Wall[0];}
        
    }
    public Wall[] getArray(){return wall;}
    public int getSize(){return wall.length;}
}