package com.hccake.starter.sms.properties.extra;

import java.util.List;
import lombok.Data;

/**
 * 天一泓 配置
 *
 * @author lingting 2020/5/6 17:16
 */
@Data
public class TianYiHong {

	/**
	 * 密码的密钥
	 */
	private String passwordKey;

	/**
	 * 安全密钥
	 */
	private String secretKey;

	private String senderId;

	/**
	 * 要添加 senderId 的国家
	 */
	private List<String> senderIdCountry;

}
