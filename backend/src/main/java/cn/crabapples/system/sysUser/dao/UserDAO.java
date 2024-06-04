package cn.crabapples.system.sysUser.dao;//package org.example.application.system.dao;
//
//import org.example.application.common.BaseDAO;
//import org.example.application.system.dao.jpa.UserRepository;
//import org.example.application.system.dao.mapper.UserMapper;
//import org.example.application.system.sysUser.entity.SysUser;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Component
//public class UserDAO extends BaseDAO {
//    private final UserMapper mapper;
//
//    public UserDAO(UserMapper mapper) {
//        this.mapper = mapper;
//    }
//
//    public SysUser findByUsername(String username) {
//        return mapper.findByUsername(username).orElse(null);
//    }
//
//    public SysUser save(SysUser user) {
//        return mapper.save(user);
//    }
//
//    public SysUser findById(String id) {
//        return mapper.findById(id).orElse(null);
//    }
//
//    public List<SysUser> findByIds(List<String> ids) {
//        return mapper.findByIdInAndStatusAndDelFlag(ids, 0, NOT_DEL);
//    }
//
//    @Transactional
//    public void delUser(String id) {
//        mapper.deleteById(id);
//    }
//
//    public List<SysUser> findByName(String name) {
//        return mapper.findByName(name);
//    }
//
//    public SysUser findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag) {
//        return mapper.findByUsernameAndPasswordAndStatusNotAndDelFlagNot(username, password, status, delFlag).orElse(null);
//    }
//
//    public List<SysUser> findAll() {
//        return mapper.findByDelFlag(ascByCreateTime, NOT_DEL);
//
//    }
//
//    public List<SysUser> getUserList(String userId, int status) {
//        return mapper.findByIdNotAndStatusAndDelFlag(userId, status, NOT_DEL);
//    }
//
//    @Transactional
//    public void changeStatus(String id, Integer status) {
//        mapper.changeStatus(id, status);
//    }
//
//    public List<SysUser> findByRoleAndDelFlag(int role) {
//        return mapper.findByRoleAndDelFlag(role, NOT_DEL);
//    }
//}
