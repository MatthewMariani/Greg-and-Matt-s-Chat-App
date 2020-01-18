import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


   public class User
{   
    //instance variables
    private String ID;
    private Location location;
    private String accessLevel;
    List<Cookie> cookies;
    //constructor
    public User(String uID, Location loc, String acLvl)
    {
        ID = uID;
        location = loc;
        accessLevel = acLvl;
        cookies = new ArrayList<Cookie>();

    }

    //getters
    public String getID()
    {
        return ID;
    }

    public Location getLocation()
    {
        return location;
    }

    public String getPerms()
    {
        return accessLevel;
    }

    //setters(none for ID since that shouldn't change)
    public void setLocation(Location loc)
    {
        location = loc;
    }

    public void setPerms(String newAL)
    {
        accessLevel = newAL;
    }

}