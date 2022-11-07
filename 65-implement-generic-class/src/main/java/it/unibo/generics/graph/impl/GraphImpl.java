package it.unibo.generics.graph.impl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {
    
    private static final int INIT_DISTANCE = -1;
    private static final int SRC_DISTANCE = 0;
    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;
    private final List<Edge<N>> edges;
    private final List<N> nodes;

    public GraphImpl() {
        this.edges = new ArrayList<>();
        this.nodes = new ArrayList<>();
    }

    public void addNode(final N node) {
        this.nodes.add(node);
    }

    public void addEdge(final N source, final N target) {
        if (target == null) {
            return;
        }
        final Edge<N> edge = new Edge<>(source, target);
        this.edges.add(edge);
    }

    public Set<N> nodeSet() {
        final Set<N> nodeSet = new HashSet<>(this.nodes);
        return nodeSet;
    }

    public Set<N> linkedNodes(final N node) {
        Set<N> linkedNodes = new HashSet<>();
        for (final Edge<N> e : this.edges) {
            if (e.getSource() == node) {
                linkedNodes.add(e.getTarget());
            }
        }
        return linkedNodes;
    }

    public List<N> getPath(final N source, final N target) {
        final int [] colors = new int [this.nodes.size()];
        final int [] distances = new int[this.nodes.size()];
        final List<N> prev = new ArrayList<>();
        final Queue<N> queue = new ArrayDeque<N>();

        for (int i = 0; i < this.nodes.size(); i++) {
            colors[i] = WHITE;
            distances[i] = INIT_DISTANCE;
            prev.add(null);
        }
        colors[getIndex(source)] = GRAY;
        distances[getIndex(source)] = SRC_DISTANCE;
        queue.add(source);

        while(!queue.isEmpty()) {
            N currentHead = queue.poll();
            N[] adj = (N[])linkedNodes(currentHead).toArray();
            
            for (N n : adj) {
                if (colors[getIndex(n)] == WHITE) {
                    colors[getIndex(n)] = GRAY;
                    distances[getIndex(n)] = distances[getIndex(currentHead)]++;
                    prev.set(getIndex(n), currentHead);
                    queue.add(n);
                }
            }
            colors[getIndex(currentHead)] = BLACK;
        }

        if (distances[getIndex(target)] == INIT_DISTANCE) {
            return null;
        }

        final List<N> path = new LinkedList<>();
        final List<N> reversePath = new LinkedList<>();
        N current = target;
        
        while (current != null) {
            reversePath.add(current);
            current = prev.get(getIndex(current));
        }

        for (int i = reversePath.size() - 1; i >= 0; i--) {
            path.add(reversePath.get(i));
        }
        
        return path;
    }

    private final int getIndex(N node) {
        final N[] n = (N[])this.nodes.toArray();
        for (int i = 0; i < n.length; i++) {
            if (n[i] == node) {
                return i;
            }
        }
        return -1;
    }
    
}
