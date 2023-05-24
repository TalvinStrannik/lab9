package teacher.servlet;

import teacher.entity.Ad;
import teacher.entity.AdList;
import teacher.entity.UserList;
import teacher.helper.AdListHelper;
import teacher.helper.UserListHelper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class StartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		UserList userList =	UserListHelper.readUserList(getServletContext());
		getServletContext().setAttribute("users", userList);
		AdList adList = AdListHelper.readAdList(getServletContext());
		getServletContext().setAttribute("ads", adList);
		for (Ad ad: adList.getAds()) {
			ad.setAuthor(userList.findUser(ad.getAuthorId()));
			ad.setLastModified(ad.getLastModified());
		}
	}
}
