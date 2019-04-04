[TOC]

##### 配置 `pom` 文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.yy</groupId>
    <artifactId>mybatis01</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.0</version>
        </dependency>

        <dependency>
            <groupId>postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>9.1-901-1.jdbc4</version>
        </dependency>

        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.3.2</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

##### 配置文件

###### `db.properties`

> 这里指定数据库连接的信息

```xml
driver=org.postgresql.Driver
url=jdbc:postgresql://127.0.0.1:5432/p02
user=u01
password=abc
```

###### `log4j.properties`

> 配置 `log4j` 日志输出格式

```xml
# Global logging configuration
log4j.rootLogger=DEBUG, stdout

# MyBatis logging configuration...
#log4j.logger.mybatis.simple.mapper=TRACE

# Console output...
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n
```

###### `mybatis-config.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<!-- XML 配置文件包含对 MyBatis 系统的核心设置 -->
<configuration>

    <!-- 指定 MyBatis 数据库配置文件 -->
    <properties resource="db.properties" />

    <!-- 指定 MyBatis 所用日志的具体实现 -->
    <settings>
        <setting name="logImpl" value="LOG4J" />
    </settings>


    <environments default="development">

        <!-- 环境配置，即连接的数据库。 -->
        <environment id="development">

            <!-- 指定事务管理类型，type="JDBC"指直接简单使用了JDBC的提交和回滚设置 -->
            <transactionManager type="JDBC" />

            <!-- dataSource指数据源配置，POOLED是JDBC连接对象的数据源连接池的实现。 -->
            <dataSource type="POOLED">
                <property name="driver" value="${driver}" />
                <property name="url" value="${url}" />
                <property name="username" value="${user}" />
                <property name="password" value="${password}" />
            </dataSource>
        </environment>
    </environments>
</configuration>
```

##### 测试数据库连接

```java
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

public class TestConnect {
    public static void main(String[] args) throws IOException, SQLException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession sqlSession = sessionFactory.openSession();
        System.out.println(sqlSession.getConnection().isClosed());
    }
}

```

```bash
false
```

##### 查询数据

###### 修改 `mybatis-config.xml`

> 增加以下内容，让`mybatis`知道去哪个文件里查找要执行的`SQL`，通过`ID`来查找

```xml
    <mappers>
        <mapper resource="com/yy/mapper/CountryMapper.xml"/>
    </mappers>
```

###### 增加相关的 `XXMapper.xml`

> 在`resources`目录下创建`com/yy/mapper/CountryMapper.xml`文件，这个文件对应上面的 `mappers`选项。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.yy.mapper.CountryMapper">
    <select id="selectAll" resultType="com.yy.model.Country">
        select * from country
    </select>
</mapper>
```

###### 创建相关的`model`类

```java
package com.yy.model;

public class Country {
    private Long id;
    private String countryName;
    private String countryCode;

    public Country() {
        super();
    }

    public Country(Long id, String countryName, String countryCode) {
        this.id = id;
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}

```

###### 查询程序

```java
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

```

```bash
Country{id=1, countryName='a', countryCode='b'}
Country{id=1, countryName='aa', countryCode='bb'}
Country{id=1, countryName='aaa', countryCode='bbb'}
```

##### 使用`mapper`接口类的方法

###### 新增接口类

```java
package com.yy.mapper;

import com.yy.model.Country;

import java.util.List;

public interface CountryMapper {
    public Country getCountryByID(Long id);
    public List<Country> getCountrys();
}
```

###### 修改 `CountryMapper.xml`

> 这里的`namespace`就是上面新增的接口类的全称

```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.yy.mapper.CountryMapper">
    <select id="getCountrys" resultType="com.yy.model.Country">
        select * from country
    </select>

    <select id="getCountryByID" resultType="com.yy.model.Country">
        select * from country where id = #{id}
    </select>

    <select id="deleteByID">
        delete from country where id = #{id}
    </select>
</mapper>
```

###### 修改测试代码

```java
import com.yy.mapper.CountryMapper;
import com.yy.model.Country;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ConcurrentModificationException;
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

        Country country = countryMapper.getCountryByID(1L);
        System.out.println(country.toString());
    }
}

```

```bash
DEBUG [main] - ==>  Preparing: select * from country 
DEBUG [main] - ==> Parameters: 
DEBUG [main] - <==      Total: 3
Country{id=1, countryName='a', countryCode='b'}
Country{id=2, countryName='aa', countryCode='bb'}
Country{id=3, countryName='aaa', countryCode='bbb'}
DEBUG [main] - ==>  Preparing: select * from country where id = ? 
DEBUG [main] - ==> Parameters: 1(Long)
DEBUG [main] - <==      Total: 1
Country{id=1, countryName='a', countryCode='b'}

```

##### 使用 `Param`

> 对于参数使用 `Param` 可以增加可读性

```java
package com.yy.mapper;

import com.yy.model.Country;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CountryMapper {
    Country getCountryByID(@Param("id") Long id);
    List<Country> getCountrys();

    List<Country> getCountrysByCountryName(String CountryName);

    int deleteByID(Long id);
}

```