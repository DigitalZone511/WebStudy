package member.action;

import java.sql.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class LoginAction implements CommandProcess {
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
		// ������
		String autoLogin = request.getParameter("autoLogin"); // üũ�Ǹ� ON �ȵǸ� NULL;
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(id);
		memberDTO.setPwd(pwd);

		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDTO = memberDAO.Select(memberDTO);

		if (memberDTO == null) {
			request.setAttribute("display", "/member/loginFail.jsp");

		} else {

			String name = memberDTO.getName();
			String email = memberDTO.getEmail1()+"@"+memberDTO.getEmail2();
			HttpSession session = request.getSession();

			session.setAttribute("memName", name);
			session.setAttribute("memId", id);
			session.setAttribute("memEmail", email);
			
			//���� ID�� ����� �߰�
			int amount = 60 * 60 * 24 * 7;
			Date sessionLimit = new Date(System.currentTimeMillis() + (1000*amount));
			memberDTO.setSessionkey(session.getId());
			memberDTO.setSessionlimit(sessionLimit);
			memberDAO.applySession(memberDTO);
			
			
			if (autoLogin != null) { // �ڵ��α��� üũ
				Cookie cookie = new Cookie("loginCookie", session.getId());
				// ��Ű�� ã�� ��θ� ���ؽ�Ʈ ��η� ������ �ְ�...
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 7); // ������ (��)������ 7�������� ��ȿ�ð��� ������ �ش�.
				// ��Ű�� ������ �ش�.
				response.addCookie(cookie);
			}

			request.setAttribute("display", "/member/loginOk.jsp");
		}
		return "/main/index.jsp";
	}
}
