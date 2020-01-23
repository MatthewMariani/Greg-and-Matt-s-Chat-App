package backend;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * UserBuffer
 */
public class UserBuffer {
    File userList; // need file format for user database
    ArrayList<User> users;
    HashMap<String, User> usersByID;
    HashMap<String, User> usersByName;
    public UserBuffer()
    {
        users = new ArrayList<User>();
        usersByID = new HashMap<String, User>();
        usersByName = new HashMap<String, User>();
        
    }
    public void add(User e)
    {
        users.add(e);
        usersByID.put(e.getID(),e);
        usersByName.put(e.getName(),e);
    }
    public User getByName(String name)
    {
        return usersByName.get(name);
    }
    public User getByID(String ID)
    {
        return usersByID.get(ID);
    }
    public boolean contains(String ID)
    {
        if(getByID(ID)!=null)
            return true;
        else    
            return containsInDatabase(ID);
    }
    private boolean containsInDatabase(String ID)
    {
        return false;
        //database code needs to made. Use a custom filetype? or SQL?
    }
}