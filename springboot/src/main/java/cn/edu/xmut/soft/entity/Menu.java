package cn.edu.xmut.soft.entity;

import cn.edu.xmut.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表 chrimer
 * </p>
 *
 * @author chrimer
 * @since 2021-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单链接，父菜单则为空
     */
    private String path;

    /**
     * 父菜单id，没有父菜单则为-1
     */
    private String parentId;

    /**
     * 菜单序号
     */
    private Integer sort;

    /**
     * 菜单描述
     */
    private String description;

    /**
     * 层级，1-顶级菜单  2-子菜单  3-CRUD操作
     */
    private Integer level;

    /**
     * 关联的按钮类型，就是dictype中button对应的dic的id
     */
    private String buttonType;

    /**
     * 页面组件
     */
    private String component;


}
