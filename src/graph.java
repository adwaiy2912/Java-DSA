import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class graph {
    static class edge {
        int src;
        int dest; 
        int wt;

        public edge(int s, int d) {
            src = s;
            dest = d;
            wt = 1;
        }
        public edge(int s, int d, int w) {
            src = s;
            dest = d;
            wt = w;
        }
    }

    static class pair implements Comparable<pair>{
        int node;
        int val;

        public pair(int n, int d) {
            node = n;
            val = d;
        }
        
        @Override
        public int compareTo(pair p2) {
            return this.val - p2.val;
        }
    }

    public static void createGraph(ArrayList<edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<edge>();
        }

        graph[0].add(new edge(0, 1));
        graph[0].add(new edge(0, 2));

        graph[1].add(new edge(1, 0));
        graph[1].add(new edge(1, 3));
        
        graph[2].add(new edge(2, 0));
        graph[2].add(new edge(2, 4));
        
        graph[3].add(new edge(3, 1));
        graph[3].add(new edge(3, 4));
        graph[3].add(new edge(3, 5));
        
        graph[4].add(new edge(4, 2));
        graph[4].add(new edge(4, 3));
        graph[4].add(new edge(4, 5));
        
        graph[5].add(new edge(5, 3));
        graph[5].add(new edge(5, 4));
        graph[5].add(new edge(5, 6));
        
        graph[6].add(new edge(6, 5));
        
    }

    private static void Bfs(ArrayList<edge> graph[], boolean vis[], int start) {
        Queue<Integer> q =  new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int curr = q.poll();
            if (!vis[curr]) {
                System.out.print(curr + " ");
                vis[curr] = true;

                for (int i = 0; i < graph[curr].size(); i++) {
                    q.add(graph[curr].get(i).dest);
                }
            }
        }
    }
    public static void bfs(ArrayList<edge> graph[], int V) {
        boolean vis[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                Bfs(graph, vis, i);
            }
        }
    }

    // DFS -> O(V+E)
    private static void Dfs(ArrayList<edge> graph[], boolean vis[], int curr) {
        System.out.print(curr + " ");
        vis[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                Dfs(graph, vis, e.dest);
            }
        }
    }
    public static void dfs(ArrayList<edge> graph[], int V) {
        boolean vis[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                Dfs(graph, vis, i);
            }
        }
    }

    // Time - O(V^V)
    private static void printAllPath(ArrayList<edge> graph[], boolean vis[], int curr, String path, int target) {
        if (curr == target) {
            System.out.println(path);
            return;
        }
        for (int i = 0; i < graph[curr].size(); i++) {
            edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                vis[curr] = true;
                printAllPath(graph, vis, e.dest, path+e.dest, target);
                vis[curr] = false;
            }
        }
    }
    public static void printAllPaths(ArrayList<edge> graph[], int V) {
        boolean vis[] = new boolean[V];
        printAllPath(graph, vis, 0, "0", 5);
    }

    // Modified DFS
    private static boolean isCycleDir(ArrayList<edge> graph[], boolean vis[], int curr, boolean rec[]) {
        vis[curr] = true;
        rec[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            edge e = graph[curr].get(i);
            if (rec[e.dest]) {
                return true;
            } else if (!vis[e.dest] && isCycleDir(graph, vis, e.dest, rec)) {
                return true;
            }
        }

        rec[curr] = false;
        return false;
    }
    public static void isCycleDirected(ArrayList<edge> graph[], int V) {
        boolean vis[] = new boolean[V];
        boolean rec[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                System.out.println(isCycleDir(graph, vis, 0, rec));
                break;
            }
        }
    }

    private static void topSort(ArrayList<edge> graph[], boolean vis[], int curr, Stack<Integer> stk) {
        vis[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                topSort(graph, vis, e.dest, stk);
            }
        }
        stk.push(curr);
    }
    public static void topologicalSort(ArrayList<edge> graph[], int V) {
        boolean vis[] = new boolean[V];
        Stack<Integer> stk = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                topSort(graph, vis, i, stk);
            }
        }
        while (!stk.isEmpty()) {
            System.out.print(stk.pop()+" ");
        }
    }

    // Modified DFS -> O(V+E)
    private static boolean isCycleUnDir(ArrayList<edge> graph[], boolean vis[], int curr, int par) {
        vis[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            edge e = graph[curr].get(i);
            if (vis[e.dest] && par != e.dest) {
                return true;
            }
            else if (!vis[e.dest] && isCycleUnDir(graph, vis, e.dest, curr)) {
                return true;
            }
        }
        return false;
    }
    public static void isCycleUnDirected(ArrayList<edge> graph[], int V) {
        boolean vis[] = new boolean[V];
        System.out.println(isCycleUnDir(graph, vis, 0, -1));
    }

    public static void dijkstra(ArrayList<edge> graph[], int V, int src) {
        PriorityQueue<pair> pq = new PriorityQueue<>();
        boolean vis[] = new boolean[V];
        int dist[] = new int[V];
        for (int i = 0; i < V; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        pq.add(new pair(src, 0));
        while (!pq.isEmpty()) {
            pair curr = pq.poll();
            if (!vis[curr.node]) {
                vis[curr.node] = true;
                for (int i = 0; i < graph[curr.node].size(); i++) {
                    edge e = graph[curr.node].get(i);
                    int u = e.src;
                    int v = e.dest;
                    if (dist[u] + e.wt < dist[v]) {
                        dist[v] = dist[u] + e.wt;
                        pq.add(new pair(v, dist[v]));
                    }
                }
            }
        }
        for (int i = 0; i < V; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    public static void bellmanFord(ArrayList<edge> graph[], int V, int src) {
        int dist[] = new int[V];
        for (int i = 0; i < V; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < graph[i].size(); j++) {
                    edge e = graph[i].get(j);
                    int u = e.src;
                    int v = e.dest;

                    if (dist[u] != Integer.MAX_VALUE && dist[u]+e.wt < dist[v]) {
                        dist[v] = dist[u] + e.wt;
                    }
                }
            }
        }
        for (int i = 0; i < V; i++) {
            System.out.print(dist[i] + " ");
        }

        // detect -ve weight cycle
        boolean flag = false;
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                edge e = graph[i].get(j);
                int u = e.src;
                int v = e.dest;

                if (dist[u] != Integer.MAX_VALUE && dist[u]+e.wt < dist[v]) {
                    System.out.println("-ve weight cycle detected");
                    flag = true;   
                }
            }
        }
        if (flag) {
            for (int i = 0; i < V; i++) {
                System.out.print(dist[i] + " ");
            }
        }
    }

    // O(ElogE)
    public static void primsAlgo(ArrayList<edge> graph[], int V) {
        PriorityQueue<pair> pq = new PriorityQueue<>();
        boolean vis[] = new boolean[V];
        
        pq.add(new pair(0, 0));
        int mstCost = 0;

        while (!pq.isEmpty()) {
            pair curr = pq.poll();
            if (!vis[curr.node]) {
                vis[curr.node] = true;
                mstCost += curr.val;

                for (int i = 0; i < graph[curr.node].size(); i++) {
                    edge e = graph[curr.node].get(i);
                    if (!vis[e.dest]) {
                        pq.add(new pair(e.dest, e.wt));
                    }
                }
            }
        }

        System.out.println("Min mst cost is " + mstCost);
    }

    // O(V+E)
    public static void kosarajuAlgo(ArrayList<edge> graph[], int V) {
        //step 1 - topSort
        Stack<Integer> stk = new Stack<>();
        boolean vis[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                topSort(graph, vis, i, stk);
            }
        }

        //step 2 - transpose of graph
        @SuppressWarnings("unchecked")
        ArrayList<edge> transpose[] = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            vis[i] = false;
            transpose[i] = new ArrayList<>();
        }
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                edge e = graph[i].get(j);
                transpose[e.dest].add(new edge(e.dest, e.src));
            }
        }

        //step 3 - reverse dfs (dfs on transpose)
        while (!stk.isEmpty()) {
            int curr = stk.pop();
            if (!vis[curr]) {
                Dfs(graph, vis, curr);
                System.out.println();
            }
        }
    }

    // Using Tarjan's algo.
    // Bridge - vertice joining 2 edges, if removed breaks the graph into 2 or more graphs
    private static void bridgeDFS(ArrayList<edge> graph[], int curr, boolean vis[], 
                                    int dt[], int low[], int time, int par) {
        vis[curr] = true;
        dt[curr] = low[curr] = ++time;

        for (int i = 0; i < graph[curr].size(); i++) {
            edge e = graph[curr].get(i);
            if (e.dest == par) {
                continue;
            } else if (!vis[e.dest]) {
                bridgeDFS(graph, e.dest, vis, dt, low, time, curr);
                low[curr] = Math.min(low[curr], low[e.dest]);
                if (dt[curr] < low[e.dest]) {
                    System.out.println("Bridge is " + curr + "---" + e.dest);
                }
            } else {
                low[curr] = Math.min(low[curr], dt[e.dest]);
            }
        }
    }
    public static void getBridge(ArrayList<edge> graph[], int V) {
        int dt[] = new int[V];
        int low[] = new int[V];
        int time = 0;
        boolean vis[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                bridgeDFS(graph, i, vis, dt, low, time, -1);
            }
        }
    }

    // Using Tarjan's algo.
    // Articulation point - edge/node which if removed breakes the graph into 2 or more disjoint graphs
    private static void apDFS(ArrayList<edge> graph[], int curr, int par, int time, int dt[], int low[],
                                boolean vis[], boolean ap[]) {
        vis[curr] = true;
        dt[curr] = low[curr] = ++time;
        int childern = 0;

        for (int i = 0; i < graph[curr].size(); i++) {
            edge e = graph[curr].get(i);

            if (par == e.dest) {
                continue;
            } else if (vis[e.dest]) {
                low[curr] = Math.min(low[curr], dt[e.dest]);
            } else {
                apDFS(graph, e.dest, curr, time, dt, low, vis, ap);
                low[curr] = Math.min(low[curr], low[e.dest]);

                if (dt[curr] <= low[e.dest] && par != -1) {
                    ap[curr] = true;
                }
            }
        }

        if (par == -1 && childern > 1) {
            ap[curr] = true;
        }
    }
    public static void getAP(ArrayList<edge> graph[], int V) {
        int dt[] = new int[V];
        int low[] = new int[V];
        int time = 0;
        boolean vis[] = new boolean[V];
        boolean ap[] = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                apDFS(graph, i, -1, time, dt, low, vis, ap);
            }
        }

        for (int i = 0; i < V; i++) {
            if (ap[i]) {
                System.out.println("AP: " + i);
            }
        }
    }

    public static void printGraph(ArrayList<edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print(i + " - ");
            for (int j = 0; j < graph[i].size(); j++) {
                edge e = graph[i].get(j);
                System.out.printf("{%s,%s} ", e.src, e.dest);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int V = 7;
        @SuppressWarnings("unchecked")
        ArrayList<edge> graph[] = new ArrayList[V];

        createGraph(graph);
        /*
         *    1 --- 3
         *   /      | \
         *  0       |  5 -- 6
         *   \      | /
         *    2 --- 4
         */

        // bfs(graph, V);
        // dfs(graph, V);
        // printAllPaths(graph, V);
        // isCycleDirected(graph, V);
        // topologicalSort(graph, V);
        // isCycleUnDirected(graph, V);
        // dijkstra(graph, V, 0);
        // bellmanFord(graph, V, 0);
        // primsAlgo(graph, V);
        // kosarajuAlgo(graph, V);
        // getBridge(graph, V);
        getAP(graph, V);
    }
}
