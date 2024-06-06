package cn.crabapples.system.sse;

import cn.crabapples.common.jwt.JwtIgnore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Objects;

/**
 * TODO SSE接口
 *
 * @author Mr.He
 * 2021/6/16 9:14
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name admin
 */
@Controller
@RequestMapping("/sse/")
@Slf4j
public class ServerSentEventController {

    @Qualifier("emitterClientMap")
    private Map<String, SseEmitter> emitterClientMap;

    public ServerSentEventController(Map<String, SseEmitter> emitterClientMap) {
        this.emitterClientMap = emitterClientMap;
    }

    @RequestMapping(value = "/connect/{id}")
    @JwtIgnore
    public SseEmitter createConnection(@PathVariable String id) {
        SseEmitter sseEmitter = emitterClientMap.get(id);
        if (Objects.isNull(sseEmitter)) {
            sseEmitter = new SseEmitter(1000L * 60 * 60);
            sseEmitter.onTimeout(() -> emitterClientMap.remove(id));
            sseEmitter.onError((e) -> emitterClientMap.remove(id));
            sseEmitter.onCompletion(() -> emitterClientMap.remove(id));
            emitterClientMap.put(id, sseEmitter);
        }
        return sseEmitter;
    }
}
