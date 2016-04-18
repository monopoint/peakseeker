import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        Document doc = null;
        PrintWriter writer = null;

        try {
            writer = new PrintWriter("peaks.json", "UTF-8");
            doc = Jsoup.connect("http://www.erikbolstad.no/geo/noreg/norske-fjell-over-1000-meter/").get();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        if (doc == null) System.exit(0);

        Elements rows = doc.select(".entry-content table tr");

        writer.println("{");
        writer.println("\"peaks\" : [");

        Iterator rowsIterator = rows.iterator();

        while (rowsIterator.hasNext()){

            Element row = (Element) rowsIterator.next();
            writer.println("{");

            writer.println("\"name\" : \"" + row.children().get(0).text() + "\",");
            writer.println("\"lat\" : \"" + row.children().get(1).text() + "\",");
            writer.println("\"lon\" : \"" + row.children().get(2).text() + "\",");
            writer.println("\"height\" : \"" + row.children().get(3).text() + "\",");
            writer.println("\"area\" : \"" + row.children().get(5).text() + ", " + row.children().get(6).text() + "\"");

            writer.print("}");

            if (rowsIterator.hasNext()){
                writer.println(",");
            }

        }

        writer.println("");
        writer.println("]");
        writer.println("}");
        writer.close();






    }
}
