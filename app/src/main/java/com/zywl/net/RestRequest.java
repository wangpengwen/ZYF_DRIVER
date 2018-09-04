package com.zywl.net;

import com.biz.http.BaseRequest;
import com.biz.http.RestMethodEnum;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Title: RestRequest
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/11/7  14:33
 *
 * @author wangwei
 * @version 1.0
 */
public class RestRequest<T> extends BaseRequest<T> {
    public static final MediaType MEDIA_JSON = MediaType.parse("application/json;");
    public static final MediaType MEDIA_FORM = MediaType.parse("application/x-www-form-urlencoded;");
    private String url;

    public static <T> BaseRequest<T> builder() {
        BaseRequest<T> request = new RestRequest<T>();
        request.https(false);
        request.setDefaultConnectTime();
//        request.addPublicPara("lat", UserModel.getInstance().getLat());
//        request.addPublicPara("lon", UserModel.getInstance().getLon());
//        if (UserModel.getInstance().getUserDepot() != null) {
//            request.addPublicPara("depotCode", UserModel.getInstance().getUserDepot().depotCode);
//        }
//        request.addPublicPara("userToken",UserModel.getInstance().getUserToken());
        return request;
    }


    public RestRequest() {
        super();
    }

    @Override
    public Request getOKHttpRequest() {
        Request request = null;
        if (getRestMethodEnum() == RestMethodEnum.POST) {
            RequestBody requestBody = getRequestBody();

//            if (TextUtils.isEmpty(body)) {
//                requestBody = RequestBody.create(MEDIA_FORM, new byte[0]);
//            } else {
//                requestBody = RequestBody.create(MEDIA_FORM, body);
//            }
            request = new Request.Builder().url(getUrl())
                    .post(requestBody).build();
        } else if (getRestMethodEnum() == RestMethodEnum.GET) {
            request = new Request.Builder().url(getUrl()).get().build();
        }
        return request;
    }

    @Override
    public BaseRequest<T> userId(Object userId) {
        return super.userId(userId);
    }

    @Override
    protected Map<String, Object> getParaPublic() {
        try {
//            if (userId != null && !TextUtils.isEmpty(userId.toString())&& Utils.getLong(userId.toString())>0) {
//                if (paraPublic.containsKey("memberId")) paraPublic.remove("memberId");
//                paraPublic.put("memberId", userId);
//            }
        } catch (Exception e) {
        }
        return paraPublic;
    }

    @Override
    public Observable<T> requestJson() {
        Observable<T> observable = super.requestJson();
        return observable.map(r -> {
//            if (r instanceof ResponseJson) {
//                ResponseJson responseJson= (ResponseJson) r;
//                if(responseJson.status==930){
//                    UserModel.getInstance().loginOut();
//                    EventBus.getDefault().post(new UserSignEvent());
//                }
//            }
            return r;
        });
    }
}
