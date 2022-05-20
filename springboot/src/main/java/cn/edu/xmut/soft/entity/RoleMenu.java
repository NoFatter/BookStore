package cn.edu.xmut.soft.entity;

import cn.edu.xmut.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色菜单表 chrimer
 * </p>
 *
 * @author chrimer
 * @since 2021-11-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RoleMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 所属menu
     */
    private String menuId;

    /**
     * 所属role
     */
    private String roleId;


}
