package com.sourabhkarkal.movieapp.service;


import android.util.Log;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by sourabhkarkal on 24/02/17.
 *
 * Rx Java is used to handle asynchronous calls with okHttp lib to handle Rest API
 *
 */

public class RestTemplateExecutor{

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private Subscription subscription;

    public void callServerApi(final DataRequest dataRequest) {
        subscription = getDataObservable(dataRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DataResponse>() {
                    @Override
                    public void onCompleted() {
                        if (subscription != null && !subscription.isUnsubscribed())
                            subscription.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Error", e.getMessage(), e);

                    }

                    @Override
                    public void onNext(DataResponse dataResponse) {
                        // MANAGE DATA
                        dataRequest.getiWebListener().onTaskComplete(dataRequest.getTaskId(), dataResponse, dataResponse.isError());
                    }
                });
    }

    public DataResponse serverApi(final DataRequest dataRequest) {

        final DataResponse dataResponse = new DataResponse();
        final OkHttpClient client = new OkHttpClient();

        Response response = null;
        try {
            switch (dataRequest.getHttpOperation()) {
                case GET:
                    Request requestGet = new Request.Builder()
                            .url(dataRequest.getUrl()) // Api Call
                            //.addHeader()
                            .build();
                    response = client.newCall(requestGet).execute();

                    break;
                // uncomment below code to set add post request ; not required in this app
                /*case POST:
                    RequestBody requestBody = RequestBody.create(JSON, dataRequest.getData() != null ? dataRequest.getData().toString() : "");
                    Request requestPost = new Request.Builder()
                            .url(dataRequest.getUrl()) // Api Call
                            .post(requestBody)
                            .build();
                    response = client.newCall(requestPost).execute();
                    break;*/
            }
            if (response != null) {
                if (response.isSuccessful()) { // Success
                    dataResponse.setError(false);
                    dataResponse.setObject(response.body().string());
                    dataResponse.setResponseCode(response.code());
                } else { // Failure
                    dataResponse.setMessage(response.body().string());
                    dataResponse.setError(true);
                    dataResponse.setResponseCode(response.code());
                }
            }
            response.body().close();
            return dataResponse;
        } catch (Exception e) {
            e.printStackTrace();
            dataResponse.setError(true);
            dataResponse.setMessage(e.getMessage());
            dataResponse.setResponseCode(502);
            return dataResponse;
        }
    }


    public Observable<DataResponse> getDataObservable(final DataRequest dataRequest) {
        return Observable.defer(new Func0<Observable<DataResponse>>() {
            @Override
            public Observable<DataResponse> call() {
                try {
                    return Observable.just(serverApi(dataRequest));
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });

    }

}