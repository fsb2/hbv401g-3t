package entities;

import java.util.Currency;
import java.util.Date;

/**
 * This is the concrete class for the offer functionality.
 */

public class OfferEntity {
    private String name;
    private String category;
    private double coordX, coordY;
    private Date startDate;
    private Date endDate;
    private Currency cost;

    // Default constructor.
    public OfferEntity(){        
    }
    
    public OfferEntity(String name, String category, double coordX, 
            double coordY, Date startDate, Date endDate, Currency cost) {
        
        this.name = name;
        this.category = category;
        this.coordX = coordX;
        this.coordY = coordY;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
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
    public Date getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public Currency getCost() {
      return cost;
    }
    
    @Override
    public String toString() {
        return "Offer " + name + " only " + cost + "!";
    }
}
