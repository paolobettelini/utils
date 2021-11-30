package regions.shapes;

import regions.Region;

public class Rectangle implements Region {
    
    double minX;
    double maxX;
    double minY;
    double maxY;
    
    public Rectangle(double x1, double y1, double x2, double y2) {
        this.minX = Math.min(x1, x2);
        this.maxX = Math.max(x1, x2);
        this.minY = Math.min(y1, y2);
        this.maxY = Math.max(y1, y2);
    }
    
    @Override
    public boolean contains(double x, double y) {
        return x >= this.minX && x <= this.maxX && y >= this.minY && y <= this.maxY;
    }
    
}