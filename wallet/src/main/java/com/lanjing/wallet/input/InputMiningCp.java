package com.lanjing.wallet.input;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.agc.domain.AgcConsumeProfit;
import com.lanjing.wallet.agc.domain.AgcConsumeRecord;
import com.lanjing.wallet.agc.domain.AgcCpProfit;
import com.lanjing.wallet.agc.service.IAgcConsumeRecordService;
import com.lanjing.wallet.agc.service.IAgcCpProfitService;
import com.lanjing.wallet.dao.UsersMapper;
import com.lanjing.wallet.dao.WelletsMapper;
import com.lanjing.wallet.model.Users;
import com.lanjing.wallet.util.StringUtils;

@Component
@RestController
public class InputMiningCp {

	@Resource
	UsersMapper usersMapper;

	@Resource
	private WelletsMapper welletsMapper;

	@Autowired
	private IAgcCpProfitService agcCpProfitService;

	@Autowired
	private IAgcConsumeRecordService agcConsumeRecordService;

	@RequestMapping("/input/readerCpXml")
	public String readerCpXml() {
		List<MiningCp> userMiningCp = readerCpXml2("/历史算力数据.xlsx");
		int total = userMiningCp.size();
		int totalt = 0;
		StringBuffer errStr = new StringBuffer("导入失败：");
		for (MiningCp miningCp : userMiningCp) {
			System.out.println("json:" + JSONObject.toJSONString(miningCp));

			Users user = usersMapper.selectByPhone(miningCp.getPhone());

			if (StringUtils.isNull(user)) {
				System.err.println("找不到用户:" + miningCp.getName()+"---"+miningCp.getPhone());
				errStr.append("\n");
				errStr.append(miningCp.getName()+"---"+miningCp.getPhone());
			} else {
				totalt = totalt + 1;
				AgcCpProfit ap = new AgcCpProfit();
				AgcConsumeRecord record = new AgcConsumeRecord();
				ap.setCreateTime(new Date());
				ap.setUpdateTime(new Date());
				ap.setUserId(user.getKeyes());
				ap.setStatus("1");
				record.setCreateTime(new Date());
				record.setUpdateTime(new Date());
				record.setUserId(user.getKeyes());

				if (StringUtils.isNotEmpty(miningCp.getpCp())) {
					BigDecimal pCp = new BigDecimal(
							miningCp.getpCp().replaceAll("G", "").replaceAll("g", "").replaceAll(" ", ""));
					ap.setCp(pCp);
				} else {
					ap.setCp(BigDecimal.ZERO);
				}

				if (StringUtils.isNotEmpty(miningCp.getcCp())) {
					BigDecimal cCp = new BigDecimal(
							miningCp.getcCp().replaceAll("G", "").replaceAll("g", "").replaceAll(" ", ""));
					record.setConsumeCp(cCp);
				} else {
					record.setConsumeCp(BigDecimal.ZERO);
				}
				
				if (record.getConsumeCp().compareTo(BigDecimal.ZERO)!=0) {
					agcConsumeRecordService.insertAgcConsumeRecord(record);
				}

				if (ap.getCp().compareTo(BigDecimal.ZERO)!=0) {
					agcCpProfitService.insertAgcCpProfit(ap);
				}
				
				
			}
		}
		System.out.println("总共需要导入人数：" + total);
		System.out.println("总共实际导入人数：" + totalt);
		System.out.println("导入失败：" + errStr.toString());
		return "导入ok";
	}

	// 读取历史算力
	private List<MiningCp> readerCpXml2(String fileName) {
		List<MiningCp> userMiningCp = new ArrayList<MiningCp>();
		try {
			// 读取表格数据
			InputStream in = this.getClass().getResourceAsStream(fileName);
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
			MiningCp userInputEntity;
			for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
				userInputEntity = new MiningCp();
				XSSFRow xssfRow = xssfSheet.getRow(i);// 获取每一行的数据
				// 设置单元格类型
				XSSFCell cell = xssfRow.getCell(0);
				if (StringUtils.isNotNull(cell)) {
					cell.setCellType(CellType.STRING);
					String stringCellValue0 = cell.getStringCellValue();
					userInputEntity.setName(stringCellValue0);
				}

				XSSFCell cell1 = xssfRow.getCell(1);
				if (StringUtils.isNotNull(cell1)) {
					cell1.setCellType(CellType.STRING);
					String stringCellValue = cell1.getStringCellValue();
					userInputEntity.setPhone(stringCellValue);
				}

				XSSFCell cell2 = xssfRow.getCell(2);
				if (StringUtils.isNotNull(cell2)) {
					cell2.setCellType(CellType.STRING);
					String stringCellValue1 = cell2.getStringCellValue();
					userInputEntity.setpCp(stringCellValue1);
				}

				XSSFCell cell3 = xssfRow.getCell(3);
				if (StringUtils.isNotNull(cell3)) {
					cell3.setCellType(CellType.STRING);
					String numericCellValue = cell3.getStringCellValue();
					userInputEntity.setcCp(numericCellValue);
				}

				userMiningCp.add(userInputEntity);

			}
			return userMiningCp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userMiningCp;
	}
}
