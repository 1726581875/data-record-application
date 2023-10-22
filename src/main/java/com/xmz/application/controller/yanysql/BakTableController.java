package com.xmz.application.controller.yanysql;

import com.xmz.application.model.common.vo.DataListVO;
import com.xmz.application.model.yanysql.BakTable;
import com.xmz.application.service.sys.DataService;
import com.xmz.application.service.yanysql.BakTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaomingzhang
 * @date 2022/10/22
 */
@RestController
@RequestMapping("/bakTable")
public class BakTableController {

    @Autowired
    private BakTableService bakTableService;

    @GetMapping("/getAllBakTables")
    public List<BakTable> getAllBakTables(@RequestParam(name = "tableName", defaultValue = "") String tableName) {
        return bakTableService.getAllBakTables(tableName);
    }

    @GetMapping("/getDataList")
    public DataListVO getList(String tableName, Integer current, Integer size) {
        return bakTableService.getDataList(tableName, current, size);
    }

}
