/**
 * 文件名  ：SmsUtil.java
 * 
 * 描        述  ：
 * 修    改 人 ：huad
 * 修改时间  ：{date}
 * 跟踪单号  ： 跟踪单号
 *
 */
package cn.bluemobi.util.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.bluemobi.util.prop.PropUtils;

/**
 * 短信工具类
 * 功能详细描述
 * @author huad
 * version [版本号，2015年4月10日]
 * see     [相关类/方法]
 * since   [产品/模块版本]
 */
public class SmsUtil
{
    /**
     * 
     * 短信接口平台
     * 向手机上发送验证码.tempid是短信平台上设置的模板.
     * @param vCode
     * @param phone
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String sendMessage(String vCode, String phone)
    {
        OutputStream out = null;
        BufferedReader rd = null;
        String responseContent = "";
        HttpURLConnection urlConn = null;
        //短信接口用户名和密码
        String userName = PropUtils.getProperty("SMS_USERNAME");
        String scode = PropUtils.getProperty("SMS_SCODE");
        //短信模板ID
        String tempid = PropUtils.getProperty("SMS_TEMPID");
        try
        {
            String _url = "http://222.185.228.25:8000/msm/sdk/http/sendsmsutf8.jsp?username=" + userName + "&scode="
                + scode;
            String _param = "&content=@1@=" + vCode + "&tempid=" + tempid + "&mobile=" + phone;
            URL url = null;
            urlConn = null;
            url = new URL(_url);
            urlConn = (HttpURLConnection)url.openConnection();
            
            urlConn.setRequestMethod("POST");
            urlConn.setDoOutput(true);
            urlConn.connect();
            
            out = urlConn.getOutputStream();
            out.write(_param.getBytes("UTF-8"));
            out.flush();
            rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "GBK"));
            StringBuffer sb = new StringBuffer();
            int ch;
            while ((ch = rd.read()) > -1)
            {
                sb.append((char)ch);
            }
            responseContent = sb.toString();
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (urlConn != null)
                {
                    urlConn.disconnect();
                }
                if (rd != null)
                {
                    rd.close();
                }
                
                if (out != null)
                {
                    out.close();
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return responseContent;
    }
}
