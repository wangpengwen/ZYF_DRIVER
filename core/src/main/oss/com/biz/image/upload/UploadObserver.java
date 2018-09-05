package com.biz.image.upload;

public interface UploadObserver {
    void onCompleted(String name);
    void onError(String throwable);
    void onNext(int t);
}
