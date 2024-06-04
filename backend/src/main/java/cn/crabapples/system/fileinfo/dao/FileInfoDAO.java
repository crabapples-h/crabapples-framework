package cn.crabapples.system.fileinfo.dao;

import cn.crabapples.system.fileinfo.dao.jpa.FileInfoRepository;
import cn.crabapples.system.fileinfo.dao.mybatis.mapper.FileInfoMapper;
import cn.crabapples.system.fileinfo.entity.FileInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class FileInfoDAO extends ServiceImpl<FileInfoMapper, FileInfo> {
    private final FileInfoRepository repository;

    public FileInfoDAO(FileInfoRepository repository) {
        this.repository = repository;
    }

//    public FileInfo save(FileInfo fileInfo) {
//        return repository.saveAndFlush(fileInfo);
//    }
    @Transactional
    public boolean save(FileInfo fileInfo) {
        return saveOrUpdate(fileInfo);
    }

    public FileInfo findById(String id) {
        return repository.findById(id).orElse(new FileInfo());
    }
}
