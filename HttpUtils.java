package com.heer.dsp.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {
	
	public static final String HTTP_GET = "GET";
	public static final String HTTP_POST = "POST";
	public static final int CONNECT_TIME_OUT = 15000;
	public static final int READ_TIME_OUT = 60000;
	public static final String ACCEPT_CHARSET = "Accept-Charset";
	public static final String CHARSET_UTF8 = "UTF-8";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    public static final String CONTENT_TYPE_XML = "application/xml;charset=utf-8";
	
    public static String get(String httpurl) {
        HttpURLConnection connection = null;
        BufferedReader bufferReader = null;
        StringBuffer buffer = new StringBuffer();
        String tempLine;
        try {
//        	创建远程url连接对象
            URL url = new URL(httpurl);
//        	通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
//          设置请求方法：get
            connection.setRequestMethod(HTTP_GET);
//          设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(CONNECT_TIME_OUT);
//          设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(READ_TIME_OUT);
//          指定字符编码
            connection.setRequestProperty(ACCEPT_CHARSET, CHARSET_UTF8);
//          禁用缓存
            connection.setUseCaches(false);
//          发送请求
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//              获取输入流，封装输入流，并指定字符集
            	bufferReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CHARSET_UTF8));
                while ((tempLine = bufferReader.readLine()) != null) {
                	buffer.append(tempLine);
                	buffer.append("\n");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//          关闭资源
            if (bufferReader != null) {
                try {
                	bufferReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//          关闭远程连接
            connection.disconnect();
        }
        return buffer.toString();
    }

    public static String post(String httpUrl, String param) {
        HttpURLConnection connection = null;
        OutputStream out = null;
        BufferedReader bufferReader = null;
        StringBuffer buffer = new StringBuffer();
        String tempLine;
        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HTTP_POST);
            connection.setConnectTimeout(CONNECT_TIME_OUT);
            connection.setReadTimeout(READ_TIME_OUT);
//          默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
//          默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            connection.setRequestProperty(ACCEPT_CHARSET, CHARSET_UTF8);
//          设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_FORM);
            connection.setUseCaches(false);
            connection.connect();
//          通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            connection.getOutputStream().write(param.getBytes());
            if (connection.getResponseCode() == 200) {
                bufferReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CHARSET_UTF8));
                while ((tempLine = bufferReader.readLine()) != null) {
                	buffer.append(tempLine);
                	buffer.append("\n");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferReader != null) {
                try {
                    bufferReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();
        }
        return buffer.toString();
    }

    public static String postXml(String httpUrl, String param) {
        HttpURLConnection connection = null;
        OutputStream out = null;
        BufferedReader bufferReader = null;
        StringBuffer buffer = new StringBuffer();
        String tempLine;
        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HTTP_POST);
            connection.setConnectTimeout(CONNECT_TIME_OUT);
            connection.setReadTimeout(READ_TIME_OUT);
//          默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
//          默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            connection.setRequestProperty(ACCEPT_CHARSET, CHARSET_UTF8);
            connection.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_XML);
            connection.setUseCaches(false);
            connection.connect();
//          通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            connection.getOutputStream().write(param.getBytes());
            if (connection.getResponseCode() == 200) {
                bufferReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CHARSET_UTF8));
                while ((tempLine = bufferReader.readLine()) != null) {
                    buffer.append(tempLine);
                    buffer.append("\n");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferReader != null) {
                try {
                    bufferReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();
        }
        return buffer.toString();
    }
}