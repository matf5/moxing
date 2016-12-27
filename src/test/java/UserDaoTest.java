import com.moxing.ssm.dao.UserDao;
import com.moxing.ssm.model.User;


import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by mtf on 2016/11/30.
 */
//@RunWith(SpringJUnit4ClassRunner.class) //spring的单元测试
@ContextConfiguration({"classpath:spring/spring-*.xml"})    //上下文配置
public class UserDaoTest {

    @Autowired
    private UserDao userDao;    //初始化Dao层，面向接口编程

    /**
     * 添加用户的单元测试，添加成功与否会有对应的提示。
     * 当然按照我这个配置一般会正确，如果说出错就需要你一步一步的看错误的提示代码了。
     * 添加同样的LoginId的用户会添加失败，因为在上面把LoginId作为了数据库表的主键。
     */
    @Test
    public void testAdd() {
        User user = new User();
        user.setPhonenum("13750065621");
        user.setPassword("1234566");
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加用户失败");
        }
        if (result>0)
            System.out.println("添加用户成功");
    }


}