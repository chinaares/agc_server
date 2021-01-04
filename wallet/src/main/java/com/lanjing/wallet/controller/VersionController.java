package com.lanjing.wallet.controller;

import com.lanjing.wallet.model.ResultDTO;
import com.lanjing.wallet.model.Version;
import com.lanjing.wallet.dao.VersionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("version")
public class VersionController {
    @Autowired
    private VersionMapper versionMapper;

    @RequestMapping("/findVersion")
    public ResultDTO findVersion(@RequestBody Map<String,String> map) {

        String type = map.get("type");
        String version = map.get("version");
        Version version1 = versionMapper.findByid(Integer.parseInt(type));
        if (version1.getVersioncode().equals(version)){
            try {
                return new ResultDTO(200, "success", version1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                return new ResultDTO(202, "success", version1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResultDTO(201, "error");
    }
}
