package com.sourabhkarkal.movieapp.service;

import android.app.Service;
import android.content.Context;

import com.sourabhkarkal.movieapp.utils.HttpOperation;


/**
 * Created by sourabhkarkal on 24/02/17.
 */
public class DataRequest {

    private int taskId;
    private iWebListener iWebListener;
    private String url;
    private HttpOperation httpOperation;
    private Context context;
    private String token;
    private Object data;
    private Service service;
    private String extraInfo;


    public DataRequest() {

    }

    public DataRequest(int taskId, String url, Context context, iWebListener iWebListener, HttpOperation httpOperation, String token) {
        this.taskId = taskId;
        this.url = url;
        this.context=context;
        this.iWebListener = iWebListener;
        this.httpOperation = httpOperation;
        this.token=token;
    }


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public iWebListener getiWebListener() {
        return iWebListener;
    }

    public void setiWebListener(iWebListener iWebListener) {
        this.iWebListener = iWebListener;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpOperation getHttpOperation() {
        return httpOperation;
    }

    public void setHttpOperation(HttpOperation httpOperation) {
        this.httpOperation = httpOperation;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
