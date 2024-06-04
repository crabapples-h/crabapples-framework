package cn.crabapples.system.fileinfo.controller;

import cn.crabapples.common.ResponseDTO;
import cn.crabapples.common.base.BaseController;
import cn.crabapples.common.jwt.JwtIgnore;
import cn.crabapples.system.fileinfo.entity.FileInfo;
import cn.crabapples.system.fileinfo.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件接口
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class FileInfoController extends BaseController {

    private final FileInfoService fileInfoService;

    public FileInfoController(FileInfoService fileInfoService) {
        this.fileInfoService = fileInfoService;
    }

    @PostMapping("/uploadFile")
    @JwtIgnore
    public ResponseDTO<FileInfo> uploadFile(HttpServletRequest request) {
        log.info("收到请求->上传文件");
        FileInfo entity = fileInfoService.uploadFile(request);
        log.info("返回结果->上传文件结束:[{}]", entity);
        return new ResponseDTO<>(entity);
    }
}
