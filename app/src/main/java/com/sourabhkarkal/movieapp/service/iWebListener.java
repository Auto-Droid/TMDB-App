package com.sourabhkarkal.movieapp.service;


import java.io.IOException;

/**
 * Created by sourabhkarkal on 24/02/17.
 */
public interface iWebListener {

    public void onTaskComplete(int taskId, Object object, boolean isError);
}
