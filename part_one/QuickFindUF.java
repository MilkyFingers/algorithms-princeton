package part_one;

//This implementaion sacrifices the spped of the union operation ( it is O(n^2) ) to make connected constant

public class QuickFindUF {
    private int[] id;

    public QuickFindUF(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    //In this in instance, the find operation can be done in constant time, while the union operation is O(n^2)
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    //This method is simply too slow. We can see that this union algorithm will
    //take on the order of O(n^2). A better, lazy approach is adopted in QuickUnionUF
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }
}