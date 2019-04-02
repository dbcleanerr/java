import com.yy.model.Country;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

public class TestConnect {
    public static void main(String[] args) throws IOException, SQLException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession sqlSession = sessionFactory.openSession();


        List<Country> countryList = sqlSession.selectList("selectAll");

        for (Country country : countryList) {
            System.out.println(country.toString());
        }
    }
}
