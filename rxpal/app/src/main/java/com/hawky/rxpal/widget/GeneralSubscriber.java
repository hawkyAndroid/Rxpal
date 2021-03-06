package com.hawky.rxpal.widget;

import android.content.Context;

import com.google.gson.Gson;
import com.hawky.rxpal.internal.subscriber.BaseSubscriber;


/**
 * 由于服务器返回的数据格式不确定，所以这里内置了一个Subscriber用于显示请求加载框并抽象出来以供onNext()解析。
 * <p>
 * 上层只要继承GeneralSubscriber即可，也可以自定义Subscriber(继承BaseSubscriber)
 *
 * @author [*昨日重现*] lhy_ycu@163.com
 * @since version 1.1
 */
public abstract class GeneralSubscriber extends BaseSubscriber<String> implements LoadingDialog.ProgressListener {
    protected Context context;
    protected CallBack callBack;
    protected LoadingDialog loadingDialog = null;

    /**
     * 含加载框
     *
     * @param context    上下文
     * @param cancelable 是否可取消加载框
     * @param callBack   回调接口
     */
    public GeneralSubscriber(Context context, boolean cancelable, CallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        this.loadingDialog = new LoadingDialog(context, cancelable, this);
    }

    /**
     * 不含加载框
     *
     * @param context  上下文
     * @param callBack 回调接口
     */
    public GeneralSubscriber(Context context, CallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    @Override
    public void onStart() {
        if (loadingDialog != null) {
            loadingDialog.show();
        }
    }

    @Override
    public void onCompleted() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    @Override
    public void onError(Throwable e, int code) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    @Override
    public void onCancelProgress() {
//        if (!isUnsubscribed()) {
//            unsubscribe();
//        }
    }

    /**
     * 对外提供的请求回调
     */
    public interface CallBack {

        /**
         * 请求成功
         *
         * @param gson gson对象
         * @param json json串
         */
        void onSuccess(Gson gson, String json);

        /**
         * 请求失败
         *
         * @param code  错误码
         * @param error 错误信息
         */
        void onFailure(int code, String error);
    }

}
