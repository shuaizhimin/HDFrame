//package com.handsome.frame.android.test;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.webkit.WebView;
//import android.widget.TextView;
//
//import com.handsome.frame.android.R;
//import com.handsome.frame.android.widget.test.TestView;
//
//public class TextViewActivity extends Activity {
//    private TextView mTestView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.activity_text_view);
//        mTestView=(TextView)findViewById(R.id.test);
//        mTestView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(TextViewActivity.this,SingleInstanceActivity.class));
//            }
//        });
//
//
//        WebView webView=new WebView(this);
//
//    }
//}
