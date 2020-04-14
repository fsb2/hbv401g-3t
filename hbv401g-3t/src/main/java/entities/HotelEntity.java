package entities;

/**
 * This is the concrete class for the hotels.
 */

public class HotelEntity {
    private String name, location;
    
    public HotelEntity(){        
    }
    
    public HotelEntity(String name, String location){
        this.name = name;
        this.location = location;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getLocation(){
        return location;
    }
    
    public void setLocaction(String location){
        this.location = location;
    }
}
