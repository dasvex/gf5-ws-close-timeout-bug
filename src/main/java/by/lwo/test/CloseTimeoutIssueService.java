package by.lwo.test;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint("/endpoint")
public class CloseTimeoutIssueService {
    private static final Logger loger = Logger.getLogger(CloseTimeoutIssueService.class.getName());

    private String field = null;

    @OnOpen
    public void onOpen(Session session) {
        try {
            loger.info("Session " + session.getId() + " opened");
            session.setMaxIdleTimeout(1000L*60*10);
            session.close();
        } catch (IOException e) {
            System.err.println("EXCEPTION:"+e.getMessage());
        }
    }

    @OnError
    public void onError(Throwable t) {
        loger.log(Level.SEVERE,"exception on ws",t);
    }

    @OnClose
    public void onClose(Session session) {
        loger.info("Session " + session.getId() + " has ended");
    }
}
