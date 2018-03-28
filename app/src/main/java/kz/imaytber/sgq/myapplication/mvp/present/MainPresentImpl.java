package kz.imaytber.sgq.myapplication.mvp.present;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void onClick(String text) {
        Map<String, String> map = new HashMap<>();
        map.put("key", mainModel.getKey());
        map.put("text", text);
        map.put("lang", "en-ru");
        mainModel.getApi().getAnswer(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TranslateAnswer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TranslateAnswer translateAnswer) {
                        List<String> list = translateAnswer.getText();
                        StringBuilder str = new StringBuilder();
                        for (int i = 0; i < list.size(); i++) {
                            str.append(list.get(i));
                        }
                        mainView.setText(str.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
