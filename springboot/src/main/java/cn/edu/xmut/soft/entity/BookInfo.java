package cn.edu.xmut.soft.entity;

import cn.edu.xmut.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Project name:springboot
 * Author: NoFat
 * Create time:2021/12/15 2:49
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BookInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private String bookId;
    private String author;
    private String updateTime;
    private String createUser;
    private String info;
    private String img;
}
