package searchengine.conf;

public class CrawlerStartingPoints {

    private static String[] urls = {
            "http://www.worldcook.net/Worldcook%20front.htm",
            "https://www.jamieoliver.com/recipes/category/world/?",
            "https://www.allrecipes.com/recipes/86/world-cuisine/",
            "https://www.bbcgoodfood.com/recipes/category/cuisines",
            "http://www.myrecipes.com/world-cuisine",
            "https://www.cookingchanneltv.com/recipes/packages/best-international-recipes-global-and-cooking",
            "http://globaltableadventure.com/recipe/"
            //"https://www.ranker.com/list/best-recipe-websites/chef-jen"
    };

    public static String[] get() {
        return urls;
    }
}
