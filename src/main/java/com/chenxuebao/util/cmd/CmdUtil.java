package com.chenxuebao.util.cmd;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @program: swis
 * @description:
 * @author: 陈学宝
 * @create: 2022-05-17 22:44
 **/
public class CmdUtil {
    /**
     * @Description: 执行bat文件,新开窗口 有bug，打开cmd后必须手动关闭，否则会一直阻塞进程(可末尾追加exit解决此问题)
     * @Author:陈学宝
     * @Date: 2022/5/17 22:42
     */
    public static String executeBatFileWithNewWindow(String file,boolean isAsynchronous)
    {
        String cmdCommand = "cmd.exe /k start " + file;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if(isAsynchronous){
                Runtime.getRuntime().exec(cmdCommand);
            }else {
                Process process = Runtime.getRuntime().exec(cmdCommand);
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(process.getInputStream(), "GBK"));
                String line;
                while((line=bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(line+"\n");
                }
                return stringBuilder.toString();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
