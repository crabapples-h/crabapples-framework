package cn.crabapples.system.base.controller;

import cn.crabapples.common.Groups;
import cn.crabapples.common.ResponseDTO;
import cn.crabapples.common.base.BaseController;
import cn.crabapples.common.jwt.JwtIgnore;
import cn.crabapples.system.base.service.SystemService;
import cn.crabapples.system.sysUser.form.SysUserForm;
import cn.crabapples.system.sysUser.service.SystemUserService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.tags.Tag;
import cn.crabapples.system.sysUser.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO 系统相关接口
 *
 * @author Mr.He
 * 2020/1/28 23:17
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */

@RestController
@RequestMapping("/api/system/")
@Slf4j
//@Tag(name = "系统功能", description = "系统功能 ")
public class SystemController extends BaseController {

    private final SystemUserService userService;
    private final SystemService sysService;

    public SystemController(SystemUserService userService, SystemService sysService) {
        this.userService = userService;
        this.sysService = sysService;
    }

    /**
     * 获取系统时间
     */
    @JwtIgnore
    @GetMapping("/timestamp")
    public ResponseDTO<Long> getTimestamp() {
        log.info("收到请求->获取系统时间");
        long currentTimeMillis = System.currentTimeMillis();
        log.debug("获取获取系统时间结束:[{}]", currentTimeMillis);
        return new ResponseDTO<>(currentTimeMillis);
    }



    /**
     * 发起登录请求
     *
     * @param form 用户名和密码
     * @return 登录成功返回token
     */
    @JwtIgnore
    @PostMapping(value = "/login")
//    @Operation(summary = "用户登陆", description = "用户登陆接口",
//            parameters = {@Parameter(required = true, name = "code", description = "token")})
    public ResponseDTO<String> login(@RequestBody SysUserForm form) {
        log.info("收到请求->用户登陆验证:[{}]", form);
        super.validate(form, Groups.IsLogin.class);
        String token = sysService.login(form);
        log.info("返回结果->登录成功->token:[{}]", token);
        return new ResponseDTO<>("登录成功", token);
    }

    /**
     * 注销登录
     */
    @JwtIgnore
    @PostMapping("/logout")
//    @ApiOperation(value = "注销登录", notes = "注销登录接口")
    public ResponseDTO<String> logout() {
        return new ResponseDTO<>("注销成功");
    }

    @GetMapping("/permissions")
    public ResponseDTO<List<String>> getUserPermissions() {
        log.info("收到请求->获取用户权限列表");
        List<String> list = sysService.getUserPermissions();
        log.debug("返回结果->获取用户权限列表成功:[{}]", list);
        return new ResponseDTO<>(list);
    }

    @GetMapping("/userInfo")
//    @ApiOperation(value = "获取当前用户信息", notes = "获取当前用户信息接口")
    public ResponseDTO<SysUser> getUserInfo() {
        log.info("收到请求->获取当前用户信息");
        SysUser entity = userService.getUserInfo();
        log.info("返回结果->获取当前用户信息结束:[{}]", entity);
        return new ResponseDTO<>(entity);
    }

    @GetMapping("/checkUsername/{username}")
//    @ApiOperation(value = "检测用户名是否被使用", notes = "检测用户名是否被使用接口")
    public ResponseDTO<Boolean> checkUsername(@PathVariable String username) {
        log.info("收到请求->检测用户名是否被使用:[{}]", username);
        boolean exist = sysService.checkUsername(username);
        log.info("返回结果->检测用户名是否被使用:[{}]", exist);
        return new ResponseDTO<>("用户名未被使用", exist);
    }
}
