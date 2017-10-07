/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.postgresql.PGConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledUpdatesOnTopic {

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedDelay = 3000)
    public void publishUpdates() {
     
        System.out.println("****************Listening PG Notify*******************");
        try {
            // issue a dummy query to contact the backend
            // and receive any pending notifications.
            Connection conn = PGConn.getConn();
            PGConnection pgconn = (org.postgresql.PGConnection)conn;
            Statement stmt = conn.createStatement();
            stmt.execute("LISTEN messages");
            ResultSet rs = stmt.executeQuery("SELECT 1");
            rs.close();
            stmt.close();
            
            org.postgresql.PGNotification notifications[] = pgconn.getNotifications();
            System.out.println(notifications);
            if (notifications != null) {
                
                for (int i = 0; i < notifications.length; i++) {
                    System.out.println("Got notification: " + notifications[i].getName() + notifications[i].getParameter());
                    String[] msgs = notifications[i].getParameter().split("\\|");
                    System.out.println(msgs[0].trim()+ " -- "+ msgs[1].trim());
                    template.convertAndSendToUser(msgs[0].trim(),"/topic/greetings", msgs[1]);
                }
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }
}
