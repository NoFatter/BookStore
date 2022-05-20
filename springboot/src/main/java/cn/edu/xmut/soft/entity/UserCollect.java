package cn.edu.xmut.soft.entity;

import cn.edu.xmut.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Project name:springboot
 * Author: NoFat
 * Create time:2021/12/15 19:14
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserCollect extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String bookId;
}
