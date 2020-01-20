package backend;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ConcurrentListHash
 */
public class ConcurrentListHash {

    ArrayList<Post> chrono;
    HashMap<String, Post> search;

    public ConcurrentListHash()
    {
        chrono=new ArrayList<Post>();
        search = new HashMap<String, Post>();
    }
    public void add(Post in)
    {
        chrono.add(in);
        search.put(in.getID(),in);
    }
    public Post get(int i)
    {
        return chrono.get(i);
    }
    public Post get(String ID)
    {
        return search.get(ID);
    }
    public int getSize()
    {
        return chrono.size();
        
    }
    public boolean contains(Post o)
    {
        return search.containsKey(o.getID());
    }
    public boolean contains(String o)
    {
        return search.containsKey(o);
    }
    public int getClosestPostIndex(Instant in)
    {
        //preform binary search
        int min = 0;
        int max = getSize()-1;
        boolean found = false;
        while(!found)
        {
            int target = (min+max)/2;
            int test = in.compareTo(chrono.get(target).getTime());
            if(test!=0&&(min==target||max==target))
            {
                return Math.min(getSize()-1,target+1);
            }
            if(test>0)
                min=target;
            else if(test<0)
                max=target;
            else if(test==0)
                return target;

        }
        return -1;
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentListHash temp = new ConcurrentListHash();
        User dead = new User("BIGMANDEAD");
        Instant temp2 = null;
        for (int i = 0; i < 12; i++) {
            temp.add(new Post(dead, "TEST: "+i,i+""));
            Thread.sleep(100);
            if(i==0)
            {
                temp2=java.time.Clock.systemUTC().instant();
            }
            Thread.sleep(100);
        }
        Instant foo = temp.chrono.get(4).getTime();
        int bar = temp.getClosestPostIndex(temp2);
        System.out.println(bar);
        System.out.println(temp.get(bar).title);
    }
    

}