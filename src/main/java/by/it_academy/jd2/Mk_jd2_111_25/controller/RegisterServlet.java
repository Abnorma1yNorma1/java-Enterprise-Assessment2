package by.it_academy.jd2.Mk_jd2_111_25.controller;

import by.it_academy.jd2.Mk_jd2_111_25.dto.Role;
import by.it_academy.jd2.Mk_jd2_111_25.service.ServiceFactory;
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

    private final IUserService service = ServiceFactory.getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String birthDateStr = req.getParameter("date");

        if (login == null || login.isBlank() ||
                password == null || password.isBlank() ||
                name == null || name.isBlank() ||
                birthDateStr == null || birthDateStr.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("All fields are required");
            return;
        }

        LocalDate birthDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            birthDate = LocalDate.parse(birthDateStr, formatter);
        } catch (DateTimeParseException e){
            throw new ServletException("Invalid date format, expected yyyy-MM-dd");
        }

        Role role = Role.USER;

        try {
            service.addUser(login, password, name, birthDate, role);
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.sendRedirect(req.getContextPath() + "/ui/signIn");
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Server error: " + e.getMessage());
        }
    }

}
