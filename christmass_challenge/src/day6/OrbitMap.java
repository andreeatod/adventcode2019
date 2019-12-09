package day6;

import common.ReadFromFile;

import java.util.*;

public class OrbitMap {

    public static void main(String[] args) {
        String fileName = "christmass_challenge/src/day6/daySix.txt";

        List<String> input = ReadFromFile.readStrings(fileName);
        Map<String, Node> nodes = new HashMap<>();

        for (String s : input) {
            String[] names = s.split("\\)");

            if (!nodes.containsKey(names[0])) {
                Node node = new Node(names[0]);
                nodes.put(names[0], node);
            }

            Node node;
            if (!nodes.containsKey(names[1])) {
                node = new Node(names[1]);
            } else {
                node = nodes.get(names[1]);
            }

            node.addDirectLink(names[0]);
            nodes.put(names[1], node);
        }

        int totalLinks = 0;
        for (String key : nodes.keySet()) {
            totalLinks += getNeighbour(key, nodes);
        }

        System.out.println("Total links " + totalLinks);

        addIndirectLinks(nodes);

        Set<String> youLinks = nodes.get("YOU").getNeighbours();
        Set<String> sanLinks = nodes.get("SAN").getNeighbours();

        Set<String> santaNodes = new HashSet<>(sanLinks);
        santaNodes.removeAll(youLinks);

        Set<String> youNodes = new HashSet<>(youLinks);
        youNodes.removeAll(sanLinks);

        int transfers = youNodes.size() + santaNodes.size();
        System.out.println("Orbital transfers required " + transfers);
    }

    private static void addIndirectLinks(Map<String, Node> nodes) {
        for (String key : nodes.keySet()) {
            Set<String> indirectLinks = new HashSet<>();
            final String keyCopy = key;

            while (nodes.get(key).getDirectLink() != null) {
                String nextLink = nodes.get(key).getDirectLink();
                indirectLinks.add(nextLink);
                key = nextLink;
            }

            indirectLinks.forEach(n -> nodes.get(keyCopy).addNeighbour(n));
        }
    }

    private static int getNeighbour(String name, Map<String, Node> nodes) {
        String directLink = nodes.get(name).getDirectLink();
        if (directLink == null) {
            return 0;
        }
        return getNeighbour(directLink, nodes) + 1;
    }
}
