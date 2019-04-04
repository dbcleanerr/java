import com.yy.mapper.CountryMapper;
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
        // 连接
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession sqlSession = sessionFactory.openSession();

        // query
        CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
        List<Country> countryList = countryMapper.getCountrys();
        for (Country country : countryList) {
            System.out.println(country.toString());
        }

        Country country = countryMapper.getCountryByID(2L);
        System.out.println(country.toString());

        List<Country> countryList2 = countryMapper.getCountrysByCountryName("aa");
        for (Country country2 : countryList2) {
            System.out.println(country2.toString());
        }


        Integer cnt = countryMapper.deleteByID(1L);
        System.out.println(cnt);

        sqlSession.commit();

    }
}
