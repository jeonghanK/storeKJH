package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import exception.ShopException;
import logic.Mail;
import logic.ShopService;
import logic.User;

@Controller
public class AdminController {
	@Autowired
	private ShopService service;

	@RequestMapping("admin/admin")
	public ModelAndView admin(String id, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		List<User> userList = service.userList();
		mav.addObject("userList", userList);
		return mav;
	}

	@RequestMapping("admin/mailForm")
	public ModelAndView mailForm(String[] idchks, HttpSession session) {
		ModelAndView mav = new ModelAndView("admin/mail");
		if (idchks == null || idchks.length == 0) {
			throw new ShopException("������ ���� ����ڸ� �����ϼ���", "admin.shop");
		}
		List<User> userList = service.userList(idchks);
		mav.addObject("userList", userList);
		return mav;
	}

	// private : ���� Ŭ���� - final�� �پ��־ �� Ŭ������ ��ӹ��� �� ���� Ŭ������ ����.
	private final class MyAuthenticator extends Authenticator {
		private String id;
		private String pw;

		public MyAuthenticator(String id, String pw) {
			this.id = id;
			this.pw = pw;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(id, pw);
		}
	}

	@RequestMapping("admin/mail")
	public ModelAndView mail(Mail mail, HttpSession session) {
		ModelAndView mav = new ModelAndView("admin/mailsuccess");
		adminMailSend(mail);
		return mav;
	}

	private void adminMailSend(Mail mail) {
		System.out.println(mail);
		// �Էµ� ���̹� �̸��� �ּ�, �Ǵ� ���̵�
		String naverid = mail.getNaverid();
		// �Էµ� ���̹� ��й�ȣ
		String naverpass = mail.getNaverpass();
		MyAuthenticator auth = new MyAuthenticator(naverid, naverpass);
		Properties prop = new Properties();
		FileInputStream fis = null;
		try {
			File f = new File("d:/20180122/spring/shop-3/mail.properties");
			fis = new FileInputStream(f);
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ���̹����� �㰡�� �޴� �κ�. ���� ������ ��´�. ������Ƽ ����, ���� ��
		Session session = Session.getInstance(prop, auth);
		// ���� ���� ��ü
		MimeMessage msg = new MimeMessage(session);
		try {
			// ������ ��� ���� - id�� ���ڿ��̱⶧���� ���� �ּҷ� �ٲ�.
			msg.setFrom(new InternetAddress(naverid));
			List<InternetAddress> addrs = new ArrayList<InternetAddress>();
			// mail.getRecipient() : �޴� �����,
			String[] emails = mail.getRecipient().split(",");
			for (int i = 0; i < emails.length; i++) { // �޴� ����� ������ �� �� �־ for�� ���
				try {
					// ���� �⺻���ڵ��� 8859_1�� ��������� �ѱ��� ��µ�.
					addrs.add(new InternetAddress(new String(emails[i].getBytes("euc-kr"), "8859_1")));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			InternetAddress[] arr = new InternetAddress[emails.length];
			for (int i = 0; i < addrs.size(); i++) {
				arr[i] = addrs.get(i);
			}
			msg.setSentDate(new Date()); // ���� ��¥
			InternetAddress from = new InternetAddress(naverid);
			msg.setFrom(from);
			// Message.RecipientType.TO : �޴� ��� ����
			msg.setRecipients(Message.RecipientType.TO, arr);
			msg.setSubject(mail.getTitle());

			MimeMultipart multipart = new MimeMultipart(); // ���ϵ� ����������.

			// ���� �κ��� Part�� ����
			MimeBodyPart message = new MimeBodyPart();
			message.setContent(mail.getContents(), mail.getMtype());
			multipart.addBodyPart(message); // Mail.java���� List�� �ޱ� ������ �������� ÷�������� ���� �� �ֵ��� ����(��� �߰�����)
			for (MultipartFile mf : mail.getFile1()) {
				if ((mf != null) && (!mf.isEmpty())) {
					multipart.addBodyPart(bodyPart(mf));
				}
			}
			msg.setContent(multipart);
			Transport.send(msg);
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	private BodyPart bodyPart(MultipartFile mf) {
		MimeBodyPart body = new MimeBodyPart();
		String orgFile = mf.getOriginalFilename();
		File f1 = new File("d:/20180122/shop-3-mail/email/upload/" + orgFile);
		try {
			mf.transferTo(f1); // ������ �ӽ÷� ����
			body.attachFile(f1); // ����� ������ ���Ͽ� ÷��
			body.setFileName(new String(orgFile.getBytes("EUC-KR"), "8859_1")); // ÷�ε� ������ �̸� ����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}

	@RequestMapping("admin/graph*")
	public ModelAndView graph(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> map = service.graph();
		Map<String, Object> treemap = new TreeMap<String, Object>(map);
		mav.addObject("map", treemap);
		return mav;
	}
}
