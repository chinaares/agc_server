package com.lanjing.otc.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.UUID;

public class BASE64imgUtil {

    public static String GetImageStr(String imgFile)
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    public static String GenerateImage(String base64str,String savepath)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (base64str == null) //图像数据为空
            return null;
        // System.out.println("开始解码");
        String imgurl = savepath+"/"+ UUID.randomUUID().toString().replace("-","")+".png";
        File file = new File(imgurl);
        try {
            OutputStream outputStreamB = new FileOutputStream(file);
            BASE64Decoder decodeB = new BASE64Decoder();
            byte[] bytesB = decodeB.decodeBuffer(base64str);
            outputStreamB.write(bytesB);
            outputStreamB.flush();
            outputStreamB.close();
            return imgurl;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        /*BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(base64str);
            //  System.out.println("解码完成");
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            // System.out.println("开始生成图片");
            //生成jpeg图片
            OutputStream out = new FileOutputStream(savepath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }*/
    }
}
