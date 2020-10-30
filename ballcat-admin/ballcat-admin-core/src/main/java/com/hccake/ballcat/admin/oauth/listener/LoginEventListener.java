package com.hccake.ballcat.admin.oauth.listener;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.hccake.ballcat.admin.constants.LoginEventTypeEnum;
import com.hccake.ballcat.admin.modules.log.model.entity.AdminLoginLog;
import com.hccake.ballcat.admin.modules.log.service.AdminLoginLogService;
import com.hccake.ballcat.commom.log.constant.LogConstant;
import com.hccake.ballcat.commom.log.operation.enums.LogStatusEnum;
import com.hccake.ballcat.commom.log.util.LogUtils;
import com.hccake.ballcat.common.core.util.IPUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * 登陆成功监听器
 *
 * @author Hccake
 * @version 1.0
 * @date 2020 /6/9 20:52
 */
@RequiredArgsConstructor
@Component
public class LoginEventListener {

	private final AdminLoginLogService adminLoginLogService;

	/**
	 * 登陆成功时间监听 记录用户登录日志
	 * @param event 登陆成功 event
	 */
	@EventListener(AuthenticationSuccessEvent.class)
	public void onAuthenticationSuccessEvent(AuthenticationSuccessEvent event) {

		AbstractAuthenticationToken source = (AbstractAuthenticationToken) event.getSource();
		Object details = source.getDetails();
		if (!(details instanceof HashMap)) {
			return;
		}
		// TODO 暂时只记录了 password 模式，其他的第三方登陆，等 spring-authorization-server 孵化后重构
		// https://github.com/spring-projects-experimental/spring-authorization-server
		if ("password".equals(((HashMap) details).get("grant_type"))) {
			AdminLoginLog adminLoginLog = prodLoginLog(source).setMsg("登陆成功")
					.setStatus(LogStatusEnum.SUCCESS.getValue()).setEventType(LoginEventTypeEnum.LOGIN.getValue());
			adminLoginLogService.save(adminLoginLog);
		}
	}

	/**
	 * 监听鉴权失败事件
	 * @param event the event
	 */
	@EventListener(AbstractAuthenticationFailureEvent.class)
	public void onAuthenticationFailureEvent(AbstractAuthenticationFailureEvent event) {
		AbstractAuthenticationToken source = (AbstractAuthenticationToken) event.getSource();
		AdminLoginLog adminLoginLog = prodLoginLog(source).setMsg(event.getException().getMessage())
				.setEventType(LoginEventTypeEnum.LOGIN.getValue()).setStatus(LogStatusEnum.FAIL.getValue());
		adminLoginLogService.save(adminLoginLog);
	}

	/**
	 * On logout success event.
	 * @param event the event
	 */
	@EventListener(LogoutSuccessEvent.class)
	public void onLogoutSuccessEvent(LogoutSuccessEvent event) {
		AbstractAuthenticationToken source = (AbstractAuthenticationToken) event.getSource();
		AdminLoginLog adminLoginLog = prodLoginLog(source).setMsg("登出成功")
				.setEventType(LoginEventTypeEnum.LOGOUT.getValue());
		adminLoginLogService.save(adminLoginLog);
	}

	/**
	 * 根据token和请求信息产生一个登陆日志
	 * @param source AbstractAuthenticationToken 当前token
	 * @return LoginLog 登陆日志
	 */
	private AdminLoginLog prodLoginLog(AbstractAuthenticationToken source) {
		// 获取 Request
		HttpServletRequest request = LogUtils.getHttpServletRequest();
		AdminLoginLog adminLoginLog = new AdminLoginLog().setLoginTime(LocalDateTime.now())
				.setIp(IPUtil.getIpAddr(request)).setStatus(LogStatusEnum.SUCCESS.getValue())
				.setTraceId(MDC.get(LogConstant.TRACE_ID)).setUsername(source.getName());
		// 根据 ua 获取浏览器和操作系统
		UserAgent ua = UserAgentUtil.parse(request.getHeader("user-agent"));
		if (ua != null) {
			adminLoginLog.setBrowser(ua.getBrowser().getName()).setOs(ua.getOs().getName());
		}
		return adminLoginLog;
	}

}
