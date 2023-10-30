package tabiMax.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailSender {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	@Async
	public void send(String to, String email) {
//        SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom("ngotuankiet12347@gmail.com");
//		message.setTo(to);
//		message.setSubject("Confirm your email");
//		message.setText(email);
//		// sending message
//		mailSender.send(message);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		try {
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Confirm your email");
			helper.setFrom("ngotuankiet12347@gmail.com");
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
