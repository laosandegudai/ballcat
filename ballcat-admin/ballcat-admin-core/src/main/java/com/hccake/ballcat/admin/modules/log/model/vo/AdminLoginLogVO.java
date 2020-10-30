package com.hccake.ballcat.admin.modules.log.model.vo;

import com.hccake.ballcat.admin.constants.LoginEventTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 登陆日志
 *
 * @author hccake 2020-09-16 20:21:10
 */
@Data
@ApiModel(value = "登陆日志")
public class AdminLoginLogVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@ApiModelProperty(value = "编号")
	private Long id;

	/**
	 * 追踪ID
	 */
	@ApiModelProperty(value = "追踪ID")
	private String traceId;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String username;

	/**
	 * 操作信息
	 */
	@ApiModelProperty(value = "操作信息")
	private String ip;

	/**
	 * 操作系统
	 */
	@ApiModelProperty(value = "操作系统")
	private String os;

	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	private Integer status;

	/**
	 * 日志消息
	 */
	@ApiModelProperty(value = "日志消息")
	private String msg;

	/**
	 * 登陆地点
	 */
	@ApiModelProperty(value = "登陆地点")
	private String location;

	/**
	 * 事件类型 登陆/登出
	 * @see LoginEventTypeEnum
	 */
	@ApiModelProperty(value = "事件类型")
	private Integer eventType;

	/**
	 * 浏览器
	 */
	@ApiModelProperty(value = "浏览器")
	private String browser;

	/**
	 * 登录/登出时间
	 */
	@ApiModelProperty(value = "登录/登出时间")
	private LocalDateTime loginTime;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

}