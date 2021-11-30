package regions.shapes;

import regions.Region;
import regions.utils.Point;

public class Circle implements Region {
    
    private Point origin;
    private double radiusSquared;
    
    public Circle(Point origin, double radius) {
        this.origin = origin;
        this.radiusSquared = radius * radius;
    }
    
    public Circle(double x, double y, double radius) {
        this(new Point(x, y), radius);
    }
    
    @Override
    public boolean contains(double x, double y) {
        return distanceSquared(x, y, this.origin.getX(), this.origin.getY()) <= this.radiusSquared;
    }
    
    private static double distanceSquared(double x1, double y1, double x2, double y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

}