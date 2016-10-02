package cnam.glg204.quiz.common.config;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.core.env.Environment;

/**
 *
 * Configuration Spring de la partie JPA / Hibernate
 *
 * @see
 * <a href="http://www.baeldung.com/2011/12/13/the-persistence-layer-with-spring-3-1-and-jpa/">spring
 * JPA Hibernate tutorial</a>
 *
 * @author Pierre Faraco
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:connexion.properties"})
public class PersistenceJPAConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"cnam.glg204.quiz.server.domain"});
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean(destroyMethod="")
    public DataSource dataSource() {
     
    //récupération de la datsource, configurée dans les fichiers server.xml, context.xml de tomcat.
    
        DataSource dataSource = null;
        try {
            Context initCtx = new InitialContext();
            Context  envCtx = (Context) initCtx.lookup("java:comp/env");
            dataSource = (DataSource) envCtx.lookup("jdbc/quizForTeacherDB");
           
      
        } catch (NamingException ex) {
            Logger.getLogger(PersistenceJPAConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        //Si a connection de tomcat n' a pas été récupérée on initialise une connection a partir des parametre du fichier connexion.properties
        if (dataSource == null){
            DriverManagerDataSource dataSource2 = new DriverManagerDataSource();
            dataSource2.setDriverClassName(env.getProperty("jdbc.DriverClassName"));
            dataSource2.setUrl(env.getProperty("jdbc.Url"));
            dataSource2.setUsername(env.getProperty("jdbc.Username"));
            dataSource2.setPassword(env.getProperty("jdbc.Password"));
            dataSource = dataSource2;
        }

  
        
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        //properties.setProperty("hibernate.hbm2ddl.auto", "create");//create-drop update create
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }

}
