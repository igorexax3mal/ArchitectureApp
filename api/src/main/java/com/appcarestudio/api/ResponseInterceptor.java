package com.appcarestudio.api;

import android.support.annotation.NonNull;



import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ResponseInterceptor implements Interceptor {

    private boolean isAuthorized;

    public ResponseInterceptor(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
    /*    if (isAuthorized) {
            if (response.code() == 401) {
                handleUnathorizedResponse();
            }
        }*/

        return response;
    }

    private void handleUnathorizedResponse() {

     /*   if (AppPrefs.getInstance().isSigned()) {
            //TODO make relogin - refresh - request Token
        } else {
           // Toast.makeText(App.getContext(), "Вы должны быть авторизованы, чтобы воспользоваться этой функцией", Toast.LENGTH_SHORT).show();
        }*/

    }


}
