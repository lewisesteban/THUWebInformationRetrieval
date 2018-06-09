package searchengine.conf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordGraph {

    private Node root = new Node(null, '\0');
    private int maxCount;

    public WordGraph(int maxCount) {
        this.maxCount = maxCount;
    }

    private char getChar(byte[] bytes, int pos) {
        return (bytes[pos] < 0 ? (char)(-bytes[pos] + 127) : (char)bytes[pos]);
    }

    public double weigh(String str) {
        return weigh(str, null);
    }

    public double weigh(String str, Double stopAt) {
        HashMap<Node, Integer> counts = new HashMap<>();
        byte[] bytes = str.getBytes();
        ArrayList<Node> readingWords = new ArrayList<>(26);
        double weight = 0;
        for (int strIt = 0; strIt < bytes.length; ++strIt) {
            char c = getChar(bytes, strIt);

            for (int wordsIt = 0; wordsIt < readingWords.size(); ++wordsIt) {
                if (readingWords.get(wordsIt).get(c) != null) {
                    readingWords.set(wordsIt, readingWords.get(wordsIt).get(c));
                    weight += getWeightOf(readingWords.get(wordsIt), counts);
                    if (stopAt != null && weight >= stopAt)
                        return weight;
                } else {
                    readingWords.remove(wordsIt);
                    --wordsIt;
                }
            }
            if (root.get(c) != null) {
                readingWords.add(root.get(c));
                weight += getWeightOf(root.get(c), counts);
                if (stopAt != null && weight >= stopAt)
                    return weight;
            }
        }
        return weight;
    }

    private double getWeightOf(Node node, Map<Node, Integer> counts) {
        int count = counts.getOrDefault(node, 0);
        if (count >= maxCount) {
            return 0;
        } else {
            counts.put(node, count + 1);
            return node.weight;
        }
    }

    public String findFirst(String str) {
        byte[] bytes = str.getBytes();
        ArrayList<Node> readingWords = new ArrayList<>(26);
        for (int strIt = 0; strIt < bytes.length; ++strIt) {
            char c = getChar(bytes, strIt);

            for (int wordsIt = 0; wordsIt < readingWords.size(); ++wordsIt) {
                if (readingWords.get(wordsIt).get(c) != null) {
                    readingWords.set(wordsIt, readingWords.get(wordsIt).get(c));
                    if (readingWords.get(wordsIt).weight != 0)
                        return readingWords.get(wordsIt).getString();
                } else {
                    readingWords.remove(wordsIt);
                    --wordsIt;
                }
            }
            if (root.get(c) != null) {
                readingWords.add(root.get(c));
                if (root.get(c).weight != 0)
                    return root.get(c).getString();
            }
        }
        return null;
    }

    public void feed(String word, double weight) {
        feed(root, word, weight);
    }

    private void feed(Node node, String word, double weight) {
        if (word.isEmpty()) {
            node.addWeight(weight);
        } else {
            char c = getChar(word.getBytes(), 0);
            if (node.get(c) == null) {
                node.addChild(c);
            }
            feed(node.get(c), word.substring(1), weight);
        }
    }

    private class Node {

        private double weight;
        private Node[] children;
        private Node parent;
        private char character;

        Node(Node parent, char character) {
            weight = 0;
            children = new Node[256];
            this.parent = parent;
            this.character = character;
            Arrays.fill(children, null);
        }

        void addWeight(double weight) {
            this.weight += weight;
        }

        void addChild(char at) {
            children[at] = new Node(this, at);
        }

        Node get(char at) {
            return children[at];
        }

        String getString() {
            if (parent.parent == null) {
                return new String(new char[]{character});
            } else {
                return parent.getString() + new String(new char[]{character});
            }
        }
    }
}
