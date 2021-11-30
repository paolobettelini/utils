package regions.utils;

public class Point {

    private double x;
    private double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Point clone() {
        return new Point(this.x, this.y);
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public void move(double x, double y) {
        this.setX(x);
        this.setY(y);
    }
    
    public void move(Point point) {
        this.move(point.getX(), point.getY());
    }
    
}