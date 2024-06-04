package cn.crabapples.system.fileinfo.dao.jpa;

import cn.crabapples.system.fileinfo.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, String>, JpaSpecificationExecutor<FileInfo> {
}
