package part_one;

//In this improved implementation, we maintain a new data structure to keep track of 
//tree sizes. This allows us to always add the smaller tree to the larger tree, keeping
//average distance to root lower.

public class QuickUnionImprovedUF {
    private int id[];
    private int sz[];

    public QuickUnionImprovedUF(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            //over time, this will 'flatten' the tree until each node points to the tree's root itself
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        // already in the same tree
        if (i == j) {
            return;
        }
        // if the tree with root i is smaller, we add it's tree size to j
        if (sz[i] < sz[j]) {
            id[i] = j; 
            sz[j] += sz[i];
        }
        else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }


}