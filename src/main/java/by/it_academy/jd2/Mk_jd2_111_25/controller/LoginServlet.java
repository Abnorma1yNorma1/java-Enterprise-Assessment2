package by.it_academy.jd2.Mk_jd2_111_25.controller;

import by.it_academy.jd2.Mk_jd2_111_25.service.ServiceFactory;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/api/login")
public class LoginServlet extends HttpServlet {

    private final IUserService service = ServiceFactory.getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

//        try {
            if (service.authenticate(login, password)){
                HttpSession session = req.getSession();
                session.setAttribute("user", login);
//                resp.sendRedirect(".jsp");
            } else {
//                req.setAttribute("error", "Invalid credentials");
//                req.getRequestDispatcher("/.jsp").forward(req, resp);
            };
//        } catch (StorageException e) {
//            req.setAttribute("errorMessage", e.getMessage());
//            req.getRequestDispatcher("/.jsp").forward(req, resp);
//        }
    }

}
