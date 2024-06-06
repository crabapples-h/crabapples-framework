package cn.crabapples.system.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;

/**
 * TODO webSocketService
 *
 * @author Mr.He
 * 2019/8/5 22:53
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Slf4j
@Service
public class MessageService {
    @Qualifier("webSocketClientMap")
    private Map<String, Session> webSocketClientMap;

    public void sendMessage(String message, String sid) throws IOException {
        Session session = webSocketClientMap.get(sid);
        if (session == null) {
            return;
        }
        session.getBasicRemote().sendText("发往客户端的消息-->" + message);
    }
}
