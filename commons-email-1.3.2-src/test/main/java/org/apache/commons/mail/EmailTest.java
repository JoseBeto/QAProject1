package org.apache.commons.mail;
import org.junit.Test;
import junit.framework.TestCase;

public class EmailTest extends TestCase {
	
	protected SimpleEmail emailTest = new SimpleEmail();
	
	@Test
    public void testAddBcc() throws Exception {
		assertEquals(emailTest
				, emailTest.addBcc("Test@gmail.com"));
    }
	
	@Test
    public void testAddCc() throws Exception {
        
    }
	
	@Test
    public void testAddHeader() throws Exception {
        
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
