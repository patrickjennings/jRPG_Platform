
import java.awt.Color;
import java.awt.Font;
public class MenuItem
{
    private Font font;
    private String menutext;
    private Color color;
    public MenuItem(String text,String name, int number)
    {
        color = new Color(0, 0, 240);
        font = new Font(name,Font.PLAIN,number);
        menutext = text;
    }
    public MenuItem(String text,String name, int number, int r, int b, int g)
    {
        color = new Color(r, g, b);
        font = new Font(name,Font.PLAIN,number);
        menutext = text;
    }
    public void setSelected()
    {
        color = new Color(250, 250, 250);
    }
    public void setColor(int r, int g, int b)
    {
        color = new Color(r,g,b);
    }
    public Color getColor(){return color;}
    public Font getFont(){return font;}
    public String getText(){return menutext;}
}