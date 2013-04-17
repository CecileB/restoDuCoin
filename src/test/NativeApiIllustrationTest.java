package test;

import junit.framework.TestCase;

import model.Restaurant;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.*;

public class NativeApiIllustrationTest extends TestCase{

	private SessionFactory sessionFactory;

	@SuppressWarnings("deprecation")
	@BeforeClass
	protected void setUp() throws Exception {
		// A SessionFactory is set up once for an application
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
	}

	@AfterClass
	protected void tearDown() throws Exception {
		if ( sessionFactory != null ) {
			sessionFactory.close();
		}
	}

	@Test
	public void testRestoPersistence() {

		Restaurant monResto = new Restaurant("La Grande Muraille","chinois", "20 avenue des champs","75012", "Paris");
		
		System.out.println(sessionFactory);
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		System.out.println(monResto.getNom());
		
		session.save( monResto );
		
		session.getTransaction().commit();
		
		session.close();
	}
	
	
	
}
