package regions;

import regions.utils.BooleanExpression;

public class ComplexRegion implements Region {

    private BooleanExpression expression;
    private Region[] regions;
    
    public ComplexRegion(BooleanExpression expression, Region... regions) {
        this.expression = expression;
        this.regions = regions;
    }
    
    @Override
    public boolean contains(double x, double y) {
        boolean[] containing = new boolean[this.regions.length];
        
        for (int i = 0; i < this.regions.length; ++i) {
            containing[i] = this.regions[i].contains(x, y);
        }

        return this.expression.apply(containing);
    }
}