package cn.crabapples.system.sysUser.service.impl;

import cn.crabapples.common.ApplicationException;
import cn.crabapples.common.jwt.JwtTokenUtils;
import cn.crabapples.common.utils.AssertUtils;
import cn.crabapples.system.sysUser.dao.SysUserDAO;
import cn.crabapples.system.sysUser.dto.SysUserDTO;
import cn.crabapples.system.sysUser.entity.SysUser;
import cn.crabapples.system.sysUser.form.SysUserForm;
import cn.crabapples.system.sysUser.service.SystemUserService;
import cn.crabapples.system.sysUserRole.service.SystemUserRoleService;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * TODO 用户相关服务实现类[用户]
 *
 * @author Mr.He
 * 2020/1/27 2:10
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Service
@Slf4j
public class SystemUserServiceImpl implements SystemUserService {
    @Value("${isCrypt}")
    private boolean isCrypt;
    private final SysUserDAO userDAO;
    @Value("${isDebug}")
    private boolean isDebug;
    private final JwtTokenUtils jwtTokenUtils;
    private final HttpServletRequest request;
    private final SystemUserRoleService userRoleService;


    public SystemUserServiceImpl(SysUserDAO userDAO, JwtTokenUtils jwtTokenUtils,
                                 HttpServletRequest request,
                                 SystemUserRoleService userRoleService) {
        this.userDAO = userDAO;
        this.jwtTokenUtils = jwtTokenUtils;
        this.request = request;
        this.userRoleService = userRoleService;
    }

    @Override
    public SysUser findById(String id) {
        return userDAO.findById(id);
    }

    @Override
    public List<SysUser> findByIds(List<String> ids) {
        return userDAO.findByIds(ids);
    }

    @Override
    public List<SysUserDTO> findByName(String name) {
        SysUserForm form = new SysUserForm();
        form.setName(name);
        return userDAO.findAll(form);
    }

    @Override
    public SysUser findByUsername(String username) {
        SysUserForm form = new SysUserForm();
        form.setUsername(username);
        return userDAO.findOne(form);
    }

    @Override
    public IPage<SysUserDTO> findAll(Integer pageIndex, Integer pageSize, SysUserForm form) {
        return userDAO.findAll(pageIndex, pageSize, form);
    }
    @Override
    public IPage<SysUser> findAllV2(Integer pageIndex, Integer pageSize, SysUserForm form) {
        return userDAO.findAllV2(pageIndex, pageSize, form);
    }

    @Override
    public List<SysUserDTO> findAll(SysUserForm form) {
        return userDAO.findAll(form);
    }

    @Override
    public boolean saveUser(SysUserForm form) {
        SysUser entity = form.toEntity();
        String newPassword = form.getNewPassword();
        String againPassword = form.getAgainPassword();
        if (!StringUtils.isEmpty(newPassword)) {
            if (!newPassword.equals(againPassword)) {
                throw new ApplicationException("两次密码不一致");
            }
            entity.setPassword(encryptPassword(newPassword));
        }
        boolean status = entity.insertOrUpdate();
        userRoleService.saveUserRoles(entity.getId(), entity.getRoleList());
        return status;
    }

    @Override
    public SysUserDTO getById(String id) {
        SysUser user = userDAO.getById(id);
        SysUserDTO dto = new SysUserDTO();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

    private String encryptPassword(String password) {
        return isCrypt ? MD5.create().digestHex(password.getBytes(StandardCharsets.UTF_8)) : password;
    }

    /**
     * 删除用户
     */
    @Override
    public boolean delUser(String id) {
        return userDAO.delUser(id);
    }

    /**
     * 锁定用户
     */
    @Override
    public boolean lockUser(String id) {
        return userDAO.lockUser(id);
    }

    /**
     * 解锁用户
     */
    @Override
    public boolean unlockUser(String id) {
        return userDAO.unlockUser(id);
    }

    /**
     * 修改密码
     */
    @Override
    public boolean updatePassword(SysUserForm.UpdatePassword form) {
        SysUser user = checkPassword(form);
        String password = form.getOldPassword();
        String newPassword = form.getNewPassword();
        password = MD5.create().digestHex(password.getBytes(StandardCharsets.UTF_8));
        if (user.getPassword().equals(password)) {
            newPassword = MD5.create().digestHex(newPassword.getBytes(StandardCharsets.UTF_8));
            user.setPassword(newPassword);
            return userDAO.saveOrUpdate(user);
        }
        throw new ApplicationException("密码错误");
    }

    /**
     * 重置密码
     */
    @Override
    public boolean resetPassword(SysUserForm.ResetPassword form) {
        SysUser user = checkPassword(form);
        String newPassword = form.getNewPassword();
        AssertUtils.notNull(user, "用户不存在");
        newPassword = MD5.create().digestHex(newPassword.getBytes(StandardCharsets.UTF_8));
        user.setPassword(newPassword);
        return userDAO.saveOrUpdate(user);
    }

    /**
     * 获取当前登录用户的信息
     */
    @Override
    public SysUser getUserInfo() {
        String userId = "001";
        if (!isDebug) {
            userId = jwtTokenUtils.getUserId();
        }
//        Object user = cacheUtils.get(userId);
        return userDAO.findById(userId);
    }
    //    /**
//     * 添加或编辑用户时将填充用户拥有的角色信息
//     */
//    private void setRolesList(List<String> ids, SysUser entity) {
//        List<SysRoles> roles = rolesDAO.findByIds(ids);
//        entity.setRolesList(roles);
//    }

    /**
     * 修改或重置密码时检测
     * 1、检测两次输入密码是否相同
     * 2、检测用户是否存在
     */
    private SysUser checkPassword(SysUserForm.ResetPassword form) {
        String newPassword = form.getNewPassword();
        String againPassword = form.getAgainPassword();
        if (!newPassword.equals(againPassword)) {
            throw new ApplicationException("两次密码不一致");
        }
        SysUser user = userDAO.findById(form.getId());
        AssertUtils.notNull(user, "用户不存在");
        return user;
    }

}
