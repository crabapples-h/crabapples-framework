package cn.crabapples.system.sysArea.service.impl;

import cn.crabapples.system.sysArea.dao.mybatis.mapper.SysAreaMapper;
import cn.crabapples.system.sysArea.entity.SysArea;
import cn.crabapples.system.sysArea.service.SysAreaService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 系统省市区服务实现类
 */
@Service
//@CacheConfig(cacheNames = "user:")
public class SysAreaServiceImpl implements SysAreaService {

    private static final Logger logger = LoggerFactory.getLogger(SysAreaServiceImpl.class);


    private final SysAreaMapper areaMapper;

    public SysAreaServiceImpl(SysAreaMapper areaMapper) {
        this.areaMapper = areaMapper;
    }

    @Override
    public List<SysArea> getAreaTree() {
        return areaMapper.getAreaTree();
    }

    @Override
    public List<SysArea> getProvinceList() {
        return SysArea.create().selectList(Wrappers.<SysArea>lambdaQuery().isNull(SysArea::getParentCode));
    }

    @Override
    public List<SysArea> getCityList(String provinceCode) {
        return SysArea.create().selectList(Wrappers.<SysArea>lambdaQuery().eq(SysArea::getParentCode, provinceCode));
    }

    @Override
    public List<SysArea> getCountyList(String cityCode) {
        return SysArea.create().selectList(Wrappers.<SysArea>lambdaQuery().eq(SysArea::getParentCode, cityCode));
    }
}
