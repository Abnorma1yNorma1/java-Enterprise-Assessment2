package by.it_academy.jd2.Mk_jd2_111_25.filter;

import by.it_academy.jd2.Mk_jd2_111_25.dto.Role;
import by.it_academy.jd2.Mk_jd2_111_25.service.ServiceFactory;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IUserService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/ui/admin/*", "/api/admin/*"})
public class AdminSecurityFilter implements Filter {

    private final IUserService service = ServiceFactory.getUserService();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String contextPath = req.getContextPath();
        HttpSession session = req.getSession(false);
        if ((session != null) && (session.getAttribute("user") != null)) {
            String login = session.getAttribute("user").toString();

            Role role;
            try {
                role = service.getRole(login);
            } catch (Exception e) {
                res.sendRedirect(contextPath + "/ui/signIn");
                return;
            }

            if (Role.ADMIN.equals(role)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

        }
        res.sendRedirect(contextPath + "/ui/signIn");

    }
}
