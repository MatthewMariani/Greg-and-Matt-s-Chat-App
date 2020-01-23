
package backend;
public class post
{
    private String title;
    private int postNum;
    //date/time object? for date var
    //post-body?
    //attached-image?
    private User poster;
    private ArrayList<reply> replies = new ArrayList<reply>();
    //ways to measure engagement/popularity: views, comments, likes/upvotes?(total # would be invisible to users)

    //idea: include a toHTML function to format user posts or a toSQLFormat method for database storage
    //toDisplayableID
    //sorting: by replies/likes/views{aka popularity}, by most recent post{postdate}, 
}