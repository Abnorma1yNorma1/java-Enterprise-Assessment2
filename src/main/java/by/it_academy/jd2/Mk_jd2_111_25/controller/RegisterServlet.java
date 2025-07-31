package by.it_academy.jd2.Mk_jd2_111_25.controller;

import by.it_academy.jd2.Mk_jd2_111_25.controller.listener.SessionUserTrackingListener;
import by.it_academy.jd2.Mk_jd2_111_25.core.ContextFactory;
import by.it_academy.jd2.Mk_jd2_111_25.core.dto.AppStatistics;
import by.it_academy.jd2.Mk_jd2_111_25.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@WebServlet(urlPatterns = "/api/user")
public class RegisterServlet extends HttpServlet {

    private final IUserService service = ContextFactory.getBean(IUserService.class);

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String birthDateStr = req.getParameter("birthDate");

        try {
            LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
            service.create(User.builder()
                    .login(login)
                    .password(password)
                    .name(name)
                    .birthDate(birthDate)
                    .build());
            AppStatistics statistics = (AppStatistics) getServletContext().getAttribute(SessionUserTrackingListener.STATISTICS_ATTR);
            if (statistics != null){
                statistics.setTotalUsers(service.count());
            }
            resp.sendRedirect(req.getContextPath() + "/ui/signIn");
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/ui/signUp.jsp").forward(req, resp);
        } catch (DateTimeParseException e ){
            req.setAttribute("error", "Неверный формат даты рождения");
            req.getRequestDispatcher("/WEB-INF/ui/signUp.jsp").forward(req, resp);
        }catch (Exception e) {
            req.setAttribute("error", "Внутренняя ошибка сервера");
            req.getRequestDispatcher("/WEB-INF/ui/signUp.jsp").forward(req, resp);

        }
    }

}
