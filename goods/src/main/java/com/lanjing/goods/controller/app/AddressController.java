package com.lanjing.goods.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jester.util.utils.PageInfo;
import com.jester.util.utils.Result;
import com.lanjing.goods.controller.BaseController;
import com.lanjing.goods.enums.AddressEnum;
import com.lanjing.goods.ex.CheckEx;
import com.lanjing.goods.model.Address;
import com.lanjing.goods.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户收货地址管理
 */
@RestController
@Transactional
public class AddressController extends BaseController {
    @Autowired
    AddressService addressService;

    /**
     * 添加收货地址
     *
     * @param address
     * @return
     */
    @RequestMapping("/app/address/addAddress")
    @Transactional
    public Object addAddress(@RequestBody Address address) {
        String userKey = getUserKey();
        CheckEx.check(StringUtils.isEmpty(userKey), "请重新登录");
        address.setFid(null);
        address.setUserkey(userKey);

        if (address.getStatus() == AddressEnum.ENABLE.getCode()) {
            //本次地址为默认地址，将原有默认地址去除
            addressService.resetDefaultAddress(null, userKey, AddressEnum.DISABLE.getCode());
        }

        int result = addressService.insertSelective(address);
        CheckEx.check(result < 1, "添加失败");
        return Result.success();
    }

    /**
     * 删除地址
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/address/delAddress")
    public Object delAddress(@RequestBody JSONObject param) {
        String userKey = getUserKey();
        Integer id = param.getInteger("id");
        if (StringUtils.isEmpty(userKey)) {
            return Result.error("请重新登录");
        }
        if (id == null) {
            return Result.error("参数错误");
        }

        int result = addressService.updateAddressStatus(id, userKey);
        if (result < 1) {
            return Result.error("添加失败");
        }
        return Result.success();
    }

    /**
     * 更新地址
     *
     * @param address
     * @return
     */
    @RequestMapping("/app/address/updateAddress")
    public Object updateAddress(@RequestBody Address address) {
        String userKey = getUserKey();
        Integer id = address.getFid();

        if (StringUtils.isEmpty(userKey)) {
            return Result.error("请重新登录");
        }

        Integer status = address.getStatus();
        if (id == null || status == null) {
            return Result.error("参数错误");
        }

        Address oldAddress = addressService.selectByPrimaryKey(id);

        if (!oldAddress.getUserkey().equals(userKey)) {
            return Result.error("地址id错误");
        }

        if (status == AddressEnum.ENABLE.getCode()) {
            //本次地址为默认地址，将原有默认地址去除
            addressService.resetDefaultAddress(null, userKey, AddressEnum.DISABLE.getCode());
        }

        int result = addressService.updateByPrimaryKeySelective(address);
        if (result < 1) {
            return Result.error("修改失败");
        }
        return Result.success();
    }

    /**
     * 获取地址
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/address/getAddress")
    public Object getAddress(@RequestBody JSONObject param) {
        Integer pageNum = param.getInteger("pageNum");
        Integer pageSize = param.getInteger("pageSize");

        if (pageSize == null || pageNum == null) {
            return Result.error("参数错误");
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Address> addresses = addressService.getAddress(null, getUserKey());
        PageInfo<Address> pageInfo = new PageInfo<>(addresses);

        return Result.success(pageInfo);
    }

    /**
     * 通过id获取地址
     *
     * @param param
     * @return
     */
    @RequestMapping("/app/address/getAddressById")
    public Object getAddressById(@RequestBody JSONObject param) {
        Integer id = param.getInteger("id");

        if (id == null) {
            return Result.error("参数错误");
        }

        Address address = addressService.selectByPrimaryKey(id);

        return Result.success(address);
    }

    /**
     * 获取默认地址
     *
     * @return
     */
    @RequestMapping("/app/address/getDefaultAddress")
    public Object getDefaultAddress() {
        String userKey = getUserKey();

        if (StringUtils.isEmpty(userKey)) {
            return Result.error("请重新登录");
        }

        Address address = addressService.getDefaultAddress(userKey);

        if (address == null) {
            List<Address> addressList = addressService.getAddress(null, getUserKey());
            if (addressList.size() > 0) {
                return Result.success(addressList.get(0));
            } else {
                return Result.success("请添加收货地址");
            }
        }

        return Result.success(address);
    }
}
