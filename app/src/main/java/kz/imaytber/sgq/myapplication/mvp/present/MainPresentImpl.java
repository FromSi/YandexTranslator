package kz.imaytber.sgq.myapplication.mvp.present;

import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import kz.imaytber.sgq.myapplication.api.TranslateAnswer;
import kz.imaytber.sgq.myapplication.mvp.model.MainModel;
import kz.imaytber.sgq.myapplication.mvp.model.MainModelimpl;
import kz.imaytber.sgq.myapplication.mvp.view.MainView;

public class MainPresentImpl implements MainPresent {

    private final MainView mainView;
    private final MainModel mainModel;

    public MainPresentImpl(MainView mainView) {
        this.mainView = mainView;
        mainModel = new MainModelimpl();
    }

    @Override
    public void onTranslate(String text) {
        if (!TextUtils.isEmpty(text)) {
            Map<String, String> map = new HashMap<>();
            map.put("key", mainModel.getKey());
            map.put("text", text);
            map.put("lang", getLand(mainView.getLand()));

            mainModel.getApi().getAnswer(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TranslateAnswer>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d(getClass().getName(), "onSubscribe");
                        }

                        @Override
                        public void onNext(TranslateAnswer translateAnswer) {
                            List<String> list = translateAnswer.getText();
                            StringBuilder str = new StringBuilder();
                            for (int i = 0; i < list.size(); i++) {
                                str.append(list.get(i));
                            }
                            mainView.setText(str.toString());

                            Log.d(getClass().getName(), "onNext");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(getClass().getName(), "onError");
                        }

                        @Override
                        public void onComplete() {
                            Log.d(getClass().getName(), "onComplete");
                        }
                    });
        } else {
            mainView.isError();
        }
    }

    @Override
    public void onRotation(String land1, String land2) {
        mainView.setRotation(land2, land1);
    }

    private String getLand(String str) {
        String[] land = mainModel.getLand();
        if (land[0].equals(str)) {
            return land[0].toLowerCase() + "-" + land[1].toLowerCase();
        } else if (land[1].equals(str)){
            return land[1].toLowerCase() + "-" + land[0].toLowerCase();
        }
        return land[0].toLowerCase() + "-" + land[1].toLowerCase();
    }
}
