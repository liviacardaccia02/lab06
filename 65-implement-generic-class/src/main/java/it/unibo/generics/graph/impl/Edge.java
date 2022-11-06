package it.unibo.generics.graph.impl;

public class Edge<N> {
    
    private final N source;
    private final N target;

    public Edge(final N source, final N target) {
        this.source = source;
        this.target = target;
    }

    public final N getSource() {
        return this.source;
    }

    public final N getTarget() {
        return this.target;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge<N> other = (Edge<N>) obj;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (target == null) {
            if (other.target != null)
                return false;
        } else if (!target.equals(other.target))
            return false;
        return true;
    }
}
