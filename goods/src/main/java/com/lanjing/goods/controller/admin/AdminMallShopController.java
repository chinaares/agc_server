package com.lanjing.goods.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.PageHelper;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.lanjing.goods.controller.BaseController;
import com.lanjing.goods.dao.MallFundListMapper;
import com.lanjing.goods.dao.MallWalletMapper;
import com.lanjing.goods.model.*;
import com.lanjing.goods.model.po.MallShops;
import com.lanjing.goods.model.po.Merchant;
import com.lanjing.goods.service.AdminService;
import com.lanjing.goods.service.MallShopService;
import com.lanjing.goods.service.MallWalletService;
import com.lanjing.goods.service.PhonecodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 店铺管理
 *
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-21 10:14
 */
@RestController
@SuppressWarnings("all")
public class AdminMallShopController extends BaseController {
    @Autowired
    private MallShopService mallShopService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private MallWalletMapper mallWalletMapper;

    @Autowired
    private MallFundListMapper mallFundListMapper;

    @Autowired
    private PhonecodeService phonecodeService;

    @Autowired
    private MallWalletService mallWalletService;

    /**
     * 获取店铺信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/shop/getMerchant")
    public Object getMerchant(@RequestBody JSONObject param) {

        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageSize == null || pageNum == null) {
            return Result.error("参数错误");
        }

        String key = param.getString("key");

        PageHelper.startPage(pageNum, pageSize);
        List<Merchant> list = mallShopService.getMerchant(key);

        return Result.success(PageInfo.of(list));
    }

    /**
     * 添加店铺信息
     *
     * @param mallSort
     * @return
     */
    @RequestMapping("/admin/shop/addMallShop")
    public Object addMallShop(@RequestBody MallShop mallShop) {
        String phone = mallShop.getContactNumber();

        if (StringUtils.isEmpty(phone)) {
            return Result.error("参数错误");
        }

        Admin admin = adminService.findByAname(phone);
        if (admin != null) {
            return Result.error("手机号码已存在");
        }

        //添加店铺
        int result = 0;
        try {
            result = mallShopService.addMallShop(mallShop);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

        return Result.rows(result);
    }

    /**
     * 修改店铺钱包地址
     *
     * @param mallSort
     * @return
     */
    @RequestMapping("/admin/shop/updateAddress")
    public Object updateAddress(@RequestBody JSONObject param) {
        //登录账号id
        Integer id = getId();

        Integer coinId = param.getInteger("coinId");
        String address = param.getString("address");

        if (id == null || coinId == null || StringUtils.isEmpty(address)) {
            return Result.error("参数错误");
        }

        Admin admin = adminService.selectByPrimaryKey(id);
        if (admin == null) {
            return Result.error("获取登陆信息失败");
        }


        int result = mallWalletMapper.updateAddressByCodeAndCoinId(address, admin.getCode(), coinId);

        return Result.rows(result);
    }

    /**
     * 修改店铺信息
     *
     * @param mallSort
     * @return
     */
    @RequestMapping("/admin/shop/updateMallShop")
    public Object updateMallShop(@RequestBody MallShop mallShop) {
        Long code = mallShop.getCode();

        if (code==null) {
            return Result.error("参数错误");
        }

        MallShop oldMallShop=mallShopService.findByCode(code);
        if (oldMallShop==null){
            return Result.error("code不存在");
        }

        mallShop.setId(oldMallShop.getId());

        int result = mallShopService.updateByPrimaryKeySelective(mallShop);

        return Result.rows(result);
    }

    /**
     * 0 冻结，1正常，-1注销
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/shop/operateMerchant")
    public Object operateMerchant(@RequestBody JSONObject param) {
        Long code = param.getLong("code");
        Integer status = param.getInteger("status");
        String password = param.getString("password");

        if (code == null || status == null || StringUtils.isEmpty(password)) {
            return Result.error("参数错误");
        }

        Admin admin = adminService.findByCode(code);
        if (admin == null) {
            return Result.error("code不存在");
        }

        Admin admins = adminService.selectByPrimaryKey(getId());

        if (!password.equals(admins.getAtionpassword())) {
            return Result.error("密码错误");
        }

        Admin updateAdmin = new Admin();
        updateAdmin.setFid(admin.getFid());
        updateAdmin.setState(status.toString());

        MallShop mallShop = mallShopService.findByCode(code);
        if (mallShop == null) {
            return Result.error("未找到店铺信息");
        }

        MallShop updateMallShop = new MallShop();
        updateMallShop.setId(mallShop.getId());
        if (status == -1 || status == 0) {
            updateMallShop.setType(-1);
        } else {
            updateMallShop.setType(0);
        }

        int result;
        try {
            result = mallShopService.operateMerchant(updateAdmin, updateMallShop);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }

        return Result.rows(result);
    }

    /**
     * 店铺账户
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/shop/getShopAccount")
    public Object getShopAccount(@RequestBody JSONObject param) {
        Long code = param.getLong("code");

        if (code == null) {
            return Result.error("参数错误");
        }

        List<MallWallet> wallets = mallWalletMapper.findByCode(code);
        List<MallFundList> mallFundLists = mallFundListMapper.findByCode(code);

        JSONObject result = new JSONObject();
        result.put("wallets", wallets);
        result.put("mallFundLists", mallFundLists);

        return Result.success(result);
    }

    /**
     * 店铺资料
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/shop/getMallShop")
    public Object getMallShop(@RequestBody JSONObject param) {
        Long code = param.getLong("code");

        if (code == null) {
            return Result.error("参数错误");
        }

        MallShops mallShop = mallShopService.findByCodes(code);

        return Result.success(mallShop);
    }

    /**
     * 重置密码
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/shop/operatePassword")
    public Object operatePassword(@RequestBody JSONObject param) {
        Integer id = getId();
        if (id == null) {
            return Result.error("请重新登陆");
        }

        String code = param.getString("code");
        String password = param.getString("password");

        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(password)) {
            return Result.error("参数错误");
        }

        Admin admin = adminService.selectByPrimaryKey(id);

        MallShop mallShop = mallShopService.findByCode(admin.getCode());

        if (phonecodeService.selectByCode(mallShop.getContactNumber(), code) <= 0) {
            return Result.error("验证码错误");
        }

        Admin updateAdmin = new Admin();
        updateAdmin.setFid(id);
        updateAdmin.setLoginpassword(password);

        int result = adminService.updateByPrimaryKeySelective(updateAdmin);

        return Result.rows(result);
    }

    /**
     * 找回密码
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/shop/findPassword")
    public Object findPassword(@RequestBody JSONObject param) {

        String code = param.getString("code");
        String phone = param.getString("phone");
        String password = param.getString("password");

        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(password) || StringUtils.isEmpty(phone)) {
            return Result.error("参数错误");
        }

        MallShop mallShop = mallShopService.findByPhone(phone);
        if (mallShop == null) {
            Result.error("未找到店铺联系人电话");
        }

        Admin admin = adminService.findByCode(mallShop.getCode());
        if (admin == null) {
            return Result.error("未找到账户");
        }
        if (phonecodeService.selectByCode(mallShop.getContactNumber(), code) <= 0) {
            return Result.error("验证码错误");
        }

        Admin updateAdmin = new Admin();
        updateAdmin.setFid(admin.getFid());
        updateAdmin.setLoginpassword(password);

        int result = adminService.updateByPrimaryKeySelective(updateAdmin);

        return Result.rows(result);
    }

    /**
     * 管理员重置店铺密码
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/shop/adminOperatePassword")
    public Object adminOperatePassword(@RequestBody JSONObject param) {
        Integer id = getId();
        if (id == null) {
            return Result.error("请重新登陆");
        }

        Long code = param.getLong("code");
        String password = param.getString("password");

        if (code==null || StringUtils.isEmpty(password)) {
            return Result.error("参数错误");
        }

        Admin admin = adminService.selectByPrimaryKey(id);

        Integer role = admin.getArole();
        if (role==0){
            return Result.error("店铺账户");
        }

        Admin shopAdmin = adminService.findByCode(code);

        Admin updateAdmin = new Admin();
        updateAdmin.setFid(shopAdmin.getFid());
        updateAdmin.setLoginpassword(password);

        int result = adminService.updateByPrimaryKeySelective(updateAdmin);

        return Result.rows(result);
    }

    /**
     * 提币
     *
     * @param param
     * @return
     */
    @RequestMapping("/admin/shop/withdraw")
    public Object withdraw(@RequestBody JSONObject param){
        Integer id = getId();
        if (id == null) {
            return Result.error("请重新登陆");
        }

        Admin admin = adminService.selectByPrimaryKey(id);
        int coinId = param.getIntValue("coinId");
        double acount = param.getDoubleValue("aconut");
        String code = param.getString("code");
        if (coinId == 0 || acount == 0 || code == null){
            return Result.error("参数缺失");
        }
        if (code == null || "".equals(code)){
            return Result.error("请输入验证码");
        }
        if(acount < 0){
            return Result.error("数量异常");
        }
        MallShop mallShop = mallShopService.findByCode(admin.getCode());

        if (phonecodeService.selectByCode(mallShop.getContactNumber(), code) <= 0) {
            return Result.error("验证码错误");
        }
        int result = mallWalletService.withdraw1(mallShop.getCode(),coinId,acount);
        if (result > 0){
            return Result.success();
        }
        return Result.error();
    }
}
