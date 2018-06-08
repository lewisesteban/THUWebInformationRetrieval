package searchengine.conf;

public class RecipePageIdentifier {

    private static CookingWord[] cookingVoc = {
            new CookingWord("add", 0.5),
            new CookingWord("bake"),
            new CookingWord("barbecue"),
            new CookingWord("beat"),
            new CookingWord("blend"),
            new CookingWord("board", 0.5),
            new CookingWord("boil"),
            new CookingWord("bowl"),
            new CookingWord("broil"),
            new CookingWord("centimeter"),
            new CookingWord("cm", 0.5),
            new CookingWord("chop"),
            new CookingWord("cloves"),
            new CookingWord("combin"),
            //new CookingWord("cook"),
            new CookingWord("cool"),
            new CookingWord("cover"),
            new CookingWord("crisp"),
            new CookingWord("crush"),
            new CookingWord("cubes"),
            new CookingWord("cubed"),
            new CookingWord("cup"),
            new CookingWord("cut", 0.5),
            new CookingWord("deciliter"),
            new CookingWord("degree"),
            new CookingWord("dice"),
            new CookingWord("dish"),
            new CookingWord("dissolve"),
            new CookingWord("drain"),
            new CookingWord("dried"),
            new CookingWord("fine"),
            new CookingWord("fill"),
            new CookingWord("firm"),
            new CookingWord("flesh"),
            new CookingWord("fresh"),
            new CookingWord("fold"),
            new CookingWord("fried"),
            new CookingWord("fry"),
            new CookingWord("gram"),
            new CookingWord("grate"),
            new CookingWord("greas"),
            new CookingWord("grill"),
            new CookingWord("ground"),
            new CookingWord("handful"),
            new CookingWord("heat", 0.5),
            new CookingWord("hour"),
            new CookingWord("inch"),
            new CookingWord("ingredients", 2),
            new CookingWord("jar", 0.5),
            new CookingWord("juice"),
            new CookingWord("knead"),
            new CookingWord("lbs"),
            new CookingWord("liter"),
            new CookingWord("method"),
            new CookingWord("minute"),
            new CookingWord("mins"),
            new CookingWord("mix"),
            new CookingWord("measure"),
            new CookingWord("melt"),
            new CookingWord("microwave"),
            new CookingWord("mince"),
            new CookingWord("mortar"),
            new CookingWord("oil"),
            new CookingWord("open", 0.3),
            new CookingWord("ounce"),
            new CookingWord("oven"),
            new CookingWord("oz"),
            new CookingWord("pan"),
            new CookingWord("peel"),
            new CookingWord("pestle"),
            new CookingWord("plate"),
            new CookingWord("pour"),
            new CookingWord("pound"),
            new CookingWord("portion"),
            new CookingWord("preheat"),
            new CookingWord("press"),
            new CookingWord("put", 0.3),
            //new CookingWord("recipe", 2),
            new CookingWord("ready in"),
            new CookingWord("refrigerate"),
            new CookingWord("ripe"),
            new CookingWord("roast"),
            new CookingWord("salt"),
            new CookingWord("sauce"),
            new CookingWord("sauté"),
            new CookingWord("scramble"),
            new CookingWord("simmer"),
            new CookingWord("season"),
            new CookingWord("serve"),
            new CookingWord("serving"),
            new CookingWord("set aside"),
            new CookingWord("skin"),
            new CookingWord("slice"),
            new CookingWord("smoked"),
            new CookingWord("soak"),
            new CookingWord("spatula"),
            new CookingWord("spread"),
            new CookingWord("spoon"),
            new CookingWord("squeeze"),
            new CookingWord("steam"),
            new CookingWord("stir"),
            new CookingWord("strainer"),
            new CookingWord("sugar"),
            new CookingWord("tablespoon"),
            new CookingWord("TBSP"),
            new CookingWord("TSP"),
            new CookingWord("teaspoon"),
            new CookingWord("tender"),
            new CookingWord("tin", 0.5),
            new CookingWord("toss"),
            new CookingWord("wash"),
            new CookingWord("water", 0.5),
            new CookingWord("warm"),
            new CookingWord("weigh"),
            new CookingWord("whip"),
            new CookingWord("whisk"),
            new CookingWord(" kg ", 0.3),
            new CookingWord(" g ", 0.3),
            new CookingWord(" ml ", 0.3),
            new CookingWord(" dl ", 0.3)
    };

    private static final double MIN_VOC_RATIO = 0.009;
    private static final double MIN_VOC = 15;

    private static WordGraph wordGraph = null;

    private static WordGraph getWordGraph() {
        if (wordGraph == null) {
            synchronized (WordGraph.class) {
                if (wordGraph == null) {
                    wordGraph = new WordGraph(3);
                    for (CookingWord word : cookingVoc) {
                        wordGraph.feed(word.word.toLowerCase(), word.importance);
                    }
                }
            }
        }
        return wordGraph;
    }

    public static double weigh(String content) {
        return getWordGraph().weigh(content);
    }

    public static boolean isCookingPage(String url, String content) {
        RecipeUrlIdentifier.IsRecipe urlIndicatesRecipe = RecipeUrlIdentifier.urlIsRecipe(url);
        if (urlIndicatesRecipe != RecipeUrlIdentifier.IsRecipe.UNKNOWN) {
            return urlIndicatesRecipe.Bool();
        } else {
            double voc = getWordGraph().weigh(content);
            double score = voc / content.length();
            System.out.println("voc=" + voc + "\tscore=" + score);
            return score >= MIN_VOC_RATIO && voc >= MIN_VOC;
        }
    }

    private static class CookingWord {
        String word;
        double importance;

        CookingWord(String word, double importance) {
            this.word = word;
            this.importance = importance;
        }

        CookingWord(String word) {
            this.word = word;
            this.importance = 1;
        }
    }
}
