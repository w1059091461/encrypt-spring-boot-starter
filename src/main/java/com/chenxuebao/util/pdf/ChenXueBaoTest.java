package com.chenxuebao.util.pdf;

import java.awt.image.BufferedImage;
import java.io.*;

import static com.chenxuebao.util.pdf.PdfConvertHtmlUtil.*;

/**
 * @Author: chenXueBao
 * @Date: 2022/4/20 20:26
 * @Description:
 * @version: V1.0
 */
public class ChenXueBaoTest {
    public static void main(String[] args) {
        File file = new File("D:\\document\\1844674407189710086418446744072037493337.pdf");
        String htmlPath = "D:\\document\\1844674407189710086418446744072037493337.html";
        InputStream inputStream = null;
        BufferedImage bufferedImage = null;
        try {
            inputStream = new FileInputStream(file);
            bufferedImage = pdfStreamToPng(inputStream);
            String base64_png = bufferedImageToBase64(bufferedImage);
            createHtmlByBase64(base64_png,htmlPath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream != null){inputStream.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
