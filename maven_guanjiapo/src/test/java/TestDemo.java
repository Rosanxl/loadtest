import cn.itheima.dao.ZhangWuDao;
import cn.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class TestDemo {
    @Test
    public void demo01(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");;
        ZhangWuDao ZW = (ZhangWuDao) context.getBean("zhangWuDao");
        System.out.println(ZW);
    }

    @Test
    public void testMybatis() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("UserMapper.selectUser",1);
        System.out.println(user);
    }
}
