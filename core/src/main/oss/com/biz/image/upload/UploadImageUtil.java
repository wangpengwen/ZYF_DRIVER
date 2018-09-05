package com.biz.image.upload;

import android.os.Environment;
import android.text.TextUtils;

import com.biz.application.BaseApplication;
import com.biz.http.R;
import com.biz.util.FileUtil;
import com.biz.util.LogUtil;
import com.biz.util.MD5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
public class UploadImageUtil {
    public static Observable<String> upload(String src, String bucketName,OssTokenEntity ossTokenEntity,boolean isPrivate) {
        return Observable.create(subscriber -> {
            Observable.combineLatest(Observable.just(src),
                    Observable.just(bucketName),
                    new ImageCompressHandle()).subscribe(
                    imageUploadEntity -> {
                        if (TextUtils.isEmpty(imageUploadEntity.src)) {
                            subscriber.onError(new TextErrorException(BaseApplication.getAppContext().getString(R.string.text_upload_image_handle_error)));
                            return;
                        }
                        ExifInterfaceUtil.SavePictureDegree(imageUploadEntity.src, imageUploadEntity.degree);
                        uploadImage(bucketName, imageUploadEntity.degree, imageUploadEntity.src, ossTokenEntity,new UploadObserver() {
                            @Override
                            public void onCompleted(String name) {
                                deleteFile(imageUploadEntity, name, bucketName,ossTokenEntity,isPrivate);
                                subscriber.onNext(name);
                                subscriber.onCompleted();
                            }

                            @Override
                            public void onError(String throwable) {
                                subscriber.onError(new TextErrorException(throwable));
                            }

                            @Override
                            public void onNext(int t) {

                            }
                        });
                    }, throwable -> {
                        subscriber.onError(throwable);
                    }
            );
        });
    }
    private static void changeFile(String src,int count){
        if(count>30){
            return;
        }
        File file=new File(src);
        LogUtil.print("当前图片地址："+src+" "+file.length());
        if(file.length()==0){
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
            }
            changeFile(src,count+1);
        }
    }


    public static Observable<String> upload(String src, String bucketName,String ak,String sk,boolean isPrivate) {
        return Observable.create(subscriber -> {
            changeFile(src,0);
            Luban.with(BaseApplication.getAppContext())
                    .load(src)                                   // 传人要压缩的图片列表
                    .setCompressListener(new OnCompressListener() { //设置回调
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onSuccess(File file) {
                            if (file==null||!file.exists()) {
                                subscriber.onError(new TextErrorException(BaseApplication.getAppContext().getString(R.string.text_upload_image_handle_error)));
                                return;
                            }
                            LogUtil.print("图片处理完成："+file);
                            uploadImage(bucketName, 0, file.getAbsolutePath(), ak,sk,new UploadObserver() {
                                @Override
                                public void onCompleted(String name) {
                                    ImageCompressEntity imageCompressEntity=new ImageCompressEntity();
                                    imageCompressEntity.isDeleteFile=true;
                                    imageCompressEntity.isDisCache=false;
                                    deleteFile(imageCompressEntity, name, bucketName,null,isPrivate);
                                    subscriber.onNext(name);
                                    subscriber.onCompleted();
                                }

                                @Override
                                public void onError(String throwable) {
                                    subscriber.onError(new TextErrorException(throwable));
                                }

                                @Override
                                public void onNext(int t) {

                                }
                            });
                        }

                        @Override
                        public void onError(Throwable e) {
                            subscriber.onError(e);
                        }
                    }).launch();
        });
    }
    private static void deleteFile(ImageCompressEntity imageUploadEntity, String uploadImage, String type,OssTokenEntity ossTokenEntity,boolean isPrivate) {
        if (imageUploadEntity != null && !TextUtils.isEmpty(imageUploadEntity.src)) {
            if (!TextUtils.isEmpty(uploadImage)) {
                //上传成功 加入缓存
                //保存旋转角度
                if (imageUploadEntity.isDisCache) {
                    if (imageUploadEntity.degree != 0) {
                        ExifInterfaceUtil.SavePictureDegree(imageUploadEntity.src, imageUploadEntity.degree);
                    }
//                    try {
//                        final FileInputStream fileInputStream = new FileInputStream(imageUploadEntity.src);
//                        SimpleCacheKey simpleCacheKey = new SimpleCacheKey(getUriImage(uploadImage, type,ossTokenEntity,isPrivate));
//                        Fresco.getImagePipelineFactory().getMainFileCache().insert(simpleCacheKey, new WriterCallback() {
//                            @Override
//                            public void write(OutputStream outputStream) throws IOException {
//                                byte[] buffer = new byte[1024];
//                                int byteread = 0;
//                                while ((byteread = fileInputStream.read(buffer)) != -1) {
//                                    outputStream.write(buffer, 0, byteread);
//                                }
//                                fileInputStream.close();
//                            }
//                        });
//                    } catch (IOException e) {
//                    }
                }
            }
            if (imageUploadEntity.isDeleteFile) {
                //删除生成的图片
                File file = new File(imageUploadEntity.src);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    public static String getUriImage(String url, String bucketName,OssTokenEntity ossTokenEntity,boolean isPrivate) {
        if (url != null && url.indexOf("http://") > -1) {
            return url;
        } else {
            OssManager ossManager = new OssManager(BaseApplication.getAppContext(),bucketName,ossTokenEntity.accessKeyId,ossTokenEntity.accessKeySecret,ossTokenEntity.securityToken);
            if(isPrivate){
                return ossManager.getPrivateURL(url);
            }
            return ossManager.getPublicURL(url);
        }
    }

    private static void uploadImage(String type, int rotate, String filename, OssTokenEntity ossTokenEntity,UploadObserver observable) {
        File file = new File(filename);
        if (!file.exists()) {
            if (observable != null) {
                observable.onError(BaseApplication.getAppContext().getString(R.string.text_upload_image_file_not_exists));
            }
            return;
        }
        OssManager ossManager = new OssManager(BaseApplication.getAppContext(),type,ossTokenEntity.accessKeyId,ossTokenEntity.accessKeySecret,ossTokenEntity.securityToken);
        ossManager.uploadImage(type, rotate, filename, observable);
    }
    private static void uploadImage(String bucketName, int rotate, String filename, String ak,String sk,UploadObserver observable) {
        File file = new File(filename);
        if (!file.exists()) {
            if (observable != null) {
                observable.onError(BaseApplication.getAppContext().getString(R.string.text_upload_image_file_not_exists));
            }
            return;
        }
        LogUtil.print("开始上传文件:"+file);
        OssManager ossManager = new OssManager(BaseApplication.getAppContext(),bucketName,ak,sk);
        ossManager.uploadImage(bucketName, rotate, filename, observable);
    }
}
