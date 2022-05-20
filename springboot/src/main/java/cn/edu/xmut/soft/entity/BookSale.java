package cn.edu.xmut.soft.entity;

import cn.edu.xmut.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
/**
 * Project name:springboot
 * Author: NoFat
 * Create time:2021/12/15 0:13
 **/
public class BookSale extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Book
     */
    private String bookId;
    private double discount;

}
