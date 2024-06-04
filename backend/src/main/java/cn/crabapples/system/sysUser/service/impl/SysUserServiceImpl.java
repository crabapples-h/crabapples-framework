package cn.crabapples.system.sysUser.service.impl;

import cn.crabapples.common.ApplicationException;
import cn.crabapples.common.jwt.JwtTokenUtils;
import cn.crabapples.common.utils.AssertUtils;
import cn.crabapples.system.sysUser.dao.mybatis.SysUserMapper;
import cn.crabapples.system.sysUser.entity.SysUser;
import cn.crabapples.system.sysUser.form.ResetPasswordForm;
import cn.crabapples.system.sysUser.form.SysUserForm;
import cn.crabapples.system.sysUser.service.SysUserService;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 用户相关服务实现类
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private final JwtTokenUtils jwtTokenUtils;

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    public SysUserServiceImpl(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }


    /**
     * 根据 [用户名] 查询用户
     *
     * @param username 用户名
     * @return 查询到的用户
     */
    @Override
    public SysUser findByUsername(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    @Transactional
    public SysUser addUser(SysUserForm form) {
        SysUser entity = SysUser.create();
        BeanUtils.copyProperties(form, entity);
        saveOrUpdate(entity);
        return entity;
    }

    @Override
    @Transactional
    public SysUser editUser(SysUserForm form) {
        SysUser entity = SysUser.create().setId(form.getId()).selectById();
        AssertUtils.notNull(entity, "用户不存在");
        BeanUtils.copyProperties(form, entity, "password", "createTime");
        saveOrUpdate(entity);
        return entity;
    }

    @Override
    public List<SysUser> findByName(String name) {
        return SysUser.create().setName(name).selectList(null);
    }

    @Override
    @Transactional
    public boolean changeStatus(String id, Integer status) {
        SysUser entity = SysUser.create().setId(id).selectById();
        entity.setStatus(status);
        return saveOrUpdate(entity);
    }

    @Override
    public List<SysUser> findAll() {
        logger.info("开始获取所有用户");
        return SysUser.create().selectAll();
    }


//    @Override
//    public List<SysUserDTO> getUserListDTO(Integer role) {
////        String userId = getUserInfo(request, jwtConfigure, this, isDebug).getId();
//        List<SysUser> userList = findAll();
//        List<SysUserDTO> userDTOS = new ArrayList<>();
//        userList.stream().filter(e -> e.getRole() == role).forEach(e -> {
//            SysUserDTO sysUserDTO = new SysUserDTO();
//            BeanUtils.copyProperties(e, sysUserDTO);
//            userDTOS.add(sysUserDTO);
//        });
//        return userDTOS;
//    }

    @Override
    public SysUser getUserInfo() {
        logger.info("调用[{}]接口", "获取当前用户信息");
        String userId = jwtTokenUtils.getUserId();
        logger.info("调用[{}]接口,用户ID:[{}]", "获取当前用户信息", userId);
        return SysUser.create().selectOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getId, userId));
    }

    @Override
    @Transactional
    public boolean resetPassword(ResetPasswordForm form) {
        String newPassword = form.getNewPassword();
        String againPassword = form.getAgainPassword();
        if (!newPassword.equals(againPassword)) {
            throw new ApplicationException("两次密码不一致");
        }
        SysUser user = getById(form.getId());
        AssertUtils.notNull(user, "用户不存在");
        newPassword = MD5.create().digestHex(newPassword.getBytes(StandardCharsets.UTF_8));
        user.setPassword(newPassword);
        return saveOrUpdate(user);
    }

    @Override
    @Transactional
    public boolean updatePassword(ResetPasswordForm form) {
        String newPassword = form.getNewPassword();
        String againPassword = form.getAgainPassword();
        if (!newPassword.equals(againPassword)) {
            throw new ApplicationException("两次密码不一致");
        }
        SysUser user = getById(form.getId());
        AssertUtils.notNull(user, "用户不存在");
        String oldPassword = MD5.create().digestHex(form.getOldPassword().getBytes(StandardCharsets.UTF_8));
        if (user.getPassword().equals(oldPassword)) {
            newPassword = MD5.create().digestHex(newPassword.getBytes(StandardCharsets.UTF_8));
            user.setPassword(newPassword);
            return saveOrUpdate(user);
        }
        throw new ApplicationException("原密码错误");
    }

    @Override
    @Transactional
    public SysUser updateUserInfo(SysUserForm form) {
        SysUser userInfo = getUserInfo();
        SysUser entity = SysUser.create().setId(userInfo.getId()).selectById();
        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<SysUser>()
                .eq(SysUser::getId, userInfo.getId())
                .set(StringUtils.isNotBlank(form.getName()), SysUser::getName, form.getName())
                .set(StringUtils.isNotBlank(form.getAvatar()), SysUser::getAvatar, form.getAvatar());
        AssertUtils.notNull(entity, "用户不存在");
        update(updateWrapper);
        return entity;
    }
}
