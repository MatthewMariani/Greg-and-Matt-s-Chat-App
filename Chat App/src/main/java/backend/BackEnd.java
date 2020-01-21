/**
 * BackEnd
 */
package backend;

import java.util.ArrayList;

public class BackEnd {
    
    public static void main(String[] args) {
        
    }

    //make /s/ or whatever a scriptable part of the website, i.e. not predefined in the security manifest
    //what do I want for this
        //we take a user and return a feed
            //or a post history
            //or anything that the user needs to see
        //take a request for a post and put it in memory
        //keep a cache of current posts
        //every time  apost is added, it is added to a buffer
        //buffer is added to cache and SQL database
        //when a query is made, it first seaches in cache, then in SQL database
        //We need a way to store messages
}