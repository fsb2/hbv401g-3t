package entities;

import javafx.scene.image.Image;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the concrete class for the users.
 */

public class UserEntity {
    
    private static final String EXP_SIGN = "/\'\"?*+-<>@!#$%&()=_^,.[]";
    private static final String EXP_UPPER = "A-ZÐÞÆÖÁÍÓÚ";
    private static final String EXP_LOWER = "a-zðþæöáíóú";
    private static final String EXP_NUMBER = "0-9";

    private int addressNumber;
    private Image photo; // Providing a photo is optional.
    private String screenName, email, password;
    private String lastName, firstName, birthdate, address, phone;

    // Default constructor.
    public UserEntity(){
    }

    // This constructor takes in values for all the private fields.
    public UserEntity(String firstName, String lastName, String screenName, 
            String password, String birthdate, String address,
            int addressNumber, String phone, String email) {

        this.firstName = isNameValid(firstName) ? firstName : null;
        this.lastName = isNameValid(lastName) ? lastName : null;
        this.password = isPasswordValid(password) ? password : null;
        this.birthdate = isDateFormatValid(birthdate) ? birthdate : null;
        this.addressNumber = addressNumber;
        this.address = isAddressValid(address, addressNumber) 
                ? (address + " " + String.valueOf(addressNumber)) : null;
        this.phone = isPhoneValid(phone) ? phone : null;
        this.email = isEmailValid(email)? email : null;
    }

    // Returning the given screen name (a.k.a. user name).
    public String getScreenName() {
        return screenName;
    }
    
    public void setScreenName(String screenName){
        this.screenName = isNameValid(screenName) ? screenName : null;
    }

    // Returning the given email address.
    public String getEmail() {
        return email;
    }

    // Allowing the email address to be changed.
    // If the new email address is valid, then it will be changed,
    // otherwise no changes will be made.
    public void setEmail(String email) {
        this.email = isEmailValid(email) ? email : null;
    }

    // Returning the given password.
    public String getPassword() {
        return password;
    }
    
    // Format: combination with more that three of following characters:
    //  characters: /'?*+-<>\@!#$%&()=_^,.[]"
    //  numbers: from 0 to 9;
    // Allowing the password to be changed.
    // If the new password is valid, then it will be changed,
    // otherwise no changes will be made.
    public void setPassword(String password) {
        this.password = isPasswordValid(password) ? password : null;
    }

    // Returning the given last name.
    public String getLastName() {
        return lastName;
    }

    // Allowing the last name to be changed.
    // If the new last name is valid, then it will be changed,
    // otherwise no changes will be made.
    public void setLastName(String lastName) {
       this.lastName = isNameValid(lastName) ? lastName : null;
    }

    // Returning the given first name.
    public String getFirstName() {
        return firstName;
    }

    // Allowing the first name to be changed.
    // If the new first name is valid, then it will be changed,
    // otherwise no changes will be made.
    public void setFirstName(String firstName) {
        this.firstName = isNameValid(firstName) ? firstName : null;
    }

    // Returning the given address.
    public String getAddress() {
        return address;
    }

    // Allowing the address to be changed.
    // If the new address is valid, then it will be changed,
    // otherwise no changes will be made.
    public void setAddress(String address, int addressNumber) {
        this.addressNumber = addressNumber;
        this.address = isAddressValid(address, this.addressNumber) 
                ? (address + " " + String.valueOf(this.addressNumber)) : null;
    }
  
    // Returning the given phone number.
    public String getPhone() {
        return phone;
    }

    // Allowing the phone number to be changed.
    // If the new phone number is valid, then it will be changed,
    // otherwise no changes will be made.
    public void setPhone(String phone) {
        this.phone = isPhoneValid(phone) ? phone : null;
    }

    // Returning the gien birthday date.
    public String getBirthdate() {
        return birthdate;
    }

    // Format: DD-MM-YYY
    // Allowing the birthday date to be changed.
    // If the new birthday date is valid, then it will be changed,
    // otherwise no changes will be made.
    public void setBirthdate(String birthdate) {
        this.birthdate = isDateFormatValid(birthdate) ? birthdate : null;
    }
    
    // Returning the given photo;
    public Image getPhoto(){
        return photo;
    }
    
    // Format: .jpg, .jpeg, .png
    // Allowing the photo to be changed.
    // If the format of the new photo is valid, then it
    // will be changed, otherwise no changes will be made.
    public void setPhoto(Image photo){
        this.photo = isPhotoValid(photo) ? photo : null;
    }
    
    //////////////////////////
    /// VALIDATION METHODS ///
    //////////////////////////
    
    // Checing if a given date is formatted curectly.
    private boolean isDateFormatValid(String bd) {
        String day = bd.substring(0, bd.indexOf("-"));
        
        String month = bd.substring(day.length() + 1,
                bd.indexOf("-", day.length() + 1));

        String year = bd.substring(bd.indexOf("-", day.length()
                + month.length()) + 1);

        if (day.length() != 2 || month.length() != 2 || year.length() != 4) {
            return false;
        }

        return isDateNumeric(day, month, year);
    }

    // Check if the given date is numeric.
    private boolean isDateNumeric(String d, String m, String y) {
        boolean isValid;
        
        try {
            int di = Integer.parseInt(d);
            int mi = Integer.parseInt(m);
            int yi = Integer.parseInt(y);
            isValid = isDateValid(di, mi, yi);

        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
            return false;
        }

        return isValid;
    }
    
    // General date validation.
    private boolean isDateValid(int di, int mi, int yi){
         boolean isLeap, isValid;

            // First, check if a year is a leap year.
            if (yi % 4 == 0){
                if(yi % 100 == 0){
                    isLeap = yi % 400 == 0;
                }else{
                    isLeap = true;
                }
            }else{
                isLeap = false;
            }

            // Then, check if the given date is valid.
            switch (mi) {
                case 2:
                    if (isLeap) {
                        isValid = di > 0 & di < 30;
                    } else {
                        isValid = di > 0 & di < 29;
                    }
                    break;                    
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    isValid = di > 0 & di < 32;
                    break;                    
                case 4:
                case 6:
                case 9:
                case 11:
                    isValid = di > 0 & di < 31;
                    break;
                default:
                    isValid = false;
                    break;
            }
            
        return isValid;
    }
    
    // Check if the given email address is valid.
    private boolean isEmailValid(String e){

        if(e.isEmpty() || e.isBlank()){
            return false;
        }
        
        // Stripping out all the leading and trailing white spaces.
        e = e.strip();

        if(!e.contains("@")){
            return false;
        }        
     
        if(!e.endsWith(".is") || !e.endsWith(".com")){
            return false;
        }       
        
        Matcher mS = Pattern.compile(EXP_SIGN + "+").matcher(e);
        
        return !mS.matches();
    }
    
    // Check if a given name (first name, last name, address)
    // is formated accordingly. 
    private boolean isNameValid(String n){
        if(n.isEmpty() || n.isBlank()){
            return false;
        }
        
        Matcher mU = Pattern.compile(EXP_UPPER + "+").matcher(n.substring(0, 1));
        Matcher mL = Pattern.compile(EXP_LOWER + "+").matcher(n.substring(1));
        Matcher mN = Pattern.compile(EXP_NUMBER + "+").matcher(n);

        return mU.matches() && mL.matches() && !mN.matches();
    }

    // Checks if the password is formated by the rules.
    private boolean isPasswordValid(String p){
         
        Matcher mU = Pattern.compile(EXP_UPPER + "+").matcher(p);
        Matcher mL = Pattern.compile(EXP_LOWER + "+").matcher(p);
        Matcher mN = Pattern.compile(EXP_NUMBER + "+").matcher(p);

        return mU.matches() && mL.matches() && mN.matches();
    }

    // Checks if the name of the given address is valid
    // and if the house number is higher than zero.
    private boolean isAddressValid(String a, int n){
        
        // Checking if the street number is valid.
        if(n < 1){
            return false;
        }
        
        // Checking if the address name is
        // made of two or more words.
        if(a.contains(" ")){
            String[] tmp = a.split(" ", 0);
                     
            for(String str : tmp) {
                if (!isNameValid(str)) {
                    return false;
                }
            }
            
            // This is the return value if
            // the long address name checks out.
            return true;
        }
        
        // This is the return value if 
        // the address name is only one word.
        return isNameValid(a);
    }
    
    // Check if a given phone number is formated accordingly. 
    private boolean isPhoneValid(String n){
  
        if(!n.contains("-")){
            return false;
        }
        
        String first = n.substring(0, n.indexOf("-"));
        String second = n.substring(n.indexOf("-") + 1);
        
        return first.length() == 3 && second.length() == 4;
    }
    
    // Checking if the given photo has the right format.
    // The right format is either jpg (jpeg) or png.
    private boolean isPhotoValid(Image img){
        String n = img.toString();
        
        return n.endsWith(".jpg") || n.endsWith(".jpeg") || n.endsWith(".png");
    }
}
