package com.pfaraco;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RestController;

import com.pfaraco.domain.TestTopicDao;
import com.pfaraco.domain.TestUserDao;
import com.pfaraco.quiz.server.config.PersistenceJPAConfig;
import com.pfaraco.quiz.server.domain.topic.TopicDaoImpl;
import com.pfaraco.quiz.server.domain.user.UserDaoImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */

public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */

    /**
     * @return the suite of tests being tested
     */

    public static Test suite()
    {
    	final TestSuite suite = new TestSuite();
      /*suite.addTest( new TestSuite(AppTest.class));      
    	suite.addTest( new TestSuite(TestUserDao.class)); 
    	suite.addTest( new TestSuite(TestTopicDao.class)); */
        return suite;
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
