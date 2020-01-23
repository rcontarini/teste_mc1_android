package com.rcontarini.teste_mc1.ui.base;

public interface BasePresenterApp<V extends BaseViewApp> {

    void attachView(V mvpView);

    void detachView();
}
