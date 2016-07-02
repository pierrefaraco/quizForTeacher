

import static org.junit.Assert.*;

import java.util.Map;

import javax.transaction.Transactional;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cnam.quiz.common.config.PersistenceJPAConfig;
import com.cnam.quiz.common.enums.AccountType;
import com.cnam.quiz.server.domain.cours.Cours;
import com.cnam.quiz.server.domain.cours.CoursDao;
import com.cnam.quiz.server.domain.cours.CoursDaoImpl;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.domain.user.UserDao;
import com.cnam.quiz.server.domain.user.UserDaoImpl;

import cnam.glg204.domain.Dao.EntitiesCreator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceJPAConfig.class,
		UserDaoImpl.class, CoursDaoImpl.class })
@Transactional(rollbackOn = Exception.class)
@Rollback(false)
public class dataInjector extends TestCase  {
	@Autowired
	UserDao userDao;

	@Autowired
	CoursDao coursDao;

	@Test
	public void testAutoId() {
		User user =new User();
		user.setAccountType(AccountType.PROFESSOR);	
		user.setEmail("pierre.faraco@gmail.com");
		user.setPassword("55261981");
		user.setFirstName("pierre");
		user.setLastName("Faraco");
		userDao.save(user);
                
        User user2 =new User();
		user2.setAccountType(AccountType.PROFESSOR);	
		user2.setEmail("pierrot5526@hotmail.fr");
		user2.setPassword("55261981");
		user2.setFirstName("pierre");
		user2.setLastName("Faraco");
		userDao.save(user2);
	
                for (int i=0 ;i<14;i++){
                    Cours cours1 = EntitiesCreator.createRandomCours(true, user, null);
                    if( (int)(Math.random()*4)==1)
                        cours1.setActive(false);  
                    coursDao.save(cours1);
                    Cours cours2 = EntitiesCreator.createRandomCours(true, user2, null);
                    if( i%2==1)
                        cours1.setActive(false);
                    coursDao.save(cours2);
                }		
	
	}

	
}
