package webServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
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
        main.activeUsers.add(this);

    }
    public User(String ID)
    {
        this.ID=ID;
        main.activeUsers.add(this);
    }
    public static User userBuilder(String ID)
    {
        AtomicReference<User> targetUser = new AtomicReference<User>(null);
        main.activeUsers.forEach((k) -> {if(k.getID()==ID){targetUser.set(k);}});
        if(targetUser.get()==null)
            targetUser.set(new User(ID));
        return targetUser.get();
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