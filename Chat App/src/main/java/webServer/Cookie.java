package webServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Cookie
 */
public class Cookie {
    String name;
    public String value;
    List<String> options;

    public Cookie(String name) {
        
            this.name = name;
            value = "";
            options = new ArrayList<String>();

    }
    
    public String toString()
    {
        String out = "Set-Cookie: "+name+"="+value;
        AtomicReference<String> temp = new AtomicReference<String>();
        options.forEach((s) -> temp.get().concat("; "+s));
        out+=temp.get()+"\r\n";
        return out;
    }
    
}