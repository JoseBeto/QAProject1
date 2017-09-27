package org.apache.commons.mail;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.AddressException;
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
    public void testAddCc() throws Exception { 
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
    public void testAddReplyToSuccess() throws Exception {
		emailTest.addReplyTo("replyToTest@gmail.com", "testName");
        assertEquals("testName <replyToTest@gmail.com>", emailTest.getReplyToAddresses().get(0).toString());
    }
	
	@Test
    public void testAddReplyToNoName() throws Exception {
		emailTest.addReplyTo("replyToTest@gmail.com", "");
        assertEquals("replyToTest@gmail.com", emailTest.getReplyToAddresses().get(0).toString());
    }
	
	@Test
    public void testAddReplyToNoEmail() throws Exception {
		try {
			emailTest.addReplyTo("", "testName");
		} catch(EmailException e) {
			assertEquals(new EmailException(new AddressException("Illegal address in string ``''")).getMessage(), e.getMessage());
		}
    }
	
	@Test
    public void testBuildMimeMessage() throws Exception {
		emailTest.setHostName("locahost");
		emailTest.addTo("foo@bar.com");
		emailTest.setFrom("test@gmail.com");
		emailTest.setSubject("Test message");
		emailTest.setCharset("ASCII");
		emailTest.setMsg("This is a simple test of commons-email");
		emailTest.addBcc("testEmailBcc@gmail.com");
		emailTest.addCc("testEmailCc@gmail.com");
		emailTest.addReplyTo("testEmailReply@gmail.com");
		emailTest.addHeader("testHeader", "4");
		
		emailTest.buildMimeMessage();
		assertNotNull(emailTest.getMimeMessage());
    }
	
	@Test
    public void testBuildMimeMessageNoFrom() throws Exception {
		emailTest.setHostName("locahost");
		emailTest.addTo("foo@bar.com");
		emailTest.setSubject("Test message");
		emailTest.setMsg("This is a simple test of commons-email");
		
		try {
			emailTest.buildMimeMessage();
		} catch(EmailException e) {
			assertEquals("From address required", e.getMessage().toString());
		}
    }
	
	@Test
    public void testBuildMimeMessageNoList() throws Exception {
		emailTest.setHostName("locahost");
		emailTest.setFrom("test@gmail.com");
		emailTest.setSubject("Test message");
		emailTest.setMsg("This is a simple test of commons-email");
		
		try {
			emailTest.buildMimeMessage();
		} catch(EmailException e) {
			assertEquals("At least one receiver address required", e.getMessage().toString());
		}
    }
	
	@Test
    public void testBuildMimeMessageNoMessage() throws Exception {
		emailTest.setHostName("locahost");
		emailTest.addTo("foo@bar.com");
		emailTest.setFrom("test@gmail.com");
		emailTest.setSubject("Test message");
		
		emailTest.buildMimeMessage();
		assertNotNull(emailTest.getMimeMessage());
    }
	
	@Test
    public void testBuildMimeMessageAlreadyExists() throws Exception {
		emailTest.setHostName("locahost");
		emailTest.addTo("foo@bar.com");
		emailTest.setFrom("test@gmail.com");
		emailTest.setSubject("Test Subject");
		emailTest.setMsg("This is a test");
		
		emailTest.buildMimeMessage();
		try {
			emailTest.buildMimeMessage();
		} catch(IllegalStateException e) {
			assertEquals("The MimeMessage is already built.", e.getMessage().toString());
		}
    }
	
	@Test
    public void testGetHostName() throws Exception {
		emailTest.setHostName("my.server.com");
		assertNotNull(emailTest.getHostName());
    }
	
	@Test
    public void testGetHostNameNullSession() throws Exception {
        assertEquals(null, emailTest.getHostName());
    }
	
	@SuppressWarnings("deprecation")
	@Test
    public void testGetMailSession() throws Exception {
        emailTest.setSSL(true);
        emailTest.setSSLCheckServerIdentity(true);
        
        emailTest.setHostName("locahost");
		emailTest.addTo("foo@bar.com");
		emailTest.setFrom("test@gmail.com");
		emailTest.setSubject("Test Subject");
		emailTest.setMsg("This is a test");
		
		emailTest.buildMimeMessage();
        
        assertNotNull(emailTest.getMailSession());
    }
	
	@Test
	public void testGetSessionSSLOnConnect() throws Exception {
	    emailTest.setSSLOnConnect(true);
	    emailTest.setHostName("localhost");
		
		assertNotNull(emailTest.getMailSession());
	}
	
	@Test
	public void testGetMailSessionNoHostName() throws Exception {
		emailTest.setSSLOnConnect(true);
		
		try {
			emailTest.getMailSession();
		} catch(EmailException e) {
			assertEquals(new EmailException("Cannot find valid hostname for mail session").getMessage(), e.getMessage());
		}
	}
	
	@Test
    public void testGetSentDateNull() throws Exception {
        assertEquals(new Date(), emailTest.getSentDate());
    }
	
	@Test
    public void testGetSentDate() throws Exception {
		Date date = new Date();
		emailTest.setSentDate(date);
        assertEquals(date, emailTest.getSentDate());
    }
	
	@Test
    public void testGetSocketConnectionTimeout() throws Exception {
        assertNotNull(emailTest.getSocketConnectionTimeout());
    }
	
	@Test
    public void testSend() throws Exception {
		emailTest.setHostName("localhost");
		emailTest.setFrom("test@gmail.com");
		emailTest.setSubject("Test Mail");
		emailTest.setMsg("This is a test mail");
		emailTest.addTo("foo@bar.com");
		emailTest.setSmtpPort(2525);
		try{
			assertNotNull(emailTest.send());
		} catch(EmailException e){
			System.out.println("Error: Run fakeSMTP listening to port 2525");
		}
    }
	
	@Test
    public void testSetFrom() throws Exception {
		emailTest.setFrom("testEmail@gmail.com");
        assertEquals("testEmail@gmail.com", emailTest.getFromAddress().getAddress());
    }
	
	@Test
    public void testUpdateContentType() throws Exception {
        emailTest.updateContentType("test");
        assertNotNull(emailTest.contentType);
    }
}
