package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class which gives a user the ability to make
 * different profiles for one self, as many as the user wants.
 *
 */

public class ProfileEntity {
    private String profileName;
    private UserEntity user;
    private List categories;

    // The default constructor.
    public ProfileEntity(){
    }
    
    public ProfileEntity(String profileName, UserEntity user,
            ArrayList<String> categories){
        
        this.profileName = profileName;
        this.user = user;
        this.categories = new ArrayList<String>();
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
