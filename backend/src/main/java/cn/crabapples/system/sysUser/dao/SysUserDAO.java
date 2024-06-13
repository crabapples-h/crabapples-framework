package cn.crabapples.system.sysUser.dao;

import cn.crabapples.common.DIC;
import cn.crabapples.system.sysUser.dao.mybatis.mapper.SysUserMapper;
import cn.crabapples.system.sysUser.dto.SysUserDTO;
import cn.crabapples.system.sysUser.entity.SysUser;
import cn.crabapples.system.sysUser.form.SysUserForm;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SysUserDAO extends ServiceImpl<SysUserMapper, SysUser> {

    public IPage<SysUserDTO> findAll(Integer pageIndex, Integer pageSize, SysUserForm form) {
        Page<SysUser> page = Page.of(pageIndex, pageSize);
        IPage<SysUser> sysUserList = baseMapper.selectPage(page, new QueryWrapper<>(form.toEntity()));
        List<SysUserDTO> collect = sysUserList.getRecords().stream().map(e -> {
            final SysUserDTO dto = new SysUserDTO();
            BeanUtils.copyProperties(e, dto);
            return dto;
        }).collect(Collectors.toList());
        Page<SysUserDTO> dtoPage = new Page<>();
        BeanUtils.copyProperties(sysUserList, dtoPage);
        dtoPage.setRecords(collect);
        return dtoPage;
    }

    public IPage<SysUser> findAllV2(Integer pageIndex, Integer pageSize, SysUserForm form) {
        Page<SysUser> page = Page.of(pageIndex, pageSize);
        SysUser entity = form.toEntity();
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>()
                .like(StringUtils.isNotBlank(entity.getName()), SysUser::getName, entity.getName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    public List<SysUserDTO> findAll(SysUserForm form) {
        return baseMapper.selectList(new QueryWrapper<>(form.toEntity())).stream().map(e -> {
            final SysUserDTO dto = new SysUserDTO();
            BeanUtils.copyProperties(e, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    public SysUser findOne(SysUserForm form) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, form.getUsername());
        return getOne(wrapper);
    }

    public SysUser findById(String id) {
        return baseMapper.selectById(id);
    }

    public List<SysUser> findByIds(List<String> ids) {
        return baseMapper.selectBatchIds(ids);
    }

    public boolean delUser(String id) {
        return removeById(id);
    }

    public boolean lockUser(String id) {
        return new SysUser().selectById(id).setStatus(DIC.USER_LOCK).updateById();
    }

    public boolean unlockUser(String id) {
        return new SysUser().selectById(id).setStatus(DIC.USER_UNLOCK).updateById();
    }
}
