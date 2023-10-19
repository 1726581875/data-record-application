package com.xmz.application.controller.sys;

import com.xmz.application.model.common.RespResult;
import com.xmz.application.model.common.dto.TenantSourceDTO;
import com.xmz.application.service.sys.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaomingzhang
 * @date 2022/10/9
 */
@RestController
@RequestMapping("/command")
public class CommandController {

    @Autowired
    private CommandService commandService;

    @PostMapping("/ping")
    private RespResult<Boolean> ping(@RequestBody TenantSourceDTO dto) {
        return commandService.ping(dto);
    }


}
