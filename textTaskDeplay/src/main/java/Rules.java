import java.io.*;
import java.util.*;

/**
 *A class for reading all information
 * about the names of creatures,
 * the names of obstacles and the cost of moving
 * from a file.
 */
public class Rules {
    private final Map<String, String> racesAndShortName;
    private final Map<String, String> obstacleAndShortName;
    private final Map<String, int[]> racesAndObstacleCosts;


    public Rules(){
        this.racesAndShortName = new HashMap<>();
        this.obstacleAndShortName = new LinkedHashMap<>();
        this.racesAndObstacleCosts = new HashMap<>();
        readRulesFromFile();
    }

    /**
     *
     * Method reads game settings from file
     * Format for recording data in a file:
        * obstruction1, obstruction1 coding
        * ....
        * ....
        * obstructionN, obstructionN coding;
        * race1, race 1 coding, ...(n - the cost of each obstacle)
        * ....
        * raceM, race m coding, ...(n - the cost of each obstacle)
     *
     */
    private void readRulesFromFile() {
        try {
            File file = new File("rules.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                String[] arr = line.split(",");
                if(arr.length==2){
                    obstacleAndShortName.put(arr[0].trim(), arr[1].trim());
                }
                if(arr.length== obstacleAndShortName.size()+2){
                    racesAndShortName.put(arr[0].trim(), arr[1].trim());
                    int[] costs = new int[obstacleAndShortName.size()];
                    for(int i=2; i< arr.length; i++){
                        costs[i-2]=Integer.parseInt(arr[i].trim());
                    }
                    racesAndObstacleCosts.put(arr[1].trim(), costs);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param race race
     * @return the cost of each cell for a certain race
     */
    public Map<String, Integer> getCellCostForRace(String race){
        Map<String, Integer> costsCells = new HashMap<>();
        int index = 0;
        int[] weight = racesAndObstacleCosts.get(race);
        for (Map.Entry<String, String> entry : obstacleAndShortName.entrySet()) {
            costsCells.put(entry.getValue(), weight[index]);
            index++;
        }
        return costsCells;
    }

    public Map<String, String> getRacesAndShortName() {
        return racesAndShortName;
    }

    public Map<String, int[]> getRacesAndObstacleCosts() {
        return racesAndObstacleCosts;
    }

}
