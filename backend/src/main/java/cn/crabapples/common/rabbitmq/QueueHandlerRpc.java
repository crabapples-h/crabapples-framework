package cn.crabapples.common.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
//@Component
public class QueueHandlerRpc {

    @RabbitListener(queues = RabbitRpcConfigure.QUEUE_NAME)
//    @RabbitHandler
    public void messageHandler(Channel channel, Message message) throws IOException {
        String messageStr = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("[{}]收到MQ消息:[{}]", "messageHandler", messageStr);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
