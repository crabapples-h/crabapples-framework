package cn.crabapples.system.sysUser.service;


import cn.crabapples.system.sysUser.entity.SysUser;
import cn.crabapples.system.sysUser.form.ResetPasswordForm;
import cn.crabapples.system.sysUser.form.SysUserForm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface SysUserService extends IService<SysUser> {

    SysUser findByUsername(String username);

    SysUser addUser(SysUserForm form);

    SysUser editUser(SysUserForm form);

    List<SysUser> findByName(String name);

    boolean changeStatus(String id, Integer status);


    List<SysUser> findAll();


//    List<SysUserDTO> getUserListDTO(Integer role);

    SysUser getUserInfo();

    boolean resetPassword(ResetPasswordForm form);

    boolean updatePassword(ResetPasswordForm form);

    SysUser updateUserInfo(SysUserForm form);
}
