package kz.imaytber.sgq.myapplication.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kz.imaytber.sgq.myapplication.R;
import kz.imaytber.sgq.myapplication.mvp.present.MainPresent;
import kz.imaytber.sgq.myapplication.mvp.present.MainPresentImpl;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.land1)
    TextView land1;

    @BindView(R.id.land2)
    TextView land2;

    @BindView(R.id.textRead)
    EditText textRead;

    @BindView(R.id.textWrite)
    EditText textWrite;

    private MainPresent mainPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresent = new MainPresentImpl(this);
    }

    @OnClick(R.id.button)
    public void onTranslate() {
        mainPresent.onTranslate(textWrite.getText().toString());
    }

    @OnClick(R.id.rotation)
    public void onRotation(){
        mainPresent.onRotation(land1.getText().toString(),
                land2.getText().toString());
    }

    @Override
    public void setText(String text) {
        textRead.setText(text);
    }

    @Override
    public void isError() {
        textWrite.setError(getResources().getString(R.string.error));
    }

    @Override
    public void setRotation(String land1, String land2) {
        this.land1.setText(land1);
        this.land2.setText(land2);
    }

    @Override
    public String getLand() {
        return land1.getText().toString();
    }
}
