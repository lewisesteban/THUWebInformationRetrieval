package searchengine.conf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordGraph {

    private Node root = new Node();
    private int maxCount;

    public WordGraph(int maxCount) {
        this.maxCount = maxCount;
    }

    private char getChar(byte[] bytes, int pos) {
        return (bytes[pos] < 0 ? (char)(-bytes[pos] + 127) : (char)bytes[pos]);
    }

    public double weigh(String str) {
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
                } else {
                    readingWords.remove(wordsIt);
                    --wordsIt;
                }
            }
            if (root.get(c) != null) {
                readingWords.add(root.get(c));
                weight += getWeightOf(root.get(c), counts);
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

        Node() {
            weight = 0;
            children = new Node[256];
            Arrays.fill(children, null);
        }

        void addWeight(double weight) {
            this.weight += weight;
        }

        void addChild(char at) {
            children[at] = new Node();
        }

        Node get(char at) {
            return children[at];
        }
    }
}
