package kz.imaytber.sgq.myapplication.mvp.view;

public interface MainView {
    void setText(String answer);
    void isError();
    void setRotation(String land1, String land2);
    String getLand();
}
