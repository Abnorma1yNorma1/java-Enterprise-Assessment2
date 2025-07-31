package by.it_academy.jd2.Mk_jd2_111_25.controller;

import by.it_academy.jd2.Mk_jd2_111_25.controller.listener.SessionUserTrackingListener;
import by.it_academy.jd2.Mk_jd2_111_25.core.ContextFactory;
import by.it_academy.jd2.Mk_jd2_111_25.core.dto.AppStatistics;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IUserService;
import by.it_academy.jd2.Mk_jd2_111_25.storage.api.StorageException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/api/login")
public class LoginServlet extends HttpServlet {

    private final IUserService service = ContextFactory.getBean(IUserService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            if (service.authenticate(login, password)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", login);
                AppStatistics statistics = (AppStatistics) getServletContext().getAttribute(SessionUserTrackingListener.STATISTICS_ATTR);
                if (statistics != null){
                    statistics.userLoggedIn(login);
                }
                resp.sendRedirect(req.getContextPath() + "/ui/");
            } else {
                req.setAttribute("error", "Invalid credentials");
                req.getRequestDispatcher("/WEB-INF/ui/signIn.jsp").forward(req, resp);
            }
        } catch (StorageException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/ui/signIn.jsp").forward(req, resp);
        }
    }

}
