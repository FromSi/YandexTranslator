package kz.imaytber.sgq.myapplication.mvp.model;

import kz.imaytber.sgq.myapplication.api.ServiceApi;

public interface MainModel {
    String getKey();
    String[] getLand();
    ServiceApi getApi();
}
