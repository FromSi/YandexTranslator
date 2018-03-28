package kz.imaytber.sgq.myapplication.mvp.model;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import kz.imaytber.sgq.myapplication.api.ServiceApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModelimpl implements MainModel {

    private final String URL_YA = "https://translate.yandex.net";
    private final String KEY_YA = "trnsl.1.1.20170926T151123Z.95fb7d8f8a77ebdf.1ef1587b47531748bfa932fb3e5c787df5c6d8d8";
    private Retrofit retrofit;

    public MainModelimpl() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_YA)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Override
    public String getKey() {
        return KEY_YA;
    }

    @Override
    public ServiceApi getApi() {
        return retrofit.create(ServiceApi.class);
    }
}
