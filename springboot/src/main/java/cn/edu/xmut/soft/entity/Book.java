package cn.edu.xmut.soft.entity;
import cn.edu.xmut.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * <p>
 * 书籍表 chrimer
 * </p>
 *
 * @author chrimer
 * @since 2021-11-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Book extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 书名
     */
    private String title;

    /**
     * 价格
     */
    private Double price;

    /**
     * 是否可用 0- 不可用 1-可用
     */
    private Integer useful;

    /**
     * 创建时间 YYYY-mm-DD HH:MM:SS
     */
    private String createTime;

}
