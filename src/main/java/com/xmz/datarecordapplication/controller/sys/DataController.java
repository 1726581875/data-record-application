package com.xmz.datarecordapplication.controller.sys;

import com.xmz.datarecordapplication.model.vo.DataListVO;
import com.xmz.datarecordapplication.service.sys.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaomingzhang
 * @date 2022/9/18
 */
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;


    @GetMapping("/getList")
    public DataListVO getList(String tableName, Integer current, Integer size) {
        return dataService.getDataList(tableName, current, size);
    }





}
