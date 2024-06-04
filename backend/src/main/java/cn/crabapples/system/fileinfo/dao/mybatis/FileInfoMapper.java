package cn.crabapples.system.fileinfo.dao.mybatis;

import cn.crabapples.system.fileinfo.entity.FileInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FileInfoMapper extends BaseMapper<FileInfo> {
}
