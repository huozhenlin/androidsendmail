package com.example.hzl.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendMessage sendMessage = new sendMessage();
                sendMessage.start();
                Toast.makeText(getApplicationContext(), "发送成功",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    //与网络连接相关的操作都要在子线程中完成，当然也可以在Service服务里操作
    static final class sendMessage extends Thread {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                //创建HtmlEmail类
                HtmlEmail email = new HtmlEmail();
                //填写邮件的主机明，我这里使用的是163
                email.setHostName("smtp.139.com");
                email.setTLS(true);
                email.setSSL(true);
                //设置字符编码格式，防止中文乱码
                email.setCharset("gbk");
                //设置收件人的邮箱
                email.addTo("1306733585@qq.com；1031734796@qq.com");
                //设置发件人的邮箱
                email.setFrom("13580044203@139.com");
                //填写发件人的用户名和密码
                email.setAuthentication("13580044203@139.com", "88248870");
                //填写邮件主题
                email.setSubject("您好");
                String s1="1";
                String s2="2";
                //填写邮件内容
                email.setMsg("嘿嘿，邮件功能将集成在今后的项目中");
                //发送邮件
                email.send();

            } catch (EmailException e) {
                // TODO Auto-generated catch block
                Log.i("TAG", "---------------->"+e.getMessage());
            }
        }
    }
}
