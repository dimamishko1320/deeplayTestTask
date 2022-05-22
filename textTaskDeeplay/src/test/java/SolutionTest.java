import org.junit.Assert;
import org.junit.Test;


public class SolutionTest {
    @Test
    public void getMinDistance() {
        String field1 = "STWSWTPPTPTTPWPP";
        String race1 = "human";
        Assert.assertEquals(10, Solution.getResult(field1, race1));
        String field2 = "SSSSSSSSSSSSSSSS";
        String race2 = "woodman";
        Assert.assertEquals(18, Solution.getResult(field2, race2));
        String field3 = "TTTTTTTTTTTTTTTT";
        String race3 = "swamper";
        Assert.assertEquals(30, Solution.getResult(field3, race3));
    }

    @Test
    public void testIncorrectFieldError() {
        String field1 = "STWS";
        String race1 = "human";
        Assert.assertEquals(-1,Solution.getResult(field1, race1));
        String field2 = "OAOAOAOAOAOAOAOA";
        Assert.assertEquals(-1,Solution.getResult(field2, race1));
    }

    @Test
    public void testIncorrectRaceError() {
        String field1 = "STWSWTPPTPTTPWPP";
        String race1 = "alien";
        Assert.assertEquals(-1,Solution.getResult(field1, race1));
        String race2 = "dog";
        Assert.assertEquals(-1,Solution.getResult(field1, race2));
    }


}