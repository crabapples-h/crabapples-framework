package cn.crabapples.common;

import cn.crabapples.common.jwt.JwtTokenUtils;
import cn.crabapples.system.sysUser.entity.SysUser;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@Component
public class MybatisPlusAutoFillHandler implements MetaObjectHandler {
    private final JwtTokenUtils jwtTokenUtils;

    public MybatisPlusAutoFillHandler(JwtTokenUtils jwtTokenUtils, HttpServletRequest request) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始填充 insert 数据 ....");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        String userName = getUserInfo();
        this.strictInsertFill(metaObject, "createBy", String.class, userName); // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "updateBy", String.class, userName); // 起始版本 3.3.0(推荐使用)
        log.info("填充 insert 数据完成");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始填充 update 数据 ....");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
        String userName = getUserInfo();
        this.strictInsertFill(metaObject, "updateBy", String.class, userName); // 起始版本 3.3.0(推荐使用)
        log.info("填充 update 数据完成");
    }

    private String getUserInfo() {
        try {
            String userId = jwtTokenUtils.getUserId();
            log.info("填充用户id:[{}]", userId);
            return new SysUser().selectById(userId).getUsername();
        } catch (Exception e) {
            String defaultStr = "system";
            log.warn("获取请求头中token失败。可能是来自系统内部调用,自动填充为[{}]", defaultStr);
            return defaultStr;
        }
    }
}
