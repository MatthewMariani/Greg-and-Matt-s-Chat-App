package backend;

import java.time.Instant;

/**
 * messageBuffer
 */
public class MessageBuffer {

    ConcurrentListHash buffer;
    
    public MessageBuffer()
    {
        buffer = new ConcurrentListHash();
    }

    public Post[] getFeed(Instant startingAge, int numPosts)
    {
        Post[] out = new Post[numPosts];
        int startIndex = buffer.getClosestPostIndex(startingAge);
        for (int i = 0; i < numPosts; i++) {
            out[i]=buffer.get(startIndex+i);
        }
        return out;
    }

    public String getNewID()
    {
        String out = Math.random()*4294967296d+""; //2^32 = 4294967296
        if(buffer.contains(out))
            out=getNewID();
        return out;

    }

    //need multi-level linked hasmaps/arraylists
    //recreate arraylist?
    public Post getPost(String ID)
    {
        return buffer.get(ID);
    }

    public void createPost(User poster, String title, String content)
    {
        buffer.add(new Post(poster, title, content));
    }


}