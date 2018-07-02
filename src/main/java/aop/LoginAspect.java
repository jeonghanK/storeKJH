package aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import exception.LoginException;
import logic.User;

@Component // ��üȭ
@Aspect // ���� �ٷ� AOP Ŭ������ ����. ��������� @Order�� ������ ������ �� �� ����.
public class LoginAspect { // ��üȭ ��.
	// UserController.mypage(String id, HttpSession session) �޼��尡 ȣ������
	// userLoginCheck(..) �޼��� ȣ���
	@Around("execution(* controller.User*.my*(..))") // @Around : mypage���� ���� ���������϶�
	public Object userLoginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		String id = null;
		HttpSession session = null;
		User paramUser = null;
		if (joinPoint.getArgs()[0] instanceof User) {
			paramUser = (User) joinPoint.getArgs()[0];
			session = (HttpSession) joinPoint.getArgs()[2];
			id = paramUser.getUserId();
		} else {
			id = (String) joinPoint.getArgs()[0]; // �Ķ���� id��
			session = (HttpSession) joinPoint.getArgs()[1]; // ���� ��
			// @Around�� joinPoint�� ���µ� �׶� ��Ʈ�ѷ����� �Ű������� �������ؼ������ []�迭�� ����������Ѵ�.
		}
		User loginUser = (User) session.getAttribute("loginUser"); // ���ǿ� �Ӽ����� ��ϵ� loginUser�� ��ü�� ����
		if (loginUser == null) { // �α����� �ƴϸ�
			throw new LoginException("�α��� �� �ŷ��ϼ���", "../user/login.shop"); // exception�κ��� xml�� �߰�����.
		}
		if (!id.equals(loginUser.getUserId()) && !loginUser.getUserId().equals("admin")) { // ���ǿ� �α��������� id�Ķ���Ͱ� id��
																							// �ٸ��ų�, admin�� �ƴϸ�
			throw new LoginException("���θ� �ŷ� �����մϴ�.", "../user/mypage.shop?id=" + loginUser.getUserId());
		}
		Object ret = joinPoint.proceed(); // �� ������ �־�� @Around�޼��尡 �������ȴ�.
		return ret;
	}

	@Around("execution(* controller.Admin*.*(..))") // @Around : mypage���� ���� ���������϶�
	public Object adminLoginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		HttpSession session = null;
		User loginUser = null;
		boolean adminable = false;

		for (int i = 0; i < joinPoint.getArgs().length; i++) {
			if (joinPoint.getArgs()[i] instanceof HttpSession) { // ������ �ִ� ��츸 �α����� ���� ������ ����
				session = (HttpSession) joinPoint.getArgs()[i];
				loginUser = (User) session.getAttribute("loginUser");
				if (loginUser == null) {
					throw new LoginException("�����ڷ� �α��� �ϼ���", "../user/login.shop");
				}
				if (!loginUser.getUserId().equals("admin")) {
					throw new LoginException("�����ڸ� ������ �ŷ��Դϴ�.", "../user/mypage.shop?id=" + loginUser.getUserId());
				}
				adminable = true;
				break;
			}
		}
		if (!adminable) {
			throw new LoginException("����η� ��ȭ�ϼ���. ���� ��ü�� �䱸��", "../user/mypage.shop?id=" + loginUser.getUserId());
		}
		Object ret = joinPoint.proceed();
		return ret;
	}
}
