package com.hccake.ballcat.admin.websocket.distribute;

import com.hccake.ballcat.common.core.util.JacksonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * redis订阅 websocket 发送消息，接收到消息时进行推送
 *
 * @author Hccake 2021/1/12
 * @version 1.0
 */
@RequiredArgsConstructor
public class RedisWebsocketMessageListener implements MessageListener, MessageSender {

	public final static String CHANNEL = "websocket-send";

	private final StringRedisTemplate stringRedisTemplate;

	@Override
	public void onMessage(Message message, byte[] bytes) {
		byte[] channelBytes = message.getChannel();
		RedisSerializer<String> stringSerializer = stringRedisTemplate.getStringSerializer();
		String channel = stringSerializer.deserialize(channelBytes);

		// 这里没有使用通配符，所以一定是true
		if (CHANNEL.equals(channel)) {
			byte[] bodyBytes = message.getBody();
			String body = stringSerializer.deserialize(bodyBytes);
			MessageDO messageDO = JacksonUtils.toObj(body, MessageDO.class);
			doSend(messageDO);
		}
	}

}
