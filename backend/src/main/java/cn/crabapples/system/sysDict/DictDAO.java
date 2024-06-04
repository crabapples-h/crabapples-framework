package cn.crabapples.system.sysDict;

import cn.crabapples.system.sysDict.entity.SysDict;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class DictDAO extends ServiceImpl<DictMapper, SysDict> {
    public IPage<SysDict> findAll(Integer pageIndex, Integer pageSize, DictForm form) {
        return baseMapper.selectPage(Page.of(pageIndex, pageSize), new QueryWrapper<>(form.toEntity()));
    }
}
