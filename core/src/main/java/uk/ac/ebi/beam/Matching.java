package uk.ac.ebi.beam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Defines a matching on a graph.
 *
 * @author John May
 */
final class Matching {

    /** Indicate unmatched. */
    private static final int UNMATCHED = -1;

    /** Storage of which each vertex is matched with. */
    private final int[] match;

    /**
     * Create a matching of the given size.
     *
     * @param n number of items
     */
    private Matching(int n) {
        this.match = new int[n];
        Arrays.fill(match, UNMATCHED);
    }

    /**
     * Match the vertex 'u' with vertex 'v'.
     *
     * @param u a vertex
     * @param v another vertex
     */
    void match(int u, int v) {
        match[u] = v;
        match[v] = u;
    }

    /**
     * Access the current non-redundant matching of vertices.
     * 
     * @return matched pairs
     */
    Iterable<Tuple> matches() {

        List<Tuple> tuples = new ArrayList<Tuple>(match.length / 2);

        for (int v = 0; v < match.length; v++) {
            int w = match[v];            
            if (w > v && match[w] == v) {
                tuples.add(Tuple.of(v, w));
            }
        }

        return tuples;
    }

    /**
     * Allocate a matching enough for the given graph.
     *
     * @param g a graph
     * @return matching
     */
    static Matching empty(Graph g) {
        return new Matching(g.order());
    }
}
