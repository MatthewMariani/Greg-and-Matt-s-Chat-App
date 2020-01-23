
package backend;
import java.time.*;

public class Post
{
    String title;
    User poster;
    String postID;
    String content;
    Instant postTime;

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
    //idea: include a toHTML function to format user posts
}