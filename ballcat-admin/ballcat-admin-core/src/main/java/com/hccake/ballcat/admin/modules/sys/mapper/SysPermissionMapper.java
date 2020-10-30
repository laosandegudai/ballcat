package com.hccake.ballcat.admin.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hccake.ballcat.admin.modules.sys.model.entity.SysPermission;
import com.hccake.ballcat.admin.modules.sys.model.vo.PermissionVO;

import java.util.List;

/**
 * 菜单权限表 Mapper 接口
 *
 * @author
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

	/**
	 * 通过角色ID查询权限
	 * @param roleCode 角色ID
	 * @return
	 */
	List<PermissionVO> listPermissionVOsByRoleCode(String roleCode);

}
