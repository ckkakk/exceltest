package com.cc.qr;

import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeGenWrapper;
import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeOptions;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author clk
 * @date 2022/6/6
 */
public class QrcodeT {

    public static String normal(String text) throws Exception {
        return QrCodeGenWrapper.of(text).asString();
    }

    /**
     * 生成带颜色的二维码
     */
    public static String color(String text) throws Exception {
        return QrCodeGenWrapper.of(text)
                .setDrawPreColor(Color.BLUE) // 蓝色二维码
                .asString();
    }

    /**
     * 生成带背景图片的二维码
     */
    public static String bg(String text, InputStream bgFile) throws Exception {
        return QrCodeGenWrapper.of(text)
                .setBgImg(bgFile)
                .setBgStyle(QrCodeOptions.BgImgStyle.PENETRATE)
                .setBgH(500)
                .setBgW(500)
                .setW(500)
                .setH(500)
                .asString();
    }

    /**
     * 生成图片填充二维码
     */
    public static String fill(String text, InputStream bgFile) throws Exception {
        return QrCodeGenWrapper.of(text)
                .setW(500)
                .setH(500)
                .setDrawEnableScale(true)
                .setErrorCorrection(ErrorCorrectionLevel.H)
                .setDrawStyle(QrCodeOptions.DrawStyle.IMAGE)
                .addImg(1, 1, bgFile)
                .asString();
    }

    /**
     * 生成gif二维码
     */
    public static String gif(String text, InputStream bgFile) throws Exception {
        return QrCodeGenWrapper.of(text)
                .setW(500)
                .setH(500)
                .setBgImg(bgFile)
                .setBgOpacity(0.5f)
                .setPicType("gif")
                .asString();
    }

    /**
     * 生成特殊形状二维码
     */
    public static String style(String text, InputStream bgFile) throws Exception {
        return QrCodeGenWrapper.of(text)
                .setBgH(500)
                .setBgW(500)
                .setW(500)
                .setH(500)
                .setDrawEnableScale(true)
                .setDrawStyle(QrCodeOptions.DrawStyle.CIRCLE)
                .asString();
    }


    /**

     * base64转图片

     * @param base64str base64码

     * @param savePath 图片路径

     * @return

     */

    public static boolean GenerateImage(String base64str, String savePath) {
        //对字节数组字符串进行Base64解码并生成图片
        if (base64str == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(base64str);
            for (int i = 0; i < b.length; ++i) {
                //调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(savePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**

     * base64转图片

     * @param base64Code base64码

     */

    public static void convertBase64ToImage(String base64Code){
        BufferedImage image = null;
        byte[] imageByte = null;

        try {
            imageByte = DatatypeConverter.parseBase64Binary(base64Code);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(new ByteArrayInputStream(imageByte));
            bis.close();
            File outputfile = new File("d:\\sealImg.jpg");
            ImageIO.write(image, "jpg", outputfile);

        } catch (IOException e) {
            e.printStackTrace();

        }

    }


}
