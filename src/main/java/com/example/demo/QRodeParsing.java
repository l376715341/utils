package com.example.demo;

import com.alibaba.nacos.common.utils.Md5Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 二维码解析实现  二维码转byte
 */
public class QRodeParsing {

    public static void main(String[] args) {
        try {
            BufferedImage bimg = ImageIO.read(new File("D:/aaa/quiz5-abc.png"));
            StringBuilder str = new StringBuilder();
            int b = 0;
            for (int i = 0; i < bimg.getHeight(); i++) {
                for (int j = 0; j < bimg.getWidth(); j++) {
                    int a = bimg.getRGB(j, i);
                    if (a == -1){
                        str.append("0");
                    }else {
                        str.append("1");
                    }
                }
            }
            byte[] bytes = string2bytes(str.toString());

            int end = 0;
            for (int i = 0; i < bytes.length-4; i++) {
                if(bytes[i] == bytes[i+1]&&bytes[i] == bytes[i+2]&&
                bytes[i]==bytes[i+3]&& bytes[i]== 0){
                    end = i;
                    break;
                }
            }
            byte[] result = new byte[end];
            System.arraycopy(bytes, 0, result, 0, end);
            System.out.println(Md5Utils.getMD5(result));
            System.out.println(new String(result));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    static byte[] string2bytes(String input) {
        StringBuilder in = new StringBuilder(input);
        int remainder = in.length() % 8;
        if (remainder > 0)
            for (int i = 0; i < 8 - remainder; i++)
                in.append("0");
        byte[] bts = new byte[in.length() / 8];

        // Step 8 Apply compression
        for (int i = 0; i < bts.length; i++)
            bts[i] = (byte) Integer.parseInt(in.substring(i * 8, i * 8 + 8), 2);

        return bts;
    }
}
