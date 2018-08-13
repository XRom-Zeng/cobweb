package com.cobweb.commons;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * linux shell 脚本操作工具类
 *
 * @author: XRom
 * @createdTime: 2018-08-13 20:04:45
 */
public class ShellUtil {

    /**
     * 执行shell文件
     * @param filePath  shell脚本文件路径
     * @return
     */
    public String executeShellFile(String filePath) throws Exception {
        Process ps = Runtime.getRuntime().exec(filePath);
        ps.waitFor();

        BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }


    public static void main(String[] args) throws Exception {
        new ShellUtil().executeShellFile("/usr/local/temp/mkdir.sh");
    }

}  