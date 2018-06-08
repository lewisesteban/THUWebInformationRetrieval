package searchengine.conf;

import org.springframework.util.StringUtils;

class RecipeUrlIdentifier {

    enum IsRecipe {
        YES,
        NO,
        UNKNOWN;

        boolean Bool() {
            return this == YES;
        }
    }

    private static RecipeUrl[] urls = new RecipeUrl[]{
            //new RecipeUrl("http://www.worldcook.net/Cooking/", 1),
            //new RecipeUrl("https://www.allrecipes.com/recipe/"),
            new RecipeUrl("https://www.bbcgoodfood.com/recipes/", "category"),
            //new RecipeUrl("http://www.myrecipes.com/recipe/"),
            new RecipeUrl("https://www.cookingchanneltv.com/recipes/", "packages"),
            //new RecipeUrl("http://globaltableadventure.com/recipe/"),
            new RecipeUrl("https://www.jamieoliver.com/recipes/", "category")
    };

    static IsRecipe urlIsRecipe(String url) {
        if (url.contains("recipe/")) {
            return IsRecipe.YES;
        }
        for (RecipeUrl ref : urls) {
            IsRecipe isRecipe = ref.check(url);
            if (isRecipe != IsRecipe.UNKNOWN)
                return isRecipe;
        }
        return IsRecipe.UNKNOWN;
    }

    private static class RecipeUrl {

        private String urlBase;
        private String forbidden = null;
        private Integer slashesAfter = null;

        RecipeUrl(String urlBase) {
            this.urlBase = urlBase;
        }

        RecipeUrl(String urlBase, String forbidden) {
            this.urlBase = urlBase;
            this.forbidden = forbidden;
        }

        RecipeUrl(String urlBase, Integer slashesAfter) {
            this.urlBase = urlBase;
            this.slashesAfter = slashesAfter;
        }

        IsRecipe check(String url) {
            if (url.startsWith(urlBase)) {
                if (url.length() > urlBase.length()
                        && (forbidden == null || !url.contains(forbidden))
                        && (slashesAfter == null || StringUtils.countOccurrencesOf(url.substring(urlBase.length()), "/") == slashesAfter))
                    return IsRecipe.YES;
                else
                    return IsRecipe.NO;
            }
            return IsRecipe.UNKNOWN;
        }
    }
}
