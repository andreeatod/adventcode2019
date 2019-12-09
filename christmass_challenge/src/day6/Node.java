package day6;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Node {
    private String name;
    private String directLink;
    private Set<String> neighbours = new HashSet<>();

    public Node(String name) {
        this.name = name;
    }

    public void addDirectLink(String name) {
        this.directLink = name;
    }

    public String getDirectLink() {
        return this.directLink;
    }

    public void addNeighbour(String name) {
        this.neighbours.add(name);
    }

    public Set<String> getNeighbours() {
        if (this.neighbours.size() > 0) {
            return new HashSet<>(this.neighbours);
        }
        return Collections.emptySet();
    }
}
