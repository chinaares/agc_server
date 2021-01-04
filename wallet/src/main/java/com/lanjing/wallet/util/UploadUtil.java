package com.lanjing.wallet.util;

import com.lanjing.wallet.service.ParametersService;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class UploadUtil {

    @Resource(name = "ParametersService")
    private ParametersService parametersService;

    /**
     * @param request
     * @param maxLength    文件最大限制
     * @param allowExtName 不允许上传的文件扩展名
     * @return MultipartFile集合
     * @descrption 根据HttpServletRequest对象获取MultipartFile集合
     */
    public static List<MultipartFile> getFileSet(HttpServletRequest request,
                                                 long maxLength, String[] allowExtName) {
        MultipartHttpServletRequest multipartRequest = null;
        try {
            multipartRequest = (MultipartHttpServletRequest) request;
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList<MultipartFile>();
        }

        List<MultipartFile> files = new LinkedList<MultipartFile>();
        files = multipartRequest.getFiles("files");
        // 移除不符合条件的
        for (int i = 0; i < files.size(); i++) {
            if (!validateFile(files.get(i), maxLength, allowExtName)) {
                files.remove(files.get(i));
                if (files.size() == 0) {
                    return files;
                }
            }
        }
        return files;
    }


    public static List<MultipartFile> getFileCheck(HttpServletRequest request,
                                                   long maxLength, String[] allowExtName, long width, long height) throws RuntimeException {
        MultipartHttpServletRequest multipartRequest;
        try {
            multipartRequest = (MultipartHttpServletRequest) request;
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList<>();
        }

        List<MultipartFile> files;
        files = multipartRequest.getFiles("files");
        // 移除不符合条件的
        for (int i = 0; i < files.size(); i++) {
            if (!validateFileSize(files.get(i), maxLength, allowExtName, width, height)) {
                files.remove(files.get(i));
                if (files.size() == 0) {
                    return files;
                }
            }
        }
        return files;
    }

    /**
     * @param file MultipartFile对象
     * @param path 保存路径，如“D:\\File\\”
     * @return 保存的全路径 如“D:\\File\\2345678.txt”
     * @throws Exception 文件保存失败
     * @descrption 保存文件
     */
    public static String uploadFile(MultipartFile file, String path, String imgurl) throws Exception {
        String filename = file.getOriginalFilename();
        String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase();
        String lastFileName = UUID.randomUUID().toString() + extName;
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        File temp = new File(path);
        if (!temp.isDirectory()) {
            temp.mkdir();
        }
        // 图片存储的全路径
        String fileFullPath = path + lastFileName;
        FileCopyUtils.copy(file.getBytes(), new File(fileFullPath));
        String url = imgurl + "/" + lastFileName;
        return url;
    }

    /**
     * @param file         MultipartFile对象
     * @param maxLength    文件最大限制
     * @param allowExtName 不允许上传的文件扩展名
     * @return 文件格式是否合法
     * @descrption 验证文件格式，这里主要验证后缀名
     */
    private static boolean validateFile(MultipartFile file, long maxLength,
                                        String[] allowExtName) {
        if (file.getSize() < 0 || file.getSize() > maxLength)
            return false;
        String filename = file.getOriginalFilename();

        // 处理不选择文件点击上传时，也会有MultipartFile对象，在此进行过滤
        if (filename == "") {
            return false;
        }
        String extName = filename.substring(filename.lastIndexOf("."))
                .toLowerCase();
        if (allowExtName == null || allowExtName.length == 0
                || Arrays.binarySearch(allowExtName, extName) != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 校验图片
     *
     * @param file
     * @param maxLength
     * @param allowExtName
     * @param width
     * @param height
     * @return
     * @throws RuntimeException
     */
    private static boolean validateFileSize(MultipartFile file, long maxLength,
                                            String[] allowExtName, long width, long height) throws RuntimeException {
        if (file.getSize() < 0 || file.getSize() > maxLength) {
            throw new RuntimeException(String.format("图片不可大于%d", maxLength));
        }
        String filename = file.getOriginalFilename();

        if (width != 0 && height != 0) {

            BufferedImage bufferedImage; // 通过MultipartFile得到InputStream，从而得到BufferedImage
            try {
                bufferedImage = ImageIO.read(file.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException("获取图片失败");
            }

            if (bufferedImage == null) {
                throw new RuntimeException("获取图片失败");
            }

            Integer fileWidth = bufferedImage.getWidth();
            Integer fileHeight = bufferedImage.getHeight();
            if (fileWidth != width || fileHeight != height) {
                throw new RuntimeException(String.format("图片尺寸需为%d*%d", width, height));

            }
        }

        // 处理不选择文件点击上传时，也会有MultipartFile对象，在此进行过滤
        if (filename == "") {
            return false;
        }
        String extName = filename.substring(filename.lastIndexOf("."))
                .toLowerCase();
        if (allowExtName == null || allowExtName.length == 0
                || Arrays.binarySearch(allowExtName, extName) != -1) {
            return true;
        } else {
            return false;
        }
    }
}
