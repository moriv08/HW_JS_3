package HW_1;

public class Apple extends Number{
    private float weight = 1.0f;

    @Override
    public int intValue() {
        return 1;
    }

    @Override
    public long longValue() {
        return 1;
    }

    @Override
    public float floatValue() {
        return weight;
    }

    @Override
    public double doubleValue() {
        return 1.0;
    }

}
