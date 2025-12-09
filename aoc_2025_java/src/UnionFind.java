import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class UnionFind {
    int[] parent, rank, size;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }

    // Path compression
    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    // Union by rank + size tracking
    public boolean union(int x, int y) {
        int rx = find(x);
        int ry = find(y);

        if (rx == ry)
            return false;

        if (rank[rx] < rank[ry]) {
            parent[rx] = ry;
            size[ry] += size[rx];
            size[rx] = 0;
        } else if (rank[rx] >= rank[ry]) {
            parent[ry] = rx;
            size[rx] += size[ry];
            size[ry] = 0;
        }
        int biggerSize = Math.max(size[rx], size[ry]);
        IO.println("size after join = " + biggerSize);
        return true;
    }

    public int getSize(int x) {
        return size[find(x)];
    }

    public List<Integer> getAllSize() {
        return Arrays.stream(this.size).boxed().toList();
    }

    public List<Integer> getGroupSize() {
        printParent();
        printRank();
        printSize();

        // Set<Integer> seen = new HashSet<>();
        // List<Integer> res = new ArrayList<>();
        // for (int i = 0; i < this.rank.length; i++) {
        // var parent = this.find(i);
        // if (seen.contains(parent))
        // continue;
        // res.add(this.rank[parent]);
        // seen.add(parent);
        // }
        // IO.println("------");
        // IO.println(seen);
        // IO.println(res);

        // IO.println("------");
        var res = Arrays.stream(this.size).boxed().toList();
        return res;
    }

    public void printRank() {
        Arrays.stream(this.rank).forEach(i -> {
            IO.print(i + ", ");
        });
        IO.println();
    }

    public void printSize() {
        Arrays.stream(this.size).forEach(i -> {
            IO.print(i + ", ");
        });
        IO.println();
    }

    public void printParent() {
        Arrays.stream(this.parent).forEach(i -> {
            IO.print(i + ", ");
        });
        IO.println();
    }
}
