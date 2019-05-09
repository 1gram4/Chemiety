package cn.kastner.chemiety.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import cn.kastner.chemiety.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle (HttpServletRequest request,
                              HttpServletResponse response,
                              Object object) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(User.CUR_USER);
        if (null != user) {
            if (User.Role.TEACHER == user.getRoles()) {
                return true;
            } else {
                response.sendRedirect("/errorPage");
                return false;
            }
        } else {
            response.sendRedirect("/loginPage");
            return false;
        }

    }
}
