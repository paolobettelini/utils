package regions.shapes;

import regions.Region;
import regions.utils.Point;

public class Ellipse implements Region {
    
    private double x;
    private double y;
    private double xRadiusSquared;
    private double yRadiusSquared;
    private double sina;
    private double cosa;
    
    public Ellipse(double x, double y, double xRadius, double yRadius, double rotation) {
        this.x = x;
        this.y = y;
        this.xRadiusSquared = xRadius * xRadius;
        this.yRadiusSquared = yRadius * yRadius;
        if (rotation != 0.0) {
            this.sina = Math.sin(-rotation);
            this.cosa = Math.cos(-rotation);
        }
    }
    
    public Ellipse(double x, double y, double xRadius, double yRadius) {
        this(x, y, xRadius, yRadius, 0.0);
    }
    
    @Override
    public boolean contains(double x, double y) {
        Point normalized = new Point(x - this.x, y - this.y);

        if (this.sina != 0.0)
            normalized.move(this.cosa * normalized.getX() - this.sina * normalized.getY(), this.sina * normalized.getX() + this.cosa * normalized.getY());

        return normalized.getX() * normalized.getX() / this.xRadiusSquared + normalized.getY() * normalized.getY() / this.yRadiusSquared <= 1.0;
    }

}