package main.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.CommandProcess;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class MainAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		int colen = 0;
		
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			colen = cookies.length;
		}
		if (colen > 0) {
			/* System.out.println("��Ű ũ�� : " + cookies.length); */
			HttpSession session = request.getSession();
			/* System.out.println("���� : " + session.getId()); */

			for (int i = 0; i < cookies.length; i++) {
				/*
				 * System.out.println("��Ű �̸�: " + cookies[i].getName() );
				 * System.out.println("��Ű �� : " + cookies[i].getValue());
				 */
				
				if (cookies[i].getName().equals("loginCookie")) { //��Ű�� ������ ��Ű�� ���� ���ǿ� �־���
					
					MemberDAO memberDAO = MemberDAO.getInstance();
					
					//����� ���������[���Ǿ��̵�� memberDTO]
					MemberDTO memberDTO = memberDAO.sessionToDTO(cookies[i].getValue());
					/* System.out.println(memberDTO.getId()); */					
					//��Ű�� �������� Ȯ��
					
					if(memberDTO!=null) {
						session.setAttribute("memName", memberDTO.getName());
						session.setAttribute("memId", memberDTO.getId());
					}
					
				}
			}
		}
		request.setAttribute("display", "../template/body.jsp");
		return "/main/index.jsp";
	}

}
