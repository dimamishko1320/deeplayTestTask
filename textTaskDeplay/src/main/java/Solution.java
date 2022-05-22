import java.io.*;
import java.util.Map;

import static java.lang.Math.min;
import static java.util.Arrays.fill;

public class Solution {

    /**
     *
     * @param field  game field
     * @param race race
     * @return shortest path length
     */
    public static int getResult(String field, String race){
        Rules rules;
        int rez = 0;
        try {
            rules = new Rules();
        } catch (IOException e) {
            System.out.println("Error reading settings");
            return -1;
        }
        if(field.length()!=16){
            System.out.println("Field is incorrect");
            return -1;
        }
        if(!rules.getRacesCoding().containsValue(race) || !rules.getRacesAndCellCosts().containsKey(race)){
            System.out.println("Incorrect race");
            return -1;
        }

        Map<String, Integer> cellAndCost = rules.getCellCostForRace(race);

        String[] fieldByChars = field.split("");
        int[] gameFieldMatrix = new int[16];
        for(int i=0; i<16; i++){
                if(cellAndCost.containsKey(fieldByChars[i])){
                    gameFieldMatrix[i]=cellAndCost.get(fieldByChars[i]);
                }else{
                    System.out.println("Field is incorrect");
                    return -1;
                }
        }
        int[][] graph = makeGraph(gameFieldMatrix);

        dijkstra(0, graph);

        return dijkstra(0, graph)[15];
    }

    /**
     * The method translates a one-dimensional array, which is a matrix
     * into a graph in the form of a two-dimensional array, in which
     * [i][j] - distance between two adjacent vertices, 0 if they are not connected by an edge
     * @param matrix matrix
     * @return int[][] graph
     */
    private static int[][] makeGraph(int[] matrix){
        int[][] graph = new int[16][16];
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                int q = i*4+j;
                if(q+4<16){
                    graph[q][q+4]=matrix[q+4];
                }
                if(q-4>=0){
                    graph[q][q-4]=matrix[q-4];
                }
                if((q+2) % 4==0 || (q+3)%4==0){
                    graph[q][q+1] = matrix[q+1];
                    graph[q][q-1] = matrix[q-1];
                }
                if(q%4==0){
                    graph[q][q+1] = matrix[q+1];
                }
                if((q+1)%4==0){
                    graph[q][q-1]=matrix[q-1];
                }
            }
        }
        int INF = Integer.MAX_VALUE / 2;
        for(int i=0; i<16; i++){
            for(int j=0; j<16; j++){
                if(graph[i][j]==0){
                    graph[i][j]=INF;
                }
            }
        }
        return graph;
    }

    private static int[] dijkstra(int start, int[][] graph){
        int INF = Integer.MAX_VALUE / 2;
        int vNum = graph.length;
        boolean[] used = new boolean [vNum]; // массив пометок
        int[] dist = new int [vNum]; // массив расстояния. dist[v] = минимальное_расстояние(start, v)
        fill(dist, INF); // устанаавливаем расстояние до всех вершин INF
        dist[start] = 0; // для начальной вершины положим 0
        for (;;) {
            int v = -1;
            for (int nv = 0; nv < vNum; nv++) // перебираем вершины
            if (!used[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv])) // выбираем самую близкую непомеченную вершину
            v = nv;
            if (v == -1) break; // ближайшая вершина не найдена
            used[v] = true; // помечаем ее
            for (int nv = 0; nv < vNum; nv++)
            if (!used[nv] && graph[v][nv] < INF) // для всех непомеченных смежных
            dist[nv] = min(dist[nv], dist[v] + graph[v][nv]); // улучшаем оценку расстояния (релаксация)
           }
        return dist;
    }

}
