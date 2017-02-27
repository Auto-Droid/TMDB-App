package com.sourabhkarkal.movieapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sourabhkarkal.movieapp.service.iWebListener;


/**
 * Created by sourabhkarkal on 24/02/17.
 */
public abstract class BaseActivity extends AppCompatActivity implements iWebListener,View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View makeClickable(Activity activity, int id)
    {
        View viewobj = activity.findViewById(id);
        if(viewobj !=null)
        {
            viewobj.setOnClickListener(this);
        }
        return viewobj;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    
    public void setText(View view,int id,String strMessage)
    {
        TextView txtView = (TextView)view.findViewById(id);
        if(txtView != null)
            txtView.setText(strMessage);
    }

    @Override
    public void onTaskComplete(int taskId, Object object, boolean isError) {

    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }


}
