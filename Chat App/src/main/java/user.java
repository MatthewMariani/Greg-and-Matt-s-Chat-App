package main.java;

public class user
{
    //instance variables
    private ID ID;
    private Location location;
    private String accessLevel;
    private Zone zone;

    //constructor
    public user(ID uID, Location loc, String acLvl)
    {
        ID = uID;
        location = loc;
        accessLevel = acLvl;
        zone = location.findZone();
    }

    //getters
    public ID getID()
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