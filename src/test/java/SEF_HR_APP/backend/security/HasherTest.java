package SEF_HR_APP.backend.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class HasherTest {

    @Parameter(0)
    public String testPass;
    @Parameter(1)
    public String testPassAltered;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {{"test","tezt"},{"asd","azd"},{"vnjfeikj","vnjfeifj"},{"nviewipo","nviewipa"},{"vciewcuwe","vcjewcuwe"},{"oqwelnlc","oqwelmlc"},{"pqowpzx","lqowpzx"},{"bgtvfe","bgtvhe"},{"zxcasd","zgcasd"},{"qwe","qwr"},{"test1","tast1"},{"test2","test1"}};
        return Arrays.asList(data);
    }



    @Test
    public void sameInputShouldGiveSameOutput(){

        assertEquals(Hasher.getSHA256(testPass),Hasher.getSHA256(testPass));
    
    }

    @Test
    public void smallChangesHaveBigImpact(){

        assertNotEquals(Hasher.getSHA256(testPass),Hasher.getSHA256(testPassAltered));
    
    }

    
}