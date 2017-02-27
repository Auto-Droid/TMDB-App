package com.sourabhkarkal.movieapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sourabhkarkal.movieapp.service.iWebListener;


/**
 * Created by sourabhkarkal on 24/02/17.
 */
public abstract class BaseFragment extends Fragment implements iWebListener,View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public View makeClickable(View view,int id)
    {
        View viewobj = view.findViewById(id);
        if(viewobj !=null)
        {
            viewobj.setOnClickListener(this);
        }
        return view;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onTaskComplete(int taskId, Object object, boolean isError) {}

}
