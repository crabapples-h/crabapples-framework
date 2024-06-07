package cn.crabapples.common.base;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * TODO 实体类基础属性,使用字典翻译功能必须继承此类
 *
 * @author Mr.He
 * 2019/9/21 17:47
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Getter
@Setter
public abstract class BaseEntity<T extends Model<?>> extends Model<T> implements Serializable, Cloneable {
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public T toDTO(T dto) {
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
