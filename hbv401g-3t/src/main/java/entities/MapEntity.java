package entities;

/**
 * This is the concrete class for the map.
 */

public class MapEntity {
    private final String name;
    private final String category;
    private final double coordX, coordY;
    private final int area;

    public MapEntity(String name, String category, double x, double y, int area) {
        this.name = name;
        this.category = category;
        coordX = x;
        coordY = y;
        this.area = area;
    }

    public String getName(){
        return name;
    }
    public String getCategory(){
        return category;
    }
    public double getCoordX(){
        return coordX;
    }
    public double getCoordY(){
        return coordY;
    }
    public int area(){
        return area;
    }
}
