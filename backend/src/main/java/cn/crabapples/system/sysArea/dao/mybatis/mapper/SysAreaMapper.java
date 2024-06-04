package cn.crabapples.system.sysArea.dao.mybatis.mapper;

import cn.crabapples.system.sysArea.entity.SysArea;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysAreaMapper extends BaseMapper<SysArea> {
    List<SysArea> getAreaTree();
}
