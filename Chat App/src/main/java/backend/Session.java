package backend;

import java.util.ArrayList;
import webServer.Cookie;

/**
 * Session
 */
public class Session {

    String sessionID;
    Location location;
    ArrayList<webServer.Cookie> sessionCookies;
    User host;

    public Session()
    {
        sessionID = (int)(Math.random()*10000)+""; //temporary line unitl permenant solution can be disocvered.
        location=new Location();
        sessionCookies=new ArrayList<webServer.Cookie>();
    }
    public static Session sessionBuilder(String ID)
    {
        return new Session(); //temp line, should create a session, add it to current sessions and then lookup a user and make that the host.
    }
    public Cookie getCookie(String name)
    {
        for (Cookie cookie : sessionCookies) {
            if(cookie.getName()==name)
                return cookie;
        }
        return null;
    }

}