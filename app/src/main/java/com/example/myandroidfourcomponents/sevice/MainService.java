package com.example.myandroidfourcomponents.sevice;

class MainService extends BaseService {

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread().start();
    }
}
