package com.hccake.ballcat.admin.modules.sys.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织架构
 *
 * @author hccake 2020-09-23 20:39:40
 */
@Data
@ApiModel(value = "组织架构DTO")
public class SysOrganizationDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@ApiModelProperty(value = "ID")
	private Integer id;

	/**
	 * 组织名称
	 */
	@ApiModelProperty(value = "组织名称")
	private String name;

	/**
	 * 父级ID
	 */
	@ApiModelProperty(value = "父级ID")
	private Integer parentId;

	/**
	 * 描述信息
	 */
	@ApiModelProperty(value = "描述信息")
	private String description;

	/**
	 * 排序字段，由小到大
	 */
	@ApiModelProperty(value = "排序字段，由小到大")
	private Integer sort;

}
