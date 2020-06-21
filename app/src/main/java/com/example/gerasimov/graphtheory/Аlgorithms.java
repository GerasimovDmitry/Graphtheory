package com.example.gerasimov.graphtheory;

public class Аlgorithms {
    public static int[][] matrix;
    static int INF = Integer.MAX_VALUE / 2;
    public static int n = Main.countNodes;
    public static String[] components = new String[n];

    public static boolean[] dfs(int start) {
        boolean[] used = new boolean[n];
        used[start] = true;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] != 0 && !used[j]) dfs(i);
        }
        }
        return used;
    }
    public static void setMatrix(){
        matrix = Main.getMatrix();
    }
    public static int components(){
        int k = 0;
        boolean[] used = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!used[i]){
                boolean[] used2 = dfs(i);
                for(int j = 0; j < n; j++){
                    if (used2[j]){
                        used[j] = true;
                        components[k] = new StringBuilder().append(components[k]).append(j+1).toString();
                    }
                }
                k += 1;
            }
        }
        return k;
    }

           /* Алгоритм Дейкстры за O(V^2) */
           public static int[] dijkstra(int start) {
               boolean[] used = new boolean [n]; // массив пометок
                int[] dist = new int [n]; // массив расстояния. dist[v] = минимальное_расстояние(start, v)

               for(int i = 0; i < n; i ++) dist[i] = INF; // устанаавливаем расстояние до всех вершин INF
               dist[start] = 0; // для начальной вершины положим 0

               for (;;) {
                      int v = -1;
                    for (int nv = 0; nv < n; nv++) // перебираем вершины
                              if (!used[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv])) // выбираем самую близкую непомеченную вершину
                                  v = nv;
                       if (v == -1) break; // ближайшая вершина не найдена
                      used[v] = true; // помечаем ее
                       for (int nv = 0; nv < n; nv++)
                     if (!used[nv] && matrix[v][nv] < INF) // для всех непомеченных смежных
                     dist[nv] = Math.min(dist[nv], dist[v] + matrix[v][nv]); // улучшаем оценку расстояния (релаксация)
                    }
               return dist;
           }

           public static int[][] floyd(){
               for(int i = 0; i < n; i++) {
                   matrix[i][i] = INF;
                   for (int j = 0; j < n; j++) {
                       if (matrix[i][j] == 0)
                           matrix[
                                   i][j] = INF;
                   }
               }
               for(int i = 0; i < n; i++){
                   for (int j = 0; j < n; j++) {
                       if (matrix[j][i] < INF){
                           for (int k = 0; k < n; k++) {
                               if (matrix[j][i] + matrix[i][k] < matrix[j][k])
                               matrix[j][k] = matrix[j][i] + matrix[i][k];
                           }
                       }
                   }
                }
               return matrix;
           }
}
