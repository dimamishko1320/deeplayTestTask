import java.io.*;
import java.util.*;

/**
 *
 */
public class Rules {
    private Map<String, String> racesCoding;
    private Map<String, String> cellCoding;
    private Map<String, int[]> racesAndCellCosts;


    public Rules() throws IOException {
        this.racesCoding = new HashMap<>();
        this.cellCoding = new LinkedHashMap<>();
        this.racesAndCellCosts = new HashMap<>();
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
                    cellCoding.put(arr[0].trim(), arr[1].trim());
                }
                if(arr.length==cellCoding.size()+2){
                    racesCoding.put(arr[0].trim(), arr[1].trim());
                    int[] costs = new int[cellCoding.size()];
                    for(int i=2; i< arr.length; i++){
                        costs[i-2]=Integer.parseInt(arr[i].trim());
                    }
                    racesAndCellCosts.put(arr[1].trim(), costs);
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    public Map<String, Integer> getCellCostForRace(String race){
        Map<String, Integer> costsCells = new HashMap<>();
        int index = 0;
        int[] weight = racesAndCellCosts.get(race);
        for (Map.Entry<String, String> entry :cellCoding.entrySet()) {
            costsCells.put(entry.getValue(), weight[index]);
            index++;
        }
        return costsCells;
    }

    public Map<String, String> getRacesCoding() {
        return racesCoding;
    }

    public void setRacesCoding(Map<String, String> racesCoding) {
        this.racesCoding = racesCoding;
    }

    public Map<String, String> getCellCoding() {
        return cellCoding;
    }

    public void setCellCoding(Map<String, String> cellCoding) {
        this.cellCoding = cellCoding;
    }

    public Map<String, int[]> getRacesAndCellCosts() {
        return racesAndCellCosts;
    }

    public void setRacesAndCellCosts(Map<String, int[]> racesAndCellCosts) {
        this.racesAndCellCosts = racesAndCellCosts;
    }
}
