package kr.hhplus.be.server.config.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration

/*
 @CreatedDate, @LastModifiedDate, @CreatedBy, @LastModifiedBy 등의 애너테이션 사용
*/
@EnableJpaAuditing

/*
 특정한 DataSource를 연결하고 싶을 경우

@EnableJpaRepositories(
    basePackages = "kr.hhplus.be.server.query.repository",
    entityManagerFactoryRef = "queryEntityManagerFactory",
    transactionManagerRef = "queryTransactionManager"
)

    @Bean(name = "queryDataSource")
    @ConfigurationProperties("spring.datasource.query")
    public DataSource queryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "queryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean queryEntityManagerFactory(
            @Qualifier("queryDataSource") DataSource dataSource
            ) {

        return new LocalContainerEntityManagerFactoryBean() {{
            setDataSource(dataSource);
            setPackagesToScan("kr.hhplus.be.server.query.entity");
            setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        }};
    }

    @Bean(name = "queryTransactionManager")
    public PlatformTransactionManager readTransactionManager(
            @Qualifier("queryEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

 */
@EnableJpaRepositories(basePackages = "kr.hhplus.be.server")
public class JpaConfig {

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}