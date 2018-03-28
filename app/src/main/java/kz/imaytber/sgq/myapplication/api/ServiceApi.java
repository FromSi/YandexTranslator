package kz.imaytber.sgq.myapplication.api;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ServiceApi {
    @GET("/api/v1.5/tr.json/translate")
    Observable<TranslateAnswer> getAnswer(@QueryMap Map<String, String> map);
}
