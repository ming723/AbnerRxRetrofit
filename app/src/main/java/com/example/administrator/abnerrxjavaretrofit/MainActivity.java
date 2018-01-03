package com.example.administrator.abnerrxjavaretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.abnerrxretrofit.listener.HttpJavaBeanCallBackListener;
import com.example.abnerrxretrofit.listener.HttpStringCallBackListener;
import com.example.abnerrxretrofit.test.TestBean;
import com.example.abnerrxretrofit.utils.HttpHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private HttpHelper mHttpHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.tv_main);
       mHttpHelper= new HttpHelper(null,true,this);
        findViewById(R.id.btn_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStringMethod();
               // postStringMethod();
               // getJavaBeanMethod();
               // postJavaBeanMethod();

            }
        });
    }
    /**
     * GET获取String请求
     * */
    private void getStringMethod() {
        Map<String, String> map = new HashMap<>();
        map.put("start", "0");
        map.put("count", "10");
        mHttpHelper.getString("top250", map, new HttpStringCallBackListener() {
            @Override
            public void success(String message) {
                mTextView.setText(message);
            }

            @Override
            public void failure(String error) {
                mTextView.setText(error);
            }
        });
    }
    /**
     * POST获取String请求
     * */
    private void postStringMethod(){
        Map<String, String> map = new HashMap<>();
        map.put("start", "0");
        map.put("count", "10");
        mHttpHelper.postString("top250", map, new HttpStringCallBackListener() {
            @Override
            public void success(String message) {
                mTextView.setText(message);
            }

            @Override
            public void failure(String error) {
                mTextView.setText(error);
            }
        });
    }
    /**
     * GET获取JavaBean请求
     * */
    private void getJavaBeanMethod(){
        Map<String, String> map = new HashMap<>();
        map.put("start", "0");
        map.put("count", "10");
        mHttpHelper.getJavaBean(TestBean.class, "top250", map, new HttpJavaBeanCallBackListener<TestBean>() {
            @Override
            public void success(TestBean testBean) {
                List<TestBean.Subjects> subjects = testBean.getSubjects();
                StringBuffer sb=new StringBuffer();
                for (TestBean.Subjects bean :subjects){
                    sb.append(bean.getTitle()).append(",");
                }
                mTextView.setText(sb.toString());
            }

            @Override
            public void failure(String error) {
                mTextView.setText(error);
            }
        });
    }

    /**
     * POST获取JavaBean请求
     * */
    private void postJavaBeanMethod(){
        Map<String, String> map = new HashMap<>();
        map.put("start", "0");
        map.put("count", "10");
        mHttpHelper.postJavaBean(TestBean.class, "top250", map, new HttpJavaBeanCallBackListener<TestBean>() {
            @Override
            public void success(TestBean testBean) {
                List<TestBean.Subjects> subjects = testBean.getSubjects();
                StringBuffer sb=new StringBuffer();
                for (TestBean.Subjects bean :subjects){
                    sb.append(bean.getTitle()).append(",");
                }
                mTextView.setText(sb.toString());
            }

            @Override
            public void failure(String error) {
                mTextView.setText(error);
            }
        });
    }
}
