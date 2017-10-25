package cn.yx.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.github.pagehelper.PageInterceptor;

/**
 * @author yuxuanjiao
 * @date 2017年7月11日 下午5:16:28
 * @version 1.0
 */
@Configuration
@MapperScan(basePackages = "cn.yx.mapper", sqlSessionTemplateRef = "baseSqlSessionTemplate")
public class DatasourceConfig {

    @Bean(name = "baseDataSource")
    @ConfigurationProperties(prefix = "datasource")
    public DataSource createDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "baseSqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactory(@Qualifier("baseDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 分页插件
        Interceptor[] plugins = new Interceptor[] { pageHelper() };
        bean.setPlugins(plugins);

        return bean.getObject();
    }

    @Bean(name = "baseTransactionManager")
    public DataSourceTransactionManager createTransactionManager(@Qualifier("baseDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "baseSqlSessionTemplate")
    public SqlSessionTemplate createSqlSessionTemplate(
            @Qualifier("baseSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PageInterceptor pageHelper() {
        PageInterceptor pageHelper = new PageInterceptor();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "false");

        pageHelper.setProperties(p);
        return pageHelper;
    }
}
