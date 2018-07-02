package aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import exception.LoginException;
import logic.User;

@Component // 객체화
@Aspect // 내가 바로 AOP 클래스로 사용됨. 여러개라면 @Order로 순서를 지정해 줄 수 있음.
public class LoginAspect { // 객체화 됨.
	// UserController.mypage(String id, HttpSession session) 메서드가 호출전에
	// userLoginCheck(..) 메서드 호출됨
	@Around("execution(* controller.User*.my*(..))") // @Around : mypage보다 나를 먼저실행하라
	public Object userLoginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		String id = null;
		HttpSession session = null;
		User paramUser = null;
		if (joinPoint.getArgs()[0] instanceof User) {
			paramUser = (User) joinPoint.getArgs()[0];
			session = (HttpSession) joinPoint.getArgs()[2];
			id = paramUser.getUserId();
		} else {
			id = (String) joinPoint.getArgs()[0]; // 파라미터 id값
			session = (HttpSession) joinPoint.getArgs()[1]; // 세션 값
			// @Around에 joinPoint를 쓰는데 그때 컨트롤러에서 매개변수를 설정해준순서대로 []배열로 설정해줘야한다.
		}
		User loginUser = (User) session.getAttribute("loginUser"); // 세션에 속성으로 등록된 loginUser를 객체로 저장
		if (loginUser == null) { // 로그인이 아니면
			throw new LoginException("로그인 후 거래하세요", "../user/login.shop"); // exception부분을 xml에 추가해줌.
		}
		if (!id.equals(loginUser.getUserId()) && !loginUser.getUserId().equals("admin")) { // 세션에 로그인유저의 id파라미터가 id와
																							// 다르거나, admin이 아니면
			throw new LoginException("본인만 거래 가능합니다.", "../user/mypage.shop?id=" + loginUser.getUserId());
		}
		Object ret = joinPoint.proceed(); // 이 구문이 있어야 @Around메서드가 정상실행된다.
		return ret;
	}

	@Around("execution(* controller.Admin*.*(..))") // @Around : mypage보다 나를 먼저실행하라
	public Object adminLoginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		HttpSession session = null;
		User loginUser = null;
		boolean adminable = false;

		for (int i = 0; i < joinPoint.getArgs().length; i++) {
			if (joinPoint.getArgs()[i] instanceof HttpSession) { // 세션이 있는 경우만 로그인을 할지 안할지 결정
				session = (HttpSession) joinPoint.getArgs()[i];
				loginUser = (User) session.getAttribute("loginUser");
				if (loginUser == null) {
					throw new LoginException("관리자로 로그인 하세요", "../user/login.shop");
				}
				if (!loginUser.getUserId().equals("admin")) {
					throw new LoginException("관리자만 가능한 거래입니다.", "../user/mypage.shop?id=" + loginUser.getUserId());
				}
				adminable = true;
				break;
			}
		}
		if (!adminable) {
			throw new LoginException("전산부로 전화하세요. 세션 객체가 요구됨", "../user/mypage.shop?id=" + loginUser.getUserId());
		}
		Object ret = joinPoint.proceed();
		return ret;
	}
}
