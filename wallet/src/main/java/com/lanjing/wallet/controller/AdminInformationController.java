package com.lanjing.wallet.controller;

import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.Result;
import com.lanjing.wallet.dao.InformationMapper;
import com.lanjing.wallet.model.Information;
import com.lanjing.wallet.model.ParametersWithBLOBs;
import com.lanjing.wallet.model.ResultDTO;
import com.lanjing.wallet.service.ParametersService;
import com.lanjing.wallet.util.BASE64imgUtil;
import com.lanjing.wallet.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@RestController
@CrossOrigin(value = "*")
public class AdminInformationController {
    @Autowired
    private InformationMapper informationMapper;

    @Resource(name = "ParametersService")
    private ParametersService parametersService;

    /**
     * 查询
     *
     * @param param
     * @return
     */

    @RequestMapping("admin/findInformationByPage")
    public String findInformationByPage(@RequestBody Map<String, String> param) {
        try {
            int page = Integer.parseInt(param.get("page"));
            int pagesize = Integer.parseInt(param.get("pagesize"));
            int type = Integer.parseInt(param.get("type"));
            page = page <= 0 ? 0 : (page - 1) * pagesize;
            String title = param.get("title");
            List<Information> informations = informationMapper.findInformationByPage(page, pagesize, title, type);
            int count = informationMapper.findInformationByPageCount(title, type);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("informations", informations);
            map.put("count", count);
            return JSONObject.toJSONString(new ResultDTO(200, "success", map));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSONObject.toJSONString(new ResultDTO(201, "error"));
    }

    /**
     * 添加
     *
     * @param param
     * @return
     */
    @RequestMapping("admin/addInformation")
    public String addInformationByPage(@RequestBody Information param) {
        try {
            param.setCreatetime(new Date());
            param.setUpdatetime(new Date());
            int i = informationMapper.insertSelective(param);
            if (i > 0) {
                return JSONObject.toJSONString(new ResultDTO(200, "success", param));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSONObject.toJSONString(new ResultDTO(201, "error"));
    }


    /**
     * 修改
     *
     * @param param
     * @return
     */
    @RequestMapping("admin/uptInformationByKey")
    public String uptInformationByPage(@RequestBody Information param) {
        try {
            param.setUpdatetime(new Date());
            int i = informationMapper.updateByPrimaryKeySelective(param);
            if (i > 0) {
                return JSONObject.toJSONString(new ResultDTO(200, "success", param));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSONObject.toJSONString(new ResultDTO(201, "error"));
    }

    /**
     * 删除公告
     *
     * @param param
     * @return
     */
    @RequestMapping("admin/delInformation")
    public Object delInformation(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");

        Information information = informationMapper.selectByPrimaryKey(id);

        if (information == null) {
            return Result.error("id不存在");
        }

        int i = informationMapper.deleteByPrimaryKey(id);

        if (i < 1) {
            return Result.error("删除失败");
        }

        return Result.success();
    }

    /**
     * 查询公告详情
     *
     * @param param
     * @return
     */
    @RequestMapping("admin/queryInfomation")
    public String queryInfomation(@RequestBody Map<String, String> param) {
        String fid = param.get("fId");
        try {
            Information information = informationMapper.selectByPrimaryKey(Integer.parseInt(fid));
            return JSONObject.toJSONString(new ResultDTO(200, "success", information));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toJSONString(new ResultDTO(201, "error"));
    }


    /**
     * 删除
     *
     * @param param
     * @return
     */
    @RequestMapping("admin/deleteInformationByKey")
    public String deleteInformationByPage(@RequestBody Map<String, String> param) {
        try {
            String[] ids = param.get("ids").split(",");
            int i = informationMapper.deleteByIds(ids);
            if (i > 0) {
                return JSONObject.toJSONString(new ResultDTO(200, "success"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSONObject.toJSONString(new ResultDTO(201, "error"));
    }

	/*@RequestMapping("/admin/uploaduserpicture")
	public String uploadFile(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		List<MultipartFile> multipartFiles;
		try {
			String classname = request.getClass().getName();
			multipartFiles = UploadUtil.getFileSet(request, 1024 * 1024 * 2, null);
		} catch (RuntimeException e) {
			return Result.error(e.getMessage()).toString();
		}
		String path = parametersService.selectByKey("imgbath").getKeyvalue();
		if (multipartFiles.size() == 0) {
			// TODO 给出提示,不允许没选择文件点击上传
			json.put("msg","请选择图片");
			json.put("code",201);
			return json.toJSONString();
		}
		try {
			String filePath = UploadUtil.uploadFile(multipartFiles.get(0), path,parametersService.selectByKey("imgurl").getKeyvalue());
			System.out.println(filePath);
			json.put("msg","ok");
			json.put("picture",filePath);
			json.put("code",200);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg","error");
			json.put("code",300);
		}
		return json.toJSONString();
	}*/

    @RequestMapping("/admin/uploaduserpicture")
    public String uploadFile(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String img = map.get("files");
        File directory = new File("");// 参数为空
        String courseFile = parametersService.selectByKey("imgbath").getKeyvalue();
        String imgurl = BASE64imgUtil.GenerateImage(img, courseFile);
        json.put("msg", "ok");
        json.put("picture", parametersService.selectByKey("imgurl").getKeyvalue() + imgurl.substring(imgurl.lastIndexOf("/")));
        json.put("code", 200);
        return json.toJSONString();
    }

    @RequestMapping("/admin/uploadFileNotEncryption")
    public String uploadFileNotEncryption(@RequestBody Map<String, String> map) {
        JSONObject json = new JSONObject();
        String img = map.get("files");
        File directory = new File("");// 参数为空
        String courseFile = parametersService.selectByKey("imgbath").getKeyvalue();
        String imgurl = BASE64imgUtil.GenerateImage(img, courseFile);
        json.put("msg", "ok");
        json.put("picture", parametersService.selectByKey("imgurl").getKeyvalue() + imgurl.substring(imgurl.lastIndexOf("/")));
        json.put("code", 200);
        return json.toJSONString();
    }

    @RequestMapping("/admin/uploadpicture")
    public String uploadFiles(HttpServletRequest request, HttpServletResponse response, Integer id) {
        JSONObject json = new JSONObject();
        List<MultipartFile> multipartFiles;
        try {
            multipartFiles = UploadUtil.getFileCheck(request, 1024 * 1024 * 2, null, 0, 0);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage()).toString();
        }
        if (multipartFiles.size() == 0) {
            // TODO 给出提示,不允许没选择文件点击上传
            json.put("msg", "请选择图片");
            json.put("code", 201);
            return json.toJSONString();
        }
        ParametersWithBLOBs imgbath = parametersService.selectByKey("imgbath");
        ParametersWithBLOBs imgurl = parametersService.selectByKey("imgurl");
        if (imgbath == null || imgurl == null) {
            json.put("msg", "error");
            json.put("code", 300);
            return json.toJSONString();
        }
        String path = imgbath.getKeyvalue();
        try {
            List<String> imgurllist = new ArrayList<>();
            for (MultipartFile file : multipartFiles) {
                String filePath = UploadUtil.uploadFile(file, path, imgurl.getKeyvalue());
                if (StringUtils.isEmpty(filePath)) {
                    json.put("msg", file.getName() + "error");
                    json.put("code", 300);
                    return json.toJSONString();
                }
                imgurllist.add(filePath);
            }
            json.put("msg", "ok");
            json.put("picture", imgurllist);
            json.put("code", 200);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("msg", "error");
            json.put("code", 300);
        }
        return json.toJSONString();
    }

}
