package cn.crabapples.system.sysArea.controller;

import cn.crabapples.common.ResponseDTO;
import cn.crabapples.common.base.BaseController;
import cn.crabapples.common.jwt.JwtIgnore;
import cn.crabapples.system.sysArea.entity.SysArea;
import cn.crabapples.system.sysArea.service.SysAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统相关接口
 */
@RestController
@Slf4j
@RequestMapping("/api/area")
public class SysAreaController extends BaseController {

    private final SysAreaService service;

    public SysAreaController(SysAreaService service) {
        this.service = service;
    }


    /**
     * 获取省份列表
     */
    @JwtIgnore
    @GetMapping("/tree")
    public ResponseDTO< List<SysArea>> getAreaTree() {
        log.info("收到请求->获取区域数据");
        List<SysArea> list = service.getAreaTree();
        log.debug("获取区域数据结束:[{}]", list);
        return new ResponseDTO<>(list);
    }

    /**
     * 获取省份列表
     */
    @JwtIgnore
    @GetMapping("/province/list")
    public ResponseDTO< List<SysArea>> getProvinceList() {
        log.info("收到请求->获取全国省份数据");
        List<SysArea> list = service.getProvinceList();
        log.debug("获取全国省份数据结束:[{}]", list);
        return new ResponseDTO<>(list);
    }

    /**
     * 获取城市列表
     */
    @JwtIgnore
    @GetMapping("/city/list/{code}")
    public ResponseDTO< List<SysArea>> getCityList(@PathVariable String code) {
        log.info("收到请求->获取[{}]城市数据", code);
        List<SysArea> list = service.getCityList(code);
        log.debug("获取[{}]城市数据结束:[{}]", code, list);
        return new ResponseDTO<>(list);
    }

    /**
     * 获取区县列表
     */
    @JwtIgnore
    @GetMapping("/county/list/{code}")
    public ResponseDTO < List<SysArea>>getCountyList(@PathVariable String code) {
        log.info("收到请求->获取[{}]区县数据", code);
        List<SysArea> list = service.getCountyList(code);
        log.debug("获取[{}]区县数据结束:[{}]", code, list);
        return new ResponseDTO<>(list);
    }
}
