import java.applet.Applet;
import java.awt.Image;
import java.awt.MediaTracker;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class MediaPack extends Applet
{
	MediaTracker m;
	private static final long serialVersionUID = 1L;
	protected static Image[][] player=new Image[4][3];
    protected static Image[] map = new Image[5];
    protected static Image battle;
    protected static Image[] enemies = new Image[4];
    
    public MediaPack()
    {
    	m = new MediaTracker(this);
        try{      
            player[0][0]=getImage("Sprites/d1.gif");
            player[0][1]=getImage("Sprites/d2.gif");
            player[0][2]=getImage("Sprites/d3.gif");
            
            player[1][0]=getImage("Sprites/r1.gif");
            player[1][1]=getImage("Sprites/r2.gif");
            player[1][2]=getImage("Sprites/r3.gif");
            
            player[2][0]=getImage("Sprites/u1.gif");
            player[2][1]=getImage("Sprites/u2.gif");
            player[2][2]=getImage("Sprites/u3.gif");
            
            player[3][0]=getImage("Sprites/l1.gif");
            player[3][1]=getImage("Sprites/l2.gif");
            player[3][2]=getImage("Sprites/l3.gif");
            
            map[0] = getImage("Maps/Jail.gif");
            map[1] = getImage("Maps/forest.gif");
            map[2] = getImage("Maps/Battlefield.gif");
            map[3] = getImage("Maps/Mothership.gif");
            map[4] = getImage("Maps/throne.gif");
            
            battle = getImage("Images/cloud.gif");
            
            enemies[0] = getImage("Images/fatty.gif");
            enemies[1] = getImage("Images/quatro.gif");
            enemies[2] = getImage("Images/scorpion.gif");
            enemies[3] = getImage("Images/wolf.gif");
        }
        catch(Exception e){System.out.println(e);}
    }
    public Image getImage(String im)
    {
    	Image img = null;
    	InputStream in = getClass().getResourceAsStream(im);
    	try {
    	 img = ImageIO.read(in);
    	 m.addImage(img, 0);
    	 m.waitForAll();
    	 }
    	catch (Exception e) {
    	 System.out.println(im + " does not exist.");
    	}
    	return img;
    }
}
