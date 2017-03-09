import org.junit.Assert;
import org.junit.Test;
import ua.mykytenko.dao.StudentsDAO;
import ua.mykytenko.dao.StudentsDAOImpl;

public class StudentsDAOTest {
    StudentsDAO students = new StudentsDAOImpl();

    @Test
    public void getByIdTest(){
        Assert.assertEquals(TestData.STUDENTS[0], students.getById(1));
    }
}
