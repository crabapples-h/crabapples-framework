package cn.crabapples.system.fileinfo.entity;

import cn.crabapples.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;


@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Setter
@Getter
@Entity
@TableName(keepGlobalPrefix = true)
public class FileInfo extends BaseEntity<FileInfo> {
    @Id
    @TableId
    private String id;
    //原文件名
    private String oldName;
    //文件虚拟访问路径
    private String virtualPath;

    //文件真实保存访问路径
    private String uploadPath;

    // 文件类型
    private String contentType;

    //文件大小
    private Long fileSize;

    @TableLogic
    private byte delFlag;
}
