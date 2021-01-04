package com.lanjing.wallet.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.config.ConfigConst;

import static com.lanjing.wallet.config.ConfigConst.Turn_ADDRESS;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @date 2019/9/16 16:52
 * 黄写的eth工具类
 */
public class EthUtilHuang {

    /**
     * 创建钱包
     * @param password
     * @return
     */
    public static String createEthAddress(String password){
        try{
             String url = "http://13.212.161.150:8085/eth/address/create?password="+password;
              String data =  HttpUtil.sendPostTextPlain(url);
              JSONObject object = JSONObject.parseObject(data);
              if(object.getInteger("code")==0)
              return object.getString("data");

          }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取ETH余额
     * @param address
     * @return
     */
    public static double getEthBalance(String address){
        try{
            String url = "http://13.212.161.150:8085/eth/balance?address="+address;
            String data =  HttpUtil.sendGet(url);
            JSONObject object = JSONObject.parseObject(data);
            if(object.getInteger("code")==0)
                return object.getDouble("data");

        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }
    /**
     * 获取代币余额
     * @param address
     * @return
     */
    public static double getTokenBalance(String address,String contract){
        try{
            String url = "http://13.212.161.150:8085/eth/token?address="+address+"&contract="+contract;
            String data =  HttpUtil.sendGet(url);
            JSONObject object = JSONObject.parseObject(data);
            if(object.getInteger("code")==0)
                return object.getDouble("data");

        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }
    /**
     * eth转账
     * @param
     * @return
     */
    public static String ethTransfer(String from,String to,String password,String amount,Double fee){
        try{

            String url = "http://13.212.161.150:8085/eth/send/eth?from="+from+"&to="+to+"&password="+password+"&amount="+amount+"&fee="+fee;

            if(fee==null){
                url= url.replace("&fee=null","");
            }
            String data =  HttpUtil.sendPostTextPlain(url);
            System.out.println("转账的得到："+data);
            JSONObject object = JSONObject.parseObject(data);
            if(object.getInteger("code")==0)
                return object.getString("data");

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 代币转账
     * @param
     * @return
     */
    public static String tokenTransfer(String contract,String from,String to,String password,String amount,Double fee){
        try{
            String url = "http://13.212.161.150:8085/eth/send/token?from="+from+"&to="+to+"&password="+password+"&amount="+amount+"&fee="+fee+"&contract="+contract;
            OutputStream outputStream = new FileOutputStream("代币转账.txt",true);
            outputStream.write((url+"\n").getBytes());

            if(fee==null){
                url= url.replace("&fee=null","");
            }
            String data = HttpUtils.sendPost(url, null);
//            String data =  HttpUtil.sendPostTextPlain(url);
            System.out.println("转账的得到："+data);
            outputStream.write(("转账的得到："+data+"\n").getBytes());
            outputStream.flush();
            outputStream.close();
            JSONObject object = JSONObject.parseObject(data);
            if(object.getInteger("code")==0)
                return object.getString("data");

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static JSONObject getTransactionByHash(String hash){
        try{
            String url = "http://13.212.161.150:8085/eth/transaction?txid="+hash;
            String data =  HttpUtil.sendGet(url);
            JSONObject object = JSONObject.parseObject(data);
            if(object.getInteger("code")==0)
                return object.getJSONObject("data");

        }catch (Exception e){
            e.printStackTrace();
        }
     return  null;
    }


    /**
     * 获取区块高度
     * @return
     */
     public static  int getBlock(){
        try{
            String url = "http://13.212.161.150:8085/eth/block";
            String data =  HttpUtil.sendGet(url);
            JSONObject object = JSONObject.parseObject(data);
            if(object.getInteger("code")==0)
                return object.getInteger("data");
        }catch(Exception e){
            e.printStackTrace();
        }
        return  0;
    }



    /**
     * 根据区块查询交易
     * @return
     */
    public static JSONArray getHashListByBlock(int blocks){
        try{
            String url = "http://13.212.161.150:8085/eth/transactions?block="+blocks;
            String data =  HttpUtil.sendGet(url);
            JSONObject object = JSONObject.parseObject(data);
            if(object.getInteger("code")==0)
                return object.getJSONArray("data");
        }catch(Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    /**
     * 确认交易
     *
     * @param tx_hash 交易标识
     * @return
     */
    public static int confirm(String tx_hash){
        try {
            if(!tx_hash.startsWith("0x")){
                return 0;
            }
            System.out.println("确认交易传输："+tx_hash);
            JSONObject jsonObject =   getTransactionByHash(tx_hash);
            System.out.println("确认交易得到:"+jsonObject);
            String status =  jsonObject.getString("status");
            if(status.equals("confirm")){
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    /**
     * 根据区块查询交易
     * @return
     */
    public static JSONObject getPrivate(String address,String password){
        try{
            String url = "http://13.212.161.150:8085/eth/private?address="+address+"&password="+password;
            System.out.println(url);
            String data =  HttpUtil.sendGet(url);
            JSONObject object = JSONObject.parseObject(data);
            if(object.getInteger("code")==0)
                return object;
        }catch(Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    
    public  static void main(String[] str){
//      JSONObject balance =   getTransactionByHash("0xf57a1ec4695321c97414b021585186a29c508ee428d4b6c2751a866bb72d510a");
//      System.out.println(balance);
//
//       String a =  MD5Util.getMD5String(MD5Util.getMD5String(MD5Util.getMD5String("SADAFAGAnvxvf236546$#%2012457")));
//        System.out.println(a);
//    	Integer i = 2012450;
//    	while (i < 2012888) {
            String password =  MD5Util.getMD5String(MD5Util.getMD5String(MD5Util.getMD5String(ConfigConst.Turn_PWD+"2012457")));
//            if ("001FA7D2C0FFA0ABF857B3ED10813D82".equalsIgnoreCase(password)) {
//            	System.out.println("i="+i);
//            	JSONObject rs = getPrivate("0xb8a6a5191199b7d07f9101e2d1b2e9ed0b74407c", password);
//                if (rs!=null) {
//             	   System.out.println(rs);
//     			return;
//     		   }
//			}
//            
////            System.out.println(i);
////    		i = i+1;
//		}

//    	String strr =  EthUtilHuang.ethTransfer("0x495345696a6065f1971C99fb4765c72DEdfD0412", "0x047351bf7c6711d7a766129720F444cc0075eB9A", a, "0.02", 0.001);;
//    	System.out.println(strr);
//    	String txid = EthUtilHuang.tokenTransfer(ConfigConst.PLATFORM_ADDRESS, "0xFc0F51306df549b6f345608D17B8913bfcbf1821",
//    			"0x495345696a6065f1971C99fb4765c72DEdfD0412", password, "100", null);
//    	System.out.println(txid);
    	int i = getBlock();
    	
//    	JSONArray ja = getHashListByBlock(11218678);
    	System.out.println(i);
//    	System.out.println(ja.toJSONString());
//    	
//    	System.out.println(confirm("0x4003740f9c2df06285429b80faa4b83731ad3354f731b0c09641ebf9cd06d2e3"));
//    	String password =  MD5Util.getMD5String(MD5Util.getMD5String(MD5Util.getMD5String(ConfigConst.Turn_PWD+"2012457")));
//    	String add = createEthAddress(password);
//    	System.out.println(add);
    }

}
