package org.iyunbo.mail;

import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;

class SendMailTest {

	private final SendMail sendMail = new SendMail();

	@Test
	void sendMail() throws MessagingException {
		sendMail.execute();
	}
}