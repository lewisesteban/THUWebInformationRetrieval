package searchengine.conf;

import org.springframework.util.StringUtils;
import searchengine.document.Document;

class PageIdentifierKnownDomains {

    enum IsRecipe {
        YES,
        NO,
        UNKNOWN;

        boolean Bool() {
            return this == YES;
        }
    }

    private static RecipeUrl[] urls = new RecipeUrl[]{
            new RecipeUrl("http://www.worldcook.net/Cooking/", null, 1, true, false),
            //new RecipeUrl("https://www.allrecipes.com/recipe/"),
            new RecipeUrl("https://www.bbcgoodfood.com/recipes/", new String[]{"category", "collection"}),
            //new RecipeUrl("http://www.myrecipes.com/recipe/"),
            new RecipeUrl("https://www.cookingchanneltv.com/recipes/", new String[]{"packages", "photos"}),
            //new RecipeUrl("http://globaltableadventure.com/recipe/"),
            new RecipeUrl("https://www.jamieoliver.com/recipes/", new String[]{"category"})
    };

    static IsRecipe urlIsRecipe(Document document) {
        for (RecipeUrl ref : urls) {
            IsRecipe isRecipe = ref.check(document);
            if (isRecipe != IsRecipe.UNKNOWN)
                return isRecipe;
        }
        return IsRecipe.UNKNOWN;
    }

    private static class RecipeUrl {

        private String urlBase;
        private String[] forbidden;
        private Integer slashesAfter = null;
        private boolean checkPlaces = true;
        private boolean checkRecipe = false;

        RecipeUrl(String urlBase, String[] forbidden) {
            this.urlBase = urlBase;
            this.forbidden = forbidden;
        }

        RecipeUrl(String urlBase, String[] forbidden, Integer slashesAfter, boolean checkRecipe, boolean checkPlaces) {
            this.urlBase = urlBase;
            this.slashesAfter = slashesAfter;
            this.forbidden = forbidden;
            this.checkPlaces = checkPlaces;
            this.checkRecipe = checkRecipe;
        }

        IsRecipe check(Document document) {
            if (document.getUrl().startsWith(urlBase)) {
                if (document.getUrl().length() > urlBase.length()) {
                    if (slashesAfter != null && StringUtils.countOccurrencesOf(document.getUrl().substring(urlBase.length()), "/") != slashesAfter) {
                        return IsRecipe.NO;
                    }
                    if (forbidden != null) {
                        for (String str : forbidden)
                            if (document.getUrl().contains(str))
                                return IsRecipe.NO;
                    }
                    if (checkRecipe && !PageIdentifier.contentSuggestsCookingRecipe(document)) {
                        return IsRecipe.NO;
                    }
                    if (checkPlaces && !PlaceNames.isFromOneCountry(document)) {
                        return IsRecipe.NO;
                    }
                    return IsRecipe.YES;
                } else {
                    return IsRecipe.NO;
                }
            }
            return IsRecipe.UNKNOWN;
        }
    }
}
