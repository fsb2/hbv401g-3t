package entities;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 * This is a class which gives a user the ability to make
 * different profiles for one self, as many as the user wants.
 * 
 * @author hjortur
 */
public class ProfileEntity {
    private double id; // not used at the moment.
    private String profileName;
    private UserEntity user; // not used at the moment.
    private List categories; // not used at the moment.
    
    // The default constructor.
    public ProfileEntity(String profileName, UserEntity user){
        this.id = Math.random();
        this.profileName = profileName;
        this.user = user;
        categories = new ArrayList<>();
    }
    
    // Returns the given user's photo.
    public Image getImage(){
        return user.getPhoto();
    }
    
    // Returning the name of the user's profile.
    public String getProfileName(){
        return profileName;
    }
    
    // Allowing the profile name to be changed.
    public void setProfileName(String profileName){
        this.profileName = profileName;
    }
   
    // Returning the categories, choicen by the user.
    public List getCategories(){
        return categories;
    }
    
    // Allowing to add a new category to the list of categories.
    public void setCategory(String category){
        categories.add(category);
    }
    
    // Allowing the user to remove a category from the list of categoies.
    public void deleteCategory(String category){
        categories.remove(category);
    }
}
