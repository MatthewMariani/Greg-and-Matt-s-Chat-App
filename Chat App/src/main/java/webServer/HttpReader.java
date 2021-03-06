package webServer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

/**
 * HttpReader
 */

public class HttpReader {
    String content;
    public Map<String, String> headerContents;

    public HttpReader(String content) {
        this.content = content;
        Scanner reader = new Scanner(content);
        headerContents = new HashMap<String, String>();

        String current = reader.nextLine();
        String[] request = current.split(" ");
        String[] protocol = request[2].split("/");
        headerContents.put("request", request[0]);
        headerContents.put("URI", request[1]);
        headerContents.put("protocol", protocol[0]);
        headerContents.put("version", protocol[1]);
        request = null;
        protocol = null;
        current = reader.nextLine();
        for (int i = 0; !current.isBlank(); i++) {
            String[] delim = current.split(": ");
            // System.out.println(Arrays.toString(delim));
            headerContents.put(delim[0], delim[1]);
            current = reader.nextLine();
        }
        reader.close();

    }
    // public URI getURI()
    // {
    //     String
    // }
    // just a temporary test to see if it parses correctly
    public static String getCookie(String httpLine, String cookie) throws IOException
    {
        if(httpLine==null)
            throw new IOException();
        String[] cookies = httpLine.split("; ");
        String out = "";
        System.out.println("totalcookies"+Arrays.toString(cookies));
        for (String string : cookies) {
                out = string.split("=")[1];
                System.out.println("VALUE: "+out);
        }
        if(out.length()<1)
            throw new IOException();
        return out;
    }
    public String getCookie(String cookie) throws IOException
    {
        return getCookie(headerContents.get("Cookie"),cookie);
    }
    public static void main(String[] args) {
        String req = "GET / HTTP/1.1\r\n" + "Host: localhost:8009\r\n" + "Connection: keep-alive\r\n"
                + "Cache-Control: max-age=0\r\n" + "Upgrade-Insecure-Requests: 1\r\n"
                + "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36\r\n"
                + "Sec-Fetch-User: ?1\r\n"
                + "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n"
                + "Sec-Fetch-Site: none\r\n" + "Sec-Fetch-Mode: navigate\r\n" + "Accept-Encoding: gzip, deflate, br\r\n"
                + "Accept-Language: en-US,en;q=0.9\r\n" + "\r\n";
        HttpReader stuff = new HttpReader(req);
        System.out.println(stuff.headerContents.get("request"));
        String argos = "";
        for (Entry<String, String> string : stuff.headerContents.entrySet()) {
            argos+=string.getKey()+": "+string.getValue()+"\r\n";
        }
        System.out.println(argos);
    }
}