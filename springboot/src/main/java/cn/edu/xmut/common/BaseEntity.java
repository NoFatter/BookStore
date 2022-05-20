package cn.edu.xmut.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础实体类
 * @author bill
 *
 */
@Data
public class BaseEntity implements Serializable {

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @TableField(exist = false)
    private Object etc;



    @SuppressWarnings("unchecked")
    public void put(String key, Object value) {
        if(etc == null) {
            etc = new HashMap<String, Object>();
        }
        ((Map<String, Object>) etc).put(key, value);
    }
}
