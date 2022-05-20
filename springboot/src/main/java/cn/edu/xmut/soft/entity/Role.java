package cn.edu.xmut.soft.entity;

import cn.edu.xmut.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表 chrimer
 * </p>
 *
 * @author chrimer
 * @since 2021-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名
     */
    private String name;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 是否可用 0- 不可用 1-可用
     */
    private Integer useful;


}
