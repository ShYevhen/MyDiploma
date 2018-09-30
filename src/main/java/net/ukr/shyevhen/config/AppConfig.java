package net.ukr.shyevhen.config;



import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import net.ukr.shyevhen.service.UserDetailsServiceImpl;


@Configuration

//@ComponentScan({
//	"net.ukr.shyevhen.config",
//	"net.ukr.shyevhen.controllers",
//	"net.ukr.shyevhen.model",
//	"net.ukr.shyevhen.repository",
//	"net.ukr.shyevhen.service"
//	
//})
@ComponentScan("net.ukr.shyevhen")
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
@EnableWebMvc
public class AppConfig {
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		return adapter;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		Properties proper = new Properties();
		proper.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setJpaProperties(proper);
		emf.setPackagesToScan("net.ukr.shyevhen");
//		emf.setPackagesToScan("net.ukr.shyevhen.config", "net.ukr.shyevhen.controllers", "net.ukr.shyevhen.model", "net.ukr.shyevhen.repository", "net.ukr.shyevhen.service");
		return emf;
	}
	
//	@Bean
//	public InternalResourceViewResolver inetnalResourseViewResolver() {
//		InternalResourceViewResolver irvr = new InternalResourceViewResolver("WEB-INF/pages", ".jsp");
//		irvr.setOrder(1);
//		return irvr;
//	}
	
	 @Bean
	    public UrlBasedViewResolver setupViewResolver() {
	        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
	        resolver.setPrefix("/WEB-INF/pages");
	        resolver.setSuffix(".jsp");
	        resolver.setViewClass(JstlView.class);

	        return resolver;
	    }
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
