package by.it_academy.jd2.Mk_jd2_111_25.controller;

import by.it_academy.jd2.Mk_jd2_111_25.dto.AppStatistics;
import by.it_academy.jd2.Mk_jd2_111_25.controller.listener.SessionUserTrackingListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/api/admin/statistics")
public class StatisticsServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        AppStatistics stats = (AppStatistics) getServletContext().getAttribute(SessionUserTrackingListener.STATISTICS_ATTR);
        if (stats == null) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Statistics not initialized");
            return;
        }
        req.setAttribute("activeUsers", stats.getActiveUsersCount());
        req.setAttribute("totalUsers", stats.getTotalUsers());
        req.setAttribute("totalMessages", stats.getTotalMessages());
        req.getRequestDispatcher("/WEB-INF/ui/admin/statistics.jsp").forward(req, resp);

    }
}
