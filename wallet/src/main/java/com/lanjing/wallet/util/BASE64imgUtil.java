package com.lanjing.wallet.util;

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


    }

    /**
     * 图片不加密,直接保存
     * @param savepath
     * @return
     */
    public  static String generateImageNotEncryption(String savepath,String img){
        try {

            String imgurl = savepath+"/"+ UUID.randomUUID().toString().replace("-","")+".png";
            File file = new File(imgurl);
            BASE64Decoder decode = new BASE64Decoder();
            byte[] byteImgF = decode.decodeBuffer(img);
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(byteImgF);
            outputStream.flush();
            outputStream.close();
            return imgurl;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
