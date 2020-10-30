package com.hccake.ballcat.commom.log.operation.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Hccake
 * @version 1.0
 * @date 2020/5/27 17:56 操作类型
 */
@Getter
@RequiredArgsConstructor
public enum OperationTypeEnum {

	/**
	 * 查看操作，主要用于敏感数据查询记录
	 */
	READ(3),

	/**
	 * 新建操作
	 */
	CREATE(4),

	/**
	 * 修改操作
	 */
	UPDATE(5),

	/**
	 * 删除操作
	 */
	DELETE(6);

	private final Integer value;

}
