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
		
		HttpSession session = (HttpSession) joinPoint.getArgs()[0]; // 매개변수의 순서를 배열로 지정
		if (session.getAttribute("loginUser") == null) { // 로그인이 아니면
			throw new LoginException("로그인이 필요합니다.", "../user/login.shop"); // exception부분을 xml에 추가해줌.
		}
		
		Cart cart = (Cart) session.getAttribute("CART"); // CART라는 애를 가져와 형변환 하여 사용.
		if (cart == null || cart.isEmpty()) { // 카트가 비었다면
			throw new LoginException("장바구니가 비어있습니다.", "../item/list.shop"); // exception부분을 xml에 추가해줌.
		}
		Object ret = joinPoint.proceed(); // 이 구문이 있어야 @Around메서드가 정상실행된다.
		return ret;
	}
}
