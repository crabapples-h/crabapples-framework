package cn.crabapples.system.sysArea.service;


import cn.crabapples.system.sysArea.entity.SysArea;

import java.util.List;


/**
 * 系统省市区相关服务
 */
public interface SysAreaService {
    List<SysArea> getAreaTree();

    List<SysArea> getProvinceList();

    List<SysArea> getCityList(String provinceCode);

    List<SysArea> getCountyList(String cityCode);
}
