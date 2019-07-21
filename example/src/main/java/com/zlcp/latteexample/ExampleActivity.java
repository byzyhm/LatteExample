package com.zlcp.latteexample;

import com.zlcp.lattecore.activities.ProxyActivity;
import com.zlcp.lattecore.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        TextView textView = findViewById(R.id.tvTest);
//        textView.setText(" 测试内容："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//
//    }
}
