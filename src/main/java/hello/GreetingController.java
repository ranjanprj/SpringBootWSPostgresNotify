package hello;

import java.security.Principal;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
    public void greeting(Message<Object> message, @Payload HelloMessage chatMessage) throws Exception {
        Principal principal = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);
        String authedSender = principal.getName();

        System.out.println("=========RECEIVED============");
        System.out.println(authedSender);
        Greeting greeting = new Greeting("Hello, " + chatMessage.getName() + "!" + new Date());
//        return greeting;
        
        try {
            Statement stmt = PGConn.getConn().createStatement();
            stmt.execute("NOTIFY  messages,'" + authedSender + "|" + chatMessage.getName() + "'");
            stmt.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
//        template.convertAndSendToUser(authedSender, "/topic/greetings", greeting);
//        template.convertAndSend( "/topic/greetings", greeting);
    }

}
