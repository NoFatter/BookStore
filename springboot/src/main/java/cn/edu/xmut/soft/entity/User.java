package cn.edu.xmut.soft.entity;

import cn.edu.xmut.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表 chrimer
 * </p>
 *
 * @author chrimer
 * @since 2021-10-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 是否可用 0- 不可用 1-可用
     */
    private Integer useful;


}
