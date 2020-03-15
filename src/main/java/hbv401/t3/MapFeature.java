package hbv401.t3;

public class MapFeature {
    private final String name;
    private final String category;
    private final double coordx, coordy;
    // private final List 

    public MapFeature(String name, String category, double x, double y) {
        this.name = name;
        this.category = category;
        coordx = x;
        coordy = y;
    }

    public String getName(){
        return name;
    }
    public String getCategory(){
        return category;
    }
    public double getCoordx(){
        return coordx;
    }
    public double getCoordy(){
        return coordy;
    }
}
