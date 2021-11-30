package regions.shapes;

import java.util.function.BiFunction;

import regions.Region;
import regions.utils.Point;

public class Equation implements Region {

    private double x;
    private double y;
    private double sina;
    private double cosa;
    private BiFunction<Double, Double, Boolean> equation;
    
    public Equation(double x, double y, BiFunction<Double, Double, Boolean> equation, double rotation) {
        this.x = x;
        this.y = y;
        this.equation = equation;
        if (rotation != 0.0) {
            this.sina = Math.sin(-rotation);
            this.cosa = Math.cos(-rotation);
        }
    }
    
    public Equation(double x, double y, BiFunction<Double, Double, Boolean> equation) {
        this(x, y, equation, 0.0);
    }
    
    @Override
    public boolean contains(double x, double y) {
        Point normalized = new Point(x - this.x, y - this.y);

        if (this.sina != 0.0)
            normalized.move(this.cosa * normalized.getX() - this.sina * normalized.getY(), this.sina * normalized.getX() + this.cosa * normalized.getY());

        return this.equation.apply(normalized.getX(), normalized.getY());
    }
}