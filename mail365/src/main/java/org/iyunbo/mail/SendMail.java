package org.iyunbo.mail;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;

class SendMail {


	void execute() throws MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.connectiontimeout", "3600");
		props.put("mail.smtp.timeout", "3600");
		props.put("mail.debug", true);
		Session session = Session.getInstance(props);
		session.setDebug(true);

		Transport transport = session.getTransport();
		transport.connect("smtp.office365.com", 587, "yunbo.wang@outlook.com", "zedfretg0607");
	}
}
