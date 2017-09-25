package org.apache.commons.mail;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import junit.framework.TestCase;

public class EmailTest extends TestCase {
	
	protected SimpleEmail emailTest = new SimpleEmail();
	
	@Test
    public void testAddBccSuccess() throws Exception {
		String[] emails = {"addBccTest@gmail.com"};
		emailTest.addBcc(emails);
		assertEquals("addBccTest@gmail.com", emailTest.getBccAddresses().get(0).toString());
    }
	
	@Test
    public void testAddBccWithEmptyStringArray() throws Exception {
		String[] emails = {};
		try {
			emailTest.addBcc(emails);
		} catch(EmailException e) {
			assertEquals(new EmailException("Address List provided was invalid").getMessage(), e.getMessage());
		}
    }
	
	@Test
    public void testAddCc() throws Exception { //Test addCc(String... emails) ??? If so add test with emptyStringArray
        emailTest.addCc("addCcTest@gmail.com");
        assertEquals("addCcTest@gmail.com", emailTest.getCcAddresses().get(0).toString());
    }
	
	@Test
    public void testAddHeader() throws Exception { //Fully tested
		Map<String, String> headersTest = new HashMap<String, String>();
		headersTest.put("HeaderTest", "3");
		emailTest.addHeader("HeaderTest", "3");
		assertEquals(headersTest.entrySet(), emailTest.headers.entrySet());
    }
	
	@Test
    public void testAddHeaderEmptyName() throws Exception {
		try {
			emailTest.addHeader("", "10");
		} catch(IllegalArgumentException e) {
			assertEquals(new IllegalArgumentException("name can not be null or empty").getMessage(), e.getMessage());
		}
    }
	
	@Test
    public void testAddHeaderEmptyValue() throws Exception {
		try {
			emailTest.addHeader("headerTest", "");
		} catch(IllegalArgumentException e) {
			assertEquals(new IllegalArgumentException("value can not be null or empty").getMessage(), e.getMessage());
		}
    }
	
	@Test
    public void testAddReplyTo() throws Exception {
        
    }
	
	@Test
    public void testBuildMimeMessage() throws Exception {
        
    }
	
	@Test
    public void testGetHostName() throws Exception {
        
    }
	
	@Test
    public void testGetMailSession() throws Exception {
        
    }
	
	@Test
    public void testGetSentDate() throws Exception {
        
    }
	
	@Test
    public void testGetSocketConnectionTimeout() throws Exception {
        
    }
	
	@Test
    public void testSend() throws Exception {
        
    }
	
	@Test
    public void testSetFrom() throws Exception {
        
    }
	
	@Test
    public void testUpdateContentType() throws Exception {
        
    }
}
