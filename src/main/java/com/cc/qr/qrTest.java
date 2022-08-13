package com.cc.qr;

import com.github.hui.quick.plugin.qrcode.wrapper.QrCodeGenWrapper;
import org.junit.Test;

import javax.imageio.ImageIO;

import java.io.File;


/**
 * @author clk
 * @date 2022/6/6
 */
public class qrTest {
    @Test
    public void tqr() throws Exception {
        String zmcdsb = QrcodeT.normal("sby zhang!");
        QrcodeT.GenerateImage(zmcdsb,"E:\\gitdownProgram\\66.jpg");
        File file = new File("");

    }

    @Test
    public void testfor(){
        int f = 25;

        for (int i = 0; i < f; i++) {
            f--;
            System.out.println(i);
        }
    }
}
