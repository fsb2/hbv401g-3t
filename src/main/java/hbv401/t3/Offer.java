package hbv401.t3;

import java.util.Currency;
import java.util.Date;

public class Offer {
    private final String name;
    private final String category;
    private final double coordx, coordy;
    private final Date startDate;
    private final Date endDate;
    private final Currency cost;


    public Offer(String name, String category, double coordx, double coordy, Date startDate, Date endDate, Currency cost) {
        this.name = name;
        this.category = category;
        this.coordx = coordx;
        this.coordy = coordy;
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
    public double getCoordx(){
        return coordx;
    }
    public double getCoordy(){
        return coordy;
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
