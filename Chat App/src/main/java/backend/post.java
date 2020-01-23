
package backend;
import java.time.*;

public class Post
{

    String title;
    User poster;
    String postID;
    String content;
    Instant postTime;
    private ArrayList<reply> replies = new ArrayList<reply>();
    //ways to measure engagement/popularity: views, comments, likes/upvotes?(total # would be invisible to users)

    //idea: include a toHTML function to format user posts or a toSQLFormat method for database storage
    //toDisplayableID
    //sorting: by replies/likes/views{aka popularity}, by most recent post{postdate}, 

    public Post(User post, String title, String content)
    {
        poster=post;
        this.title=title;
        this.content=content;
        postTime=java.time.Clock.systemUTC().instant();
        
    }
    public String getID()
    {
        return postID;
    }
    public Instant getTime()
    {
        return postTime;
    }
}