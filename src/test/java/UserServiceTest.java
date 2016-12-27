
import com.moxing.ssm.exception.OtherThingsException;
import com.moxing.ssm.model.User;
import com.moxing.ssm.service.serviceImpl.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/9/25.
 */
public class UserServiceTest extends BaseTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testAdd() {
        User user = new User();
        user.setPhonenum("13631299999");
        user.setPassword("222323");
        try {
            userService.add(user);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
