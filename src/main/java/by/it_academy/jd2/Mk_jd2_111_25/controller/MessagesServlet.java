package by.it_academy.jd2.Mk_jd2_111_25.controller;

import by.it_academy.jd2.Mk_jd2_111_25.dto.Message;
import by.it_academy.jd2.Mk_jd2_111_25.service.ServiceFactory;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IMessageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/api/message")
public class MessagesServlet extends HttpServlet {

    private final IMessageService service = ServiceFactory.getMessageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String login = session.getAttribute("user").toString();
        List<Message> messages = service.getMessages(login);
//        resp.sendRedirect(".jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        String login = session.getAttribute("user").toString();
//        resp.sendRedirect(".jsp");
    }

}
