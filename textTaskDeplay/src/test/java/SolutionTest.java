import org.junit.Test;


public class SolutionTest {

    Solution solution = new Solution();

    @Test
    public void getMinDistance() {
        //Неверное заданная раса
        String field = "STWSWTPPTPTTPWPP";
        String race = "human";
        System.out.println(solution.getResult(field, race));

    }
}