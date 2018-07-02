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
			throw new ShopException("메일을 보낼 대상자를 선택하세요", "admin.shop");
		}
		List<User> userList = service.userList(idchks);
		mav.addObject("userList", userList);
		return mav;
	}

	// private : 내부 클래스 - final이 붙어있어서 이 클래스는 상속받을 수 없는 클래스로 지정.
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
		// 입력된 네이버 이메일 주소, 또는 아이디
		String naverid = mail.getNaverid();
		// 입력된 네이버 비밀번호
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
		// 네이버에게 허가를 받는 부분. 세션 권한을 얻는다. 프로퍼티 값과, 인증 값
		Session session = Session.getInstance(prop, auth);
		// 메일 전송 객체
		MimeMessage msg = new MimeMessage(session);
		try {
			// 보내는 사람 설정 - id는 문자열이기때문에 메일 주소로 바꿈.
			msg.setFrom(new InternetAddress(naverid));
			List<InternetAddress> addrs = new ArrayList<InternetAddress>();
			// mail.getRecipient() : 받는 사람들,
			String[] emails = mail.getRecipient().split(",");
			for (int i = 0; i < emails.length; i++) { // 받는 사람이 여러명 일 수 있어서 for문 사용
				try {
					// 웹의 기본인코딩인 8859_1로 변경해줘야 한글이 출력됨.
					addrs.add(new InternetAddress(new String(emails[i].getBytes("euc-kr"), "8859_1")));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			InternetAddress[] arr = new InternetAddress[emails.length];
			for (int i = 0; i < addrs.size(); i++) {
				arr[i] = addrs.get(i);
			}
			msg.setSentDate(new Date()); // 보낸 날짜
			InternetAddress from = new InternetAddress(naverid);
			msg.setFrom(from);
			// Message.RecipientType.TO : 받는 사람 설정
			msg.setRecipients(Message.RecipientType.TO, arr);
			msg.setSubject(mail.getTitle());

			MimeMultipart multipart = new MimeMultipart(); // 파일도 보내기위함.

			// 내용 부븐을 Part로 지정
			MimeBodyPart message = new MimeBodyPart();
			message.setContent(mail.getContents(), mail.getMtype());
			multipart.addBodyPart(message); // Mail.java에서 List로 받기 때문에 여러개의 첨부파일을 보낼 수 있도록 구현(계속 추가가능)
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
			mf.transferTo(f1); // 서버에 임시로 저장
			body.attachFile(f1); // 저장된 파일을 메일에 첨부
			body.setFileName(new String(orgFile.getBytes("EUC-KR"), "8859_1")); // 첨부된 파일의 이름 설정
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
