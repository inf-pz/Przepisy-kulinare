package tests;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.przepisy.web.dao.User;
import com.przepisy.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/przepisy/web/config/dao-context.xml",
		"classpath:com/przepisy/web/config/security-context.xml", "classpath:test/config/datasource.xml" })
public class UsersDaoTest {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private DataSource dataSource;
	
	public void init(){

		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void testCreateUser(){
		User user = new User("user22", "pass", "test@wp.pl", true, "user");
		usersDao.create(user);
	}


}
