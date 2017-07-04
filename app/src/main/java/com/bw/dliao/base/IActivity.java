package com.bw.dliao.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bw.dliao.R;


/**
 * 所有的 activity 都继承
 */
public class IActivity extends FragmentActivity implements View.OnClickListener{

    private TextView textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i);





    }



    public void setPubTitle(String title){
        textViewTitle = (TextView) findViewById(R.id.pub_title_title);
        textViewTitle.setText(title);
    }

    public void setLeftBtn(){
        Button btnRight = (Button) findViewById(R.id.pub_title_leftbtn);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     *
     * @param clazz
     * @param bundle
     * @param requestCode
     */
    public void toActivity(Class clazz , Bundle bundle, int requestCode){
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if(requestCode == 0){
            startActivity(intent);
        }else {
            startActivityForResult(intent,requestCode);
        }
    }


    @Override
    public void onClick(View v) {

    }
}
