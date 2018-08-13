package com.cobweb.commons;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 远程执行shell命令工具类
 *
 * @author: XRom
 * @createdTime: 2018-08-13 20:04:45
 */
public class RemoteShellExecutorUtils {

    /* 服务器连接信息 */
    private Connection connection;

    /* 服务器ip地址 */
    private String serverAddress;

    /* 登录用户名 */
    private String username;

    /* 登录密码 */
    private String password;

    /* 默认编码 */
    private String charset = Charset.defaultCharset().toString();

    /* 等待超时时间 */
    private static final int TIME_OUT = 1000 * 5 * 60;

    /* 不对外提供默认无参构造方法 */
    private RemoteShellExecutorUtils() {
    }

    /**
     * 构造函数
     *
     * @param serverAddress 服务器地址
     * @param username      服务器登录用户名
     * @param password      服务器登录用户密码
     */
    public RemoteShellExecutorUtils(String serverAddress, String username, String password) {
        this.serverAddress = serverAddress;
        this.username = username;
        this.password = password;
    }


    /**
     * 进行服务器认证
     *
     * @return 是否认证成功：true-认证成功； false-认证失败
     * @throws IOException 可能抛出的异常
     */
    private boolean authentication() throws IOException {
        connection = new Connection(serverAddress);
        connection.connect();
        return connection.authenticateWithPassword(username, password);
    }

    /**
     * 执行脚本
     *
     * @param command 执行的shell命令，或者是服务器shell命令地址
     * @return 执行状态结果： 0-成功
     * @throws Exception 可能抛出的异常
     */
    public int execute(String command) throws Exception {
        InputStream inputStream = null;
        String response;
        int ret = -1;
        try {
            if (authentication()) {
                Session session = connection.openSession();
                session.execCommand(command);

                inputStream = new StreamGobbler(session.getStdout());
                response = processStream(inputStream, charset);

                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);

                System.out.println("response=" + response);

                ret = session.getExitStatus();
            } else {
                throw new Exception("登录远程机器失败" + serverAddress); // 自定义异常类 实现略
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
            IOUtils.closeQuietly(inputStream);
        }
        return ret;
    }

    /**
     * 处理字节输入流，读取服务端打印日志
     *
     * @param inputStream 字节输入流
     * @param charset     字符编码
     * @return 服务器端shell命令输出日志
     * @throws Exception 可能抛出的异常
     */
    private String processStream(InputStream inputStream, String charset) throws Exception {
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (inputStream.read(buf) != -1) {
            sb.append(new String(buf, charset));
        }
        return sb.toString();
    }

    public static void main(String args[]) throws Exception {
        RemoteShellExecutorUtils executor = new RemoteShellExecutorUtils("192.168.147.128", "root", "XRom19980126.");
        // 执行myTest.sh 参数为java Know dummy
        System.out.println(executor.execute("/usr/local/temp/mkdir.sh"));
    }
}