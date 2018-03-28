package kz.imaytber.sgq.myapplication.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kz.imaytber.sgq.myapplication.R;
import kz.imaytber.sgq.myapplication.mvp.present.MainPresent;
import kz.imaytber.sgq.myapplication.mvp.present.MainPresentImpl;

public class MainActivity extends AppCompatActivity implements MainView{

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
    public void onClick(){
        mainPresent.onClick(textWrite.getText().toString());
    }

    @Override
    public void setText(String text) {
        textRead.setText(text);
    }
}
