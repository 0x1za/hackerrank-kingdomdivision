import java.util.*;
import java.io.*;


public class SolutionV2 {
    public static int mod = 1000000007;

    public static void findPaths(boolean[][] adjacency, int node, HashSet<Integer> Betty, HashSet<Integer> Reggy, int[] paths) {
        if (Betty.size() + Reggy.size() == adjacency.length) {
            boolean bettyPeaceful = connected(adjacency, Betty);
            boolean reggyPeaceful = connected(adjacency, Reggy);
            if (bettyPeaceful && reggyPeaceful) {
                paths[0] = (paths[0] + 1) % mod;
            }
            return;
        }
        if (node >= adjacency.length) {
            return;
        }

        HashSet<Integer> bCloned = (HashSet<Integer>) Betty.clone();
        bCloned.add(node);
        findPaths(adjacency, node + 1, bCloned, Reggy, paths);
        HashSet<Integer> rCloned = (HashSet<Integer>) Reggy.clone();
        rCloned.add(node);
        findPaths(adjacency, node + 1, Betty, rCloned, paths);

    }


    public static boolean connected(boolean[][] adjacency, Set<Integer> nodes) {
        for (Integer node : nodes) {
            boolean conExists = false;
            for (Integer other : nodes) {
                if (node.equals(other)) {
                    continue;
                }
                if (adjacency[node][other] || adjacency[other][node]) {
                    conExists = true;
                    break;
                }
            }
            if (!conExists) {
                return false;
            }
        }
        return true;
    }

    // Complete the kingdomDivision function below.
    public static int kingdomDivision(int n, int[][] roads) {
        if  (n >= 2 && n <= Math.pow(10, 5)) {
            boolean[][] adjacency = new boolean[n][n];
            for (int road = 0; road < roads.length; road++) {
                int u = roads[road][0] - 1;
                int v = roads[road][1] - 1;
                adjacency[u][v] = true;
                adjacency[v][u] = true;
            }
            int[] ways = new int[] { 0 };
            findPaths(adjacency, 0, new HashSet<>(), new HashSet<>(), ways);
            return ways[0];
        } else {
            return 1;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        in.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        // Create array of input
        int[][] roads = new int[t-1][2];

        for (int i = 0; i < t-1; ++i) {
            String[] roadItems = in.nextLine().split(" ");
            in.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            System.out.println("Case #" + i + ": " + Arrays.toString(roadItems));

            for (int j = 0; j < 2; j++) {
                int roadsItem = Integer.parseInt(roadItems[j]);
                roads[i][j] = roadsItem;
            }
        }

        int result = kingdomDivision(t, roads);
        System.out.println(result);
    }
}