package cn.crabapples.system.sysUser.dao.mybatis;

import cn.crabapples.system.sysUser.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
//    /**
//     * 根据 [用户名] 查询用户
//     *
//     * @param username 用户名
//     * @return 查询到的用户
//     */
//    Optional<SysUser> findByUsername(String username);
//
//    /**
//     * 根据姓名查询
//     *
//     * @param name 姓名
//     * @return 查询到的用户集合
//     */
//    List<SysUser> findByName(String name);
//
//    /**
//     * 根据姓名模糊查询
//     *
//     * @param name 姓名
//     * @return 查询到的用户集合
//     */
//    List<SysUser> findByNameLike(String name);

//    /**
//     * 删除用户
//     *
//     * @param id 用户ID
//     */
//    @Query("update SysUser set delFlag = 1 where id=:name")
//    @Modifying
//    void delUser(@Param("name") String id);
//
//    /**
//     * 禁用用户
//     *
//     * @param id 用户ID
//     */
//    @Query("update SysUser set status = 1 where id = :id")
//    @Modifying
//    void unActiveUser(@Param("id") String id);
//
//    /**
//     * 激活用户
//     *
//     * @param id 用户ID
//     */
//    @Query("update SysUser set status = 0 where id = :id")
//    @Modifying
//    void activeUser(@Param("id") String id);
//
//    /**
//     * 根据[用户名] [密码] [状态] [删除标记] 查询用户
//     *
//     * @param username 用户名
//     * @param password 密码
//     * @param status   状态
//     * @param delFlag  删除标记
//     * @return 查询到的用户
//     */
//    Optional<SysUser> findByUsernameAndPasswordAndStatusNotAndDelFlagNot(String username, String password, int status, int delFlag);
//
//    List<SysUser> findByIdNotAndStatusAndDelFlag(String id, int status, int delFlag);
//
//    List<SysUser> findByIdInAndStatusAndDelFlag(List<String> ids, int status, int delFlag);
//
//    List<SysUser> findByDelFlag(Sort sort, int delFlag);
//
//
//    @Query("update SysUser set status = :status where id = :id")
//    @Modifying
//    void changeStatus(String id, Integer status);
//
//    List<SysUser> findByRoleAndDelFlag(int role, int delFlag);
}
