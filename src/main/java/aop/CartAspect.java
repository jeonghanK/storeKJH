package aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import exception.LoginException;
import logic.Cart;

@Component
@Aspect
public class CartAspect {
	@Around("execution(* controller.Cart*.check*(..))")
	public Object loginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		
		HttpSession session = (HttpSession) joinPoint.getArgs()[0]; // �Ű������� ������ �迭�� ����
		if (session.getAttribute("loginUser") == null) { // �α����� �ƴϸ�
			throw new LoginException("�α����� �ʿ��մϴ�.", "../user/login.shop"); // exception�κ��� xml�� �߰�����.
		}
		
		Cart cart = (Cart) session.getAttribute("CART"); // CART��� �ָ� ������ ����ȯ �Ͽ� ���.
		if (cart == null || cart.isEmpty()) { // īƮ�� ����ٸ�
			throw new LoginException("��ٱ��ϰ� ����ֽ��ϴ�.", "../item/list.shop"); // exception�κ��� xml�� �߰�����.
		}
		Object ret = joinPoint.proceed(); // �� ������ �־�� @Around�޼��尡 �������ȴ�.
		return ret;
	}
}
