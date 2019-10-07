package member.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.dao.MemberDAO;

public class LogoutAction implements CommandProcess {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		HttpSession session = request.getSession();
		session.invalidate();

		int colen = 0;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			colen = cookies.length;
		}
		if (colen > 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("loginCookie")) { // ��Ű�� ������
					cookies[i].setPath("/");
					// ��Ű�� ���� �� ��ȿ�ð��� 0���� �����ϴ� �� !!! invalidate������ ����.
					cookies[i].setMaxAge(0);
					// ��Ű ������ �����Ѵ�.
					response.addCookie(cookies[i]);

					// ����� ���̺����� ��ȿ�Ⱓ�� ����ð����� �ٽ� �����������.
					MemberDAO memberDAO = MemberDAO.getInstance();

					// DB ��Ű���� �ʱ�ȭ [�Է� ��Ű ID]
					memberDAO.sessionClear(cookies[i].getValue());
				}
			}
		}

		request.setAttribute("display", "/member/logout.jsp");
		return "/main/index.jsp";

	}
}
