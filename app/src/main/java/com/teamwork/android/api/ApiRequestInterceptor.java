package com.teamwork.android.api;

import android.util.Base64;

import retrofit.RequestInterceptor;

public class ApiRequestInterceptor implements RequestInterceptor {

    @Override
    public void intercept(RequestFacade request) {

        addAuthorizationHeader(request);
    }

    private void addAuthorizationHeader(RequestFacade request) {

        final String authorizationValue = buildBasicAuthorization();
        request.addHeader("Authorization", authorizationValue);
    }

    private String buildBasicAuthorization() {

        final String userAndPassword = ApiConstants.apiKey + ":" + "xxx";
        return "Basic " + Base64.encodeToString(userAndPassword.getBytes(), Base64.NO_WRAP);
    }
}
