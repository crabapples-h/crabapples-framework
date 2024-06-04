package cn.crabapples.system.sysDict;

import cn.crabapples.system.sysDict.entity.SysDictItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictItemMapper extends BaseMapper<SysDictItem> {
}
