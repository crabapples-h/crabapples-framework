package cn.crabapples.system.fileinfo.dao;//package org.example.application.system.dao;
//
//import org.example.application.common.BaseDAO;
//import org.example.application.system.dao.jpa.FileInfoRepository;
//import org.example.application.system.fileinfo.entity.FileInfo;
//import org.springframework.stereotype.Component;
//
//@Component
//public class FileInfoDAO extends BaseDAO {
//    private final FileInfoRepository repository;
//
//    public FileInfoDAO(FileInfoRepository repository) {
//        this.repository = repository;
//    }
//
//    public FileInfo save(FileInfo fileInfo) {
//        return repository.saveAndFlush(fileInfo);
//    }
//
//    public FileInfo findById(String id) {
//        return repository.findById(id).orElse(new FileInfo());
//    }
//}
