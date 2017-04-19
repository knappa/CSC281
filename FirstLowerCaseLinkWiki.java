import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * A class to search the first lowercase link in wikipedia until you reach Philosophy
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 1.0
 */
public class FirstLowerCaseLinkWiki {

  public static void main(String[] args) {

    String search = "Stack_(abstract_data_type)";
    if (args.length == 1)
      search = args[0];

    Document doc;
    try {
      URL url = new URL("https://en.wikipedia.org/wiki/" + search);
      System.out.println(url);
      System.out.println();

      while (!"https://en.wikipedia.org/wiki/Philosophy".equals(url.toString())) {

        // get the document at the given site
        doc = Jsoup.connect(url.toString()).get();

        // gets rid of side tables if desired. Possibly more
        doc.select("table").remove();

        // get all links from main text
        Elements links = doc.select("div[id=mw-content-text]")
                           .select("a[href]");
        // Elements links = doc.select("a[href]");
        for (Element link : links) {

          // get the absolute url from href attribute
          url = new URL(link.attr("abs:href"));

          // test to see if the link is of the right type
          // look for the first URL of the correct type
          if (link.text().length() > 0
                && Character.isLowerCase(link.text().charAt(0)) // lowercase
                && "en.wikipedia.org".equals(url.getHost()) // wikipedia link
                && !url.getPath().contains(":") // safely ignore these
                && url.getQuery() == null
                && url.getRef() == null) {

            // print and break for loop at first URL of correct type
            System.out.printf("%20s %20s", link.text(), url);
            System.out.println();
            break;
          }
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
