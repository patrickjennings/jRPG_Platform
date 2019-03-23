
public class Wall
{
    private double x;
    private double y;
    private double x1;
    private double y1;
    public Wall(double x_pos, double y_pos, double x_pos1, double y_pos1)
    {
        x = x_pos;
        y = y_pos;
        x1 = x_pos1;
        y1 = y_pos1;
    }
    public boolean isCollided(int x_pos, int y_pos, int x_pos1, int y_pos1)
    {
        float r,s;
        float ax;
        float ay;
        float bx;
        float by;
        float cx;
        float cy;
        float dx;
        float dy;
        
        ax = (float)this.x;
        ay = (float)this.y;
        bx = (float)this.x1;
        by = (float)this.y1;
        cx = (float)x_pos;
        cy = (float)y_pos;
        dx = (float)x_pos1;
        dy = (float)y_pos1;
        
        //detect intersections between perfectly horizontal lines
        if ( ((this.x <= x_pos  &&                  //that startX is >= than this startX
               this.x >= x_pos1 ||
               
              (this.x1 <= x_pos  &&
               this.x1 >= x_pos1))
             &&
             
             ((Math.abs(y_pos - this.y) < 1)  &&  //the difference in y's is 0
             (Math.abs(y_pos1 - this.y1) < 1 )) ) )   //for both points
          return true;
        //detect intersections between perfectly vertical lines
        if ( ((this.y <= y_pos  &&
               this.y >= y_pos1) ||
               
              (this.y1 <= y_pos  &&
               this.y1 >= y_pos1))
             &&
             (Math.abs(x_pos - this.x) < 1 ) &&
             (Math.abs(x_pos1 - this.x1) < 1 ))
          return true;
        
        //In order to avoid a division by zero error we will slightly slant any
        //perfectly vertical or horizontal lines.  This may make the intersection
        //detection too eager when dealing with long close together parrallel lines.
        if (ax == bx) ax += .01;
        if (cx == dx) cx += .01;
        if (ay == by) ay += .01;
        if (cy == dy) cy += .01;
        
        
        r = ( ((ay - cy) * (dx - cx)) - ((ax - cx) * (dy - cy)) ) /
            ( ((bx - ax) * (dy - cy)) - ((by - ay) * (dx - cx)) );
        
        s = ( ((ay - cy) * (bx - ax)) - ((ax - cx) * (by - ay)) ) /
            ( ((bx - ax) * (dy - cy)) - ((by - ay) * (dx - cx)) );
        
        if ( r<0 || r>1 || s<0 || s>1 )
          return false;
        else
          return true;
    }
    public boolean right(int x_pos, int y_pos, int x_pos1, int y_pos1)
    {
        if((this.y >= y_pos && this.x == x_pos) && this.y1 == y_pos1)
            return true;
        return false;
    }
}