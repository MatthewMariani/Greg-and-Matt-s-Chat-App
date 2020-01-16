
public class user
{
    //instance variables
    private String ID;
    private Location location;
    private String accessLevel;

    //constructor
    public user(String uID, Location loc, String acLvl)
    {
        ID = uID;
        location = loc;
        accessLevel = acLvl;
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