package ru.maratk;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import java.sql.SQLException;

@ApplicationScoped
public class App {
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) throws SQLException {
        org.h2.tools.Server.createTcpServer().start();
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) throws SQLException {
        org.h2.tools.Server.createTcpServer().stop();
    }
}