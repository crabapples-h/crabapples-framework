package cn.crabapples.system.websocket;

import cn.crabapples.common.config.CustomSpringConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@ServerEndpoint(value = "/ws/{sid}", configurator = CustomSpringConfigure.class)
public class WebSocketController {
    @Qualifier("webSocketClientMap")
    private Map<String, Session> webSocketClientMap;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        log.info("当前连接的id:[{}]", sid);
        webSocketClientMap.put(sid, session);
        sendMessage(session, "连接成功" + sid);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session, @PathParam("sid") String sid) {
        webSocketClientMap.remove(sid);
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(Session session, String message, @PathParam("sid") String sid) {
        log.info("收到消息id:[{}],内容:[{}]", sid, message);
        webSocketClientMap.put(sid, session);
//        sendMessage(session, "收到来自" + sid + "的信息:" + message);
    }

    @OnError
    public void onError(Session session, Throwable error, @PathParam("sid") String sid) {
        webSocketClientMap.remove(sid);
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
