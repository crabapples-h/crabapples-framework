package cn.crabapples.system.sysUser.controller;

import cn.crabapples.common.Groups;
import cn.crabapples.common.ResponseDTO;
import cn.crabapples.common.base.BaseController;
import cn.crabapples.system.sysUser.entity.SysUser;
import cn.crabapples.system.sysUser.form.ResetPasswordForm;
import cn.crabapples.system.sysUser.form.SysUserForm;
import cn.crabapples.system.sysUser.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


/**
 * 用户管理接口类
 */
@RestController
@RequestMapping(value = "/api/sys/user")
public class SysUserController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(SysUserController.class);
    private final SysUserService service;

    public SysUserController(SysUserService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseDTO<List<SysUser>> getUserList() {
        logger.info("收到请求->获取用户列表");
        List<SysUser> list = service.findAll();
        logger.info("返回结果->获取用户列表结束:[{}]", list);
        return new ResponseDTO<>(list);
    }

    @GetMapping("/page")
    public ResponseDTO<Page<SysUser>> page(SysUser entity,
                                           @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        logger.info("收到请求->获取用户分页");
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getIsAdmin, 0)
//                .eq(Objects.nonNull(entity.getRole()), SysUser::getRole, entity.getRole())
                .like(StringUtils.isNotBlank(entity.getName()), SysUser::getName, entity.getName())
                .like(StringUtils.isNotBlank(entity.getUsername()), SysUser::getUsername, entity.getUsername())
                .orderByDesc(SysUser::getCreateTime);
        Page<SysUser> page = new Page<>(pageIndex, pageSize);
        Page<SysUser> data = service.page(page, wrapper);
        logger.info("返回结果->获取用户分页完成:[{}]", data);
        return new ResponseDTO<>(data);
    }

    @PostMapping("/addUser")
    public ResponseDTO<SysUser> addUser(@RequestBody SysUserForm form) {
        logger.info("收到请求->添加用户:[{}]", form);
        SysUser user = service.addUser(form);
        logger.info("返回结果->用户添加完成:[{}]", user);
        return new ResponseDTO<>(user);
    }

    @PostMapping("/updateUserInfo")
    public ResponseDTO<SysUser> updateUserInfo(@RequestBody SysUserForm form) {
        logger.info("收到请求->编辑个人信息:[{}]", form);
        SysUser user = service.updateUserInfo(form);
        logger.info("返回结果->编辑个人信息完成:[{}]", user);
        return new ResponseDTO<>(user);
    }

    @PostMapping("/editUser")
    public ResponseDTO<SysUser> editUser(@RequestBody SysUserForm form) {
        logger.info("收到请求->修改用户:[{}]", form);
        super.validate(form, Groups.IsEdit.class);
        SysUser user = service.editUser(form);
        logger.info("返回结果->用户修改完成:[{}]", user);
        return new ResponseDTO<>(user);

    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO<Boolean> remove(@PathVariable String id) {
        logger.info("收到请求->删除用户:[{}]", id);
        boolean status = SysUser.create().deleteById(id);
        logger.info("返回结果->用户删除完成:[{}]", status);
        return new ResponseDTO<>(status);

    }

    @PostMapping("/changeStatus/{id}/{status}")
    public ResponseDTO<Boolean> changeStatus(@PathVariable String id, @PathVariable Integer status) {
        logger.info("收到请求->修改用户状态,id:[{}],status:[{}]", id, status);
        boolean b = service.changeStatus(id, status);
        logger.info("返回结果->修改用户状态完成");
        return new ResponseDTO<>(b);
    }

    @PostMapping("/resetPassword")
    public ResponseDTO<Boolean> resetPassword(@RequestBody ResetPasswordForm form) {
        super.validate(form, Groups.IsNotNull.class);
        logger.info("收到请求->重置密码,id:[{}]", form.getId());
        boolean status = service.resetPassword(form);
        logger.info("返回结果->重置密码完成");
        return new ResponseDTO<>(status);
    }

    @PostMapping("/updatePassword")
    public ResponseDTO<Boolean> updatePassword(@RequestBody ResetPasswordForm form) {
        super.validate(form, Groups.IsNotNull.class);
        logger.info("收到请求->修改密码,id:[{}]", form.getId());
        boolean status = service.updatePassword(form);
        logger.info("返回结果->修改密码完成:[{}]", status);
        return new ResponseDTO<>(status);
    }

    @GetMapping("/info")
    public ResponseDTO<SysUser> getUserInfo() {
        logger.info("收到请求->获取当前用户信息");
        SysUser sysUser = service.getUserInfo();
        logger.info("返回结果->获取当前用户信息结束:[{}]", sysUser);
        return new ResponseDTO<>(sysUser);
    }


    @GetMapping("/mine")
    public ResponseDTO<Page<SysUser>> mine() {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        SysUser userinfo = service.getUserInfo();
        wrapper.eq("id", userinfo.getId());
        Page<SysUser> page = new Page<>();
        Page<SysUser> data = service.page(page, wrapper);
        logger.info("返回结果->获取用户分页完成:[{}]", data);
        return new ResponseDTO<>(data);
    }

//    @GetMapping("/getUserListDTO/{role}")
//    public ResponseDTO getUserListDTO(@PathVariable Integer role) {
//        logger.info("收到请求->获取用户列表DTO");
//        List<SysUserDTO> list = userService.getUserListDTO(role);
//        logger.info("返回结果->获取用户列表DTO结束:[{}]", list);
//        return ResponseDTO.returnSuccess("操作成功", list);
//    }


}
