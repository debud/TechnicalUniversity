package polinoame;

import static org.junit.Assert.*;
import org.junit.*;

public class JUnitTest2 {

	private static Model m;
	private static int nrTesteCuSucces = 0;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		/**O singura data inaintea executiei setului de teste din clasa!**/
		m = new Model();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println(" numarul testelor cu succes = " + nrTesteCuSucces + "\n numarul testelor cu esuate = " + (9 - nrTesteCuSucces));
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	/**S-a terminat testul curent!**/
	}
	
	@Test
	public void test1Substraction() {
		Polynomial p = new Polynomial();
		Polynomial p1 = new Polynomial();
		m.reset();
		m.poly.add(p);
		m.poly.add(p1);
		m.poly.get(0).addMonomial(12,4);
		m.poly.get(0).addMonomial(-6, 3);
		m.poly.get(0).addMonomial(2, 1);
		m.poly.get(0).addMonomial(7, 0);
		m.poly.get(1).addMonomial(3, 2);
		m.poly.get(1).addMonomial(2, 0); 
		p = m.poly.get(0).substraction(m.poly.get(1));
		assertEquals("+12*X^4-6*X^3-3*X^2+2*X^1+5*X^0",p.print());
		nrTesteCuSucces++;		
	}
	
	@Test
	public void test2Multiplication() {
		Polynomial p = new Polynomial();
		Polynomial p1 = new Polynomial();
		m.reset();
		m.poly.add(p);
		m.poly.add(p1);
		m.poly.get(0).addMonomial(12,4);
		m.poly.get(0).addMonomial(-6, 3);
		m.poly.get(0).addMonomial(2, 1);
		m.poly.get(0).addMonomial(7, 0);
		m.poly.get(1).addMonomial(3, 2);
		m.poly.get(1).addMonomial(2, 0); 
		p = m.poly.get(0).multiply(m.poly.get(1));
		assertEquals("+36*X^6-18*X^5+24*X^4-6*X^3+21*X^2+4*X^1+14*X^0",p.print());
		nrTesteCuSucces++;		
	}
	
	
	@Test
	public void test3Divsion() {
		Polynomial p = new Polynomial();
		Polynomial p1 = new Polynomial();
		String s = new String("");
		m.reset();
		m.poly.add(p);
		m.poly.add(p1);
		m.poly.get(0).addMonomial(1,2);
		m.poly.get(0).addMonomial(2, 1);
		m.poly.get(0).addMonomial(2, 0);
		m.poly.get(1).addMonomial(2, 1);
		m.poly.get(1).addMonomial(3, 0); 
		s = m.poly.get(0).division(m.poly.get(1));
		assertEquals("catul = +0.50*X^1+0.25*X^0 \n restul = +1.25*X^0",s);
		nrTesteCuSucces++;	
		
		/* Polynomial p = new Polynomial();
		Polynomial p1 = new Polynomial();
		String s = new String("");
		m.reset();
		m.poly.add(p);
		m.poly.add(p1);
		m.poly.get(0).addMonomial(12,4);
		m.poly.get(0).addMonomial(-6, 3);
		m.poly.get(0).addMonomial(2, 1);
		m.poly.get(0).addMonomial(7, 0);
		m.poly.get(1).addMonomial(3, 2);
		m.poly.get(1).addMonomial(2, 0); 
		s = m.poly.get(0).division(m.poly.get(1));
		assertEquals("catul = +4*X^2-2*X^1-2*X^0 \n restul = +6*X^1+11*X^0",s); //-8*X^2+6*X^1+7*X^0
		nrTesteCuSucces++;		*/
	}
	
	@Test
	public void test4Addition() {
		Polynomial p = new Polynomial();
		Polynomial p1 = new Polynomial();
		m.reset();
		m.poly.add(p);
		m.poly.add(p1);
		m.poly.get(0).addMonomial(12,4);
		m.poly.get(0).addMonomial(-6, 3);
		m.poly.get(0).addMonomial(2, 1);
		m.poly.get(0).addMonomial(7, 0);
		m.poly.get(1).addMonomial(100001, 1000);
		m.poly.get(1).addMonomial(-12, 4);
		m.poly.get(1).addMonomial(3, 2);
		m.poly.get(1).addMonomial(2, 0);
		p = m.poly.get(0).addition(m.poly.get(1));
		assertEquals("+100001*X^1000-6*X^3+3*X^2+2*X^1+9*X^0", p.print());
		nrTesteCuSucces++;		
	}
	
			 
	@Test
	public void test5Substraction() {
		Polynomial p = new Polynomial();
		Polynomial p1 = new Polynomial();
		m.reset();
		m.poly.add(p);
		m.poly.add(p1);
		m.poly.get(0).addMonomial(12,4);
		m.poly.get(0).addMonomial(-6, 3);
		m.poly.get(0).addMonomial(2, 1);
		m.poly.get(0).addMonomial(7, 0);
		m.poly.get(1).addMonomial(100001, 1000);
		m.poly.get(1).addMonomial(-12, 4);
		m.poly.get(1).addMonomial(3, 2);
		m.poly.get(1).addMonomial(2, 0);
		p = m.poly.get(0).substraction(m.poly.get(1));
		assertEquals("-100001*X^1000+24*X^4-6*X^3-3*X^2+2*X^1+5*X^0", p.print());
		nrTesteCuSucces++;		
	}
	
	@Test
	public void test6Multiplication() {
		Polynomial p = new Polynomial();
		Polynomial p1 = new Polynomial();
		m.reset();
		m.poly.add(p);
		m.poly.add(p1);
		m.poly.get(0).addMonomial(12,4);
		m.poly.get(0).addMonomial(-6, 3);
		m.poly.get(0).addMonomial(2, 1);
		m.poly.get(0).addMonomial(7, 0);
		m.poly.get(1).addMonomial(100001, 1000);
		m.poly.get(1).addMonomial(-12, 4);
		m.poly.get(1).addMonomial(3, 2);
		m.poly.get(1).addMonomial(2, 0);
		p = m.poly.get(0).multiply(m.poly.get(1));
		assertEquals("+1200012*X^1004-600006*X^1003+200002*X^1001+700007*X^1000-144*X^8+72*X^7+36*X^6-42*X^5-60*X^4-6*X^3+21*X^2+4*X^1+14*X^0", p.print());
		nrTesteCuSucces++;		
	}
	
	@Test
	public void test7Division() {
		Polynomial p = new Polynomial();
		Polynomial p1 = new Polynomial();
		String s = new String("");
		m.reset();
		m.poly.add(p);
		m.poly.add(p1);
		m.poly.get(0).addMonomial(12,4);
		m.poly.get(0).addMonomial(-6, 3);
		m.poly.get(0).addMonomial(2, 1);
		m.poly.get(0).addMonomial(7, 0);
		m.poly.get(1).addMonomial(100001, 1000);
		m.poly.get(1).addMonomial(-12, 4);
		m.poly.get(1).addMonomial(3, 2);
		m.poly.get(1).addMonomial(2, 0);
		s = m.poly.get(0).division(m.poly.get(1));
		assertEquals("catul = 0 \n restul = +12*X^4-6*X^3+2*X^1+7*X^0", s);
		nrTesteCuSucces++;		
	}

	@Test
	public void test8Integration() {
		Polynomial p = new Polynomial();
		Polynomial p1 = new Polynomial();
		String s = new String("");
		m.reset();
		m.poly.add(p);
		m.poly.add(p1);
		m.poly.get(0).addMonomial(-999999999,78);
		m.poly.get(0).addMonomial(12, 5);
		m.poly.get(0).addMonomial(77, 3);
		m.poly.get(0).addMonomial(1932, 1);
		m.poly.get(0).addMonomial(-4335, 0);
		s = m.poly.get(0).integrate().print();
		assertEquals("-12658227.84*X^79+2*X^6+19.25*X^4+966*X^2-4335*X^1", s);
		nrTesteCuSucces++;		
	}
	
	@Test
	public void test9Derivation() {
		Polynomial p = new Polynomial();
		Polynomial p1 = new Polynomial();
		String s = new String("");
		m.reset();
		m.poly.add(p);
		m.poly.add(p1);
		m.poly.get(0).addMonomial(-999999999,78);
		m.poly.get(0).addMonomial(12, 5);
		m.poly.get(0).addMonomial(77, 3);
		m.poly.get(0).addMonomial(1932, 1);
		m.poly.get(0).addMonomial(-4335, 0);
		s = m.poly.get(0).derivate().print();
		assertEquals("-77999999922*X^77+60*X^4+231*X^2+1932*X^0", s);
		nrTesteCuSucces++;		
	}

}
