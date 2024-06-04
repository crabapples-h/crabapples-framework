package cn.crabapples.common.base;

import cn.crabapples.common.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * TODO 基础Controller，其他controller请继承此类
 *
 * @author Mr.He
 * 2019/9/21 18:28
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public abstract class BaseController {
    @Resource
    private Validator validator;


    /**
     * 属性校验的方法
     *
     * @param object 需要验证的对象
     */
    protected final void validate(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        for (ConstraintViolation constraintViolation : constraintViolations) {
            throw new ApplicationException(constraintViolation.getMessageTemplate());
        }
    }
}
