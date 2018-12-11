package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.ListeOp;
import application.OP;

public class ListeOpTest {
	private ListeOp lst;
	
	
	@Before
	public void before() {
		lst = new ListeOp();
	}
	
	@Test
	public void testAjouter() {
		try {
			lst.ajouter(new OP("156","Breton","Kilian"));
			lst.ajouter(new OP("157","Ara","Louis"));
			lst.ajouter(new OP("156","Casa","Jean"));
		}catch(Exception e) {
			fail("testAjouter Exception");
		}
	}

	@Test
	public void testEstVide() {
		assertEquals(true, lst.estVide());
		lst.ajouter(new OP("156","Casa","Jean"));
		assertEquals(false, lst.estVide());
	}

	@Test
	public void testGetSelected() {
		lst.ajouter(new OP("156","Breton","Kilian"));
		lst.ajouter(new OP("157","Ara","Louis"));
		lst.ajouter(new OP("156","Casa","Jean"));
		lst.select(1);
		if(lst.getSelected() == null) {
			fail("getSelected return null");
		}
	}

	@Test
	public void testDelSelected() {
		lst.ajouter(new OP("156","Breton","Kilian"));
		lst.ajouter(new OP("157","Ara","Louis"));
		lst.ajouter(new OP("156","Casa","Jean"));
		lst.select(1);
		lst.delSelected();
		lst.select(1);
		if(lst.getSelected() != null) {
			fail("elem not deleted");
		}
	}


	@Test
	public void testGet() {
		//fail("Not yet implemented");
	}

}
