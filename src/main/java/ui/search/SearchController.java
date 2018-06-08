package ui.search;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import searchengine.conf.PlaceNames;
import searchengine.HteiSearchEngine;
import searchengine.document.Document;
import searchengine.io.SEInput;
import searchengine.searchdata.Query;

import java.io.IOException;

@Controller
@RequestMapping("/search")
public class SearchController {

    private SEInput engine = null;

    private synchronized SEInput getEngine() throws IOException {
        if (engine == null) {
            engine = new HteiSearchEngine();
        }
        return engine;
    }

    @RequestMapping("")
    public String home(@RequestParam(name="place", required=false) String place,
                       @RequestParam(name="ingredients", required=false) String ingredients,
                       Model model) {
        model.addAttribute("place", place);
        model.addAttribute("ingredients", ingredients);
        return "search/home";
    }

    @RequestMapping("/results")
    public String search(@RequestParam(name="place", required=false) String place,
                         @RequestParam(name="ingredients", required=false) String ingredients,
                         Model model) {
        if (!PlaceNames.isAcceptable(place)) {
            model.addAttribute("message", "Unknown place: " + place + ". Name of country required.");
            model.addAttribute("ingredients", ingredients);
            return "search/error";
        }

        try {
            Query query = getEngine().newQuery(PlaceNames.getOptions(place), ingredients.split(" "));
            if (query.getNbResults() > 0) {
                Iterable<Document> results = query.searchNext(10);
                model.addAttribute("place", place);
                model.addAttribute("ingredients", ingredients);
                int linkNb = 0;
                for (Document doc : results) {
                    model.addAttribute("title" + Integer.toString(linkNb), doc.getTitle());
                    model.addAttribute("result" + Integer.toString(linkNb), doc.getUrl());
                    linkNb++;
                }
                return "search/results";
            } else {
                model.addAttribute("message", "No results.");
                model.addAttribute("place", place);
                model.addAttribute("ingredients", ingredients);
                return "search/error";
            }
        } catch (ParseException e) {
            model.addAttribute("message", "Error parsing query: " + e.getMessage());
            model.addAttribute("place", place);
            model.addAttribute("ingredients", ingredients);
            return "search/error";
        } catch (IOException e) {
            model.addAttribute("message", "Error instanciating engine: " + e.getMessage());
            model.addAttribute("place", place);
            model.addAttribute("ingredients", ingredients);
            return "search/error";
        }
    }
}