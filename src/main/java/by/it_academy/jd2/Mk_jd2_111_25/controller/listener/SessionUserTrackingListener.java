package by.it_academy.jd2.Mk_jd2_111_25.controller.listener;


import by.it_academy.jd2.Mk_jd2_111_25.core.ContextFactory;
import by.it_academy.jd2.Mk_jd2_111_25.core.dto.AppStatistics;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IMessageService;
import by.it_academy.jd2.Mk_jd2_111_25.service.api.IUserService;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

@WebListener
public class SessionUserTrackingListener implements HttpSessionAttributeListener, ServletContextListener {

    public static final String STATISTICS_ATTR = "appStatistics";

    private AppStatistics statistics;

    private final IUserService serviceUsers = ContextFactory.getBean(IUserService.class);
    private final IMessageService serviceMessages = ContextFactory.getBean(IMessageService.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        statistics = new AppStatistics();
        statistics.setTotalUsers(serviceUsers.count());
        statistics.setTotalMessages(serviceMessages.count());
        sce.getServletContext().setAttribute(STATISTICS_ATTR, statistics);
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if ("user".equals(event.getName())) {
            String login = event.getValue().toString();
            statistics.userLoggedIn(login);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if ("user".equals(event.getName())) {
            String login = event.getValue().toString();
            statistics.userLoggedOut(login);
        }
    }

}

