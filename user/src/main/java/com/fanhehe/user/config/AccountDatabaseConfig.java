package com.fanhehe.user.config;

//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
////https://github.com/Snailclimb/springboot-integration-examples/blob/master/md/springboot-mybatis-mutipledatasource.md
//
//@Configuration
//@MapperScan(basePackages = "com.fanhehe.user.dao", sqlSessionTemplateRef = "Account.SqlSessionTemplate")
//public class AccountDatabaseConfig  {
//
//    @Primary
//    @Bean(name = "account")
//    @ConfigurationProperties(prefix = "spring.datasource.my")
//    public DataSource getDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "Account.SqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("account") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//
//        bean.setDataSource(dataSource);
//
//        return bean.getObject();
//    }
//
//    @Bean(name = "Account.DataSourceManager")
//    public DataSourceTransactionManager transactionManager(@Qualifier("account") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "Account.SqlSessionTemplate")
//    @Primary
//    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("Account.SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}

public class AccountDatabaseConfig {}