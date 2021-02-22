package com.careershe.careershe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.careershe.basics.base.BaseActivity;
import com.careershe.careershe.widget.actionbar.ActionBar;
import com.hehongdan.actionbarex.OnActionBarChildClickListener;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = findViewById(R.id.ab);
        ab.setOnRightIconClickListener(new OnActionBarChildClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }

}
