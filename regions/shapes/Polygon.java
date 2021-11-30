package regions.shapes;

import regions.Region;
import regions.utils.Point;

public class Polygon implements Region {

    private Segment[] segments;
    
    public Polygon(Point... points) {
        assert points != null : "points cannot be null";
        assert points.length > 2 : "The amount of points must be at least 3";
        this.segments = new Segment[points.length];
        for (int i = 0; i < points.length; ++i) {
            this.segments[i] = new Segment(points[i].getX(), points[i].getY(), points[(i == points.length - 1) ? 0 : (i + 1)].getX(), points[(i == points.length - 1) ? 0 : (i + 1)].getY());
        }
    }
    
    @Override
    public boolean contains(double x, double y) {
        int counter = 0;
        for (int i = 0; i < this.segments.length; ++i) {
            if (this.segments[i].crosses(x, y)) {
                ++counter;
            }
        }
        return (counter & 1) == 1;
    }
    
    public static Polygon regularPolygon(double x, double y, int sides, double radius, double rotation) {
        assert sides > 2 : "The amount of points must be at least 3";
        Point[] points = new Point[sides];
        double increment = 6.283185307179586 / sides;
        for (int i = 0; i < sides; ++i) {
            points[i] = new Point(radius * Math.cos(increment * i + rotation) + x, radius * Math.sin(increment * i + rotation) + y);
        }
        return new Polygon(points);
    }
    
    public static Polygon regularPolygon(double x, double y, int sides, double radius) {
        return regularPolygon(x, y, sides, radius, 0.0);
    }
}

class Segment {

    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    private double slope;
    private double yIntercept;
    private boolean isVertical;
    
    public Segment(double x1, double y1, double x2, double y2) {
        this.isVertical = false;
        assert y1 != y2 : "Two adjacent points cannot be overlapping";
        this.minX = Math.min(x1, x2);
        this.maxX = Math.max(x1, x2);
        this.minY = Math.min(y1, y2);
        this.maxY = Math.max(y1, y2);
        if (x1 == x2) {
            this.isVertical = true;
            return;
        }
        this.slope = (y1 - y2) / (x1 - x2);
        this.yIntercept = y1 - this.slope * x1;
    }
    
    protected boolean crosses(double x, double y) { // non basta (x < min) || ...
    // senza fare this.isVertical (?)
        return y > this.minY && y <= this.maxY && (this.isVertical ? (x < this.minX) : (x < this.minX || (x < this.maxX && x < (y - this.yIntercept) / this.slope)));
    }
    
}