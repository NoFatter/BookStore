package cn.edu.xmut.soft.entity;

import cn.edu.xmut.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色表 chrimer
 * </p>
 *
 * @author chrimer
 * @since 2021-11-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 所属user
     */
    private String userId;

    /**
     * 所属role
     */
    private String roleId;


}
