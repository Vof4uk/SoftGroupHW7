import org.junit.Assert;
import org.junit.Test;
import ua.mykytenko.util.CalcUtil;

public class PhoneValidatorTest {

    @Test
    public void testPhones(){
        Assert.assertTrue(CalcUtil.phoneNumberIsValid("+380956435160"));
    }
}
