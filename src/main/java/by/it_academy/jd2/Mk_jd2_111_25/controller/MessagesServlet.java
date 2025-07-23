package by.it_academy.jd2.Mk_jd2_111_25.controller;

import by.it_academy.jd2.Mk_jd2_111_25.dto.AppStatistics;
import by.it_academy.jd2.Mk_jd2_111_25.dto.Message;
import by.it_academy.jd2.Mk_jd2_111_25.service.ServiceFactory;
import by.it_academy.jd2.Mk_jd2_111_25.service.SessionUserTrackingListener;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IMessageService;
import by.it_academy.jd2.Mk_jd2_111_25.storage.api.StorageException;
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
        Object userAttr = session.getAttribute("user");
        if (userAttr == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }
        String login = userAttr.toString();
        List<Message> messages = service.getMessages(login);
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/WEB-INF/ui/user/chats.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        Object userAttr = session.getAttribute("user");
        if (userAttr == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }
        String login = userAttr.toString();
        String toWhom = req.getParameter("toWhom");
        String text = req.getParameter("text");
        if (toWhom == null || toWhom.isBlank() || text == null || text.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameters");
            return;
        }
        Message message = new Message();
        message.setText(text);
        message.setToWhom(toWhom);
        message.setFromWho(login);
        try {
            service.send(message);
            AppStatistics statistics = (AppStatistics) getServletContext().getAttribute(SessionUserTrackingListener.STATISTICS_ATTR);
            if (statistics != null) {
                statistics.incrementMessages();
            }
        } catch (StorageException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
        }

        resp.sendRedirect(req.getContextPath()+"/ui/user/message");
    }

}
