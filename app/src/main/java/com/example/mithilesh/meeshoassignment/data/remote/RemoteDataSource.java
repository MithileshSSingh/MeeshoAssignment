package com.example.mithilesh.meeshoassignment.data.remote;

import android.content.Context;

import com.example.mithilesh.meeshoassignment.data.DataSource;
import com.example.mithilesh.meeshoassignment.mvp.model.BeanPullRequest;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RemoteDataSource implements DataSource {


    private static RemoteDataSource INSTANCE = null;
    private static Retrofit retrofit;
    private static ApiClient.APICalls apiCalls;
    private Context mContext;

    private RemoteDataSource() {

    }

    private RemoteDataSource(Context context) {
        mContext = context;
    }

    public static RemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(context);
        }

        return INSTANCE;
    }

    public synchronized ApiClient.APICalls getApiClient() {
        if (apiCalls == null) {
            apiCalls = getRetrofitInstance().create(ApiClient.APICalls.class);
        }

        return apiCalls;
    }

    private synchronized Retrofit getRetrofitInstance() {
        //XXX
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient();

            client.setReadTimeout(10, TimeUnit.SECONDS);
            client.setWriteTimeout(10, TimeUnit.SECONDS);
            client.setConnectTimeout(10, TimeUnit.SECONDS);

            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiClient.ServiceType.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(
                            new ErrorHandlingExecutorCallAdapterFactory(
                                    new ErrorHandlingExecutorCallAdapterFactory.MainThreadExecutor()))
                    .build();
        }

        return retrofit;
    }

    private ApiError getApiError(Throwable t) {
        RetrofitException exception = (RetrofitException) t;
        ApiError apiError = new ApiError();

        if (exception.getErrorResponse() != null) {
            ApiErrorResponse apiErrorResponse = exception.getErrorResponse();
            if (apiErrorResponse != null) {

                apiError.errorCode = apiErrorResponse.getStatusCode();
                apiError.msgError = apiErrorResponse.getMessage();

            } else {

                apiError.errorCode = ApiClient.HttpErrorCode.NO_CODE;
                apiError.msgError = "Error";

            }
        } else {

            apiError.errorCode = ApiClient.HttpErrorCode.NO_CODE;
            apiError.msgError = "Error";

        }

        return apiError;
    }


    @Override
    public void loadAllPullRequest(
            String ownerName,
            String repoName,
            final GetPullRequestDetail callBack) {

        if (!NetworkUtils.isNetworkAvailable(mContext)) {

            callBack.failed(
                    ApiClient.HttpErrorCode.NO_CODE,
                    "No Network"
            );
            return;
        }


        final Call<ArrayList<BeanPullRequest>> call = getApiClient().getAllPullRequests(ownerName, repoName);

        call.enqueue(new Callback<ArrayList<BeanPullRequest>>() {
            @Override
            public void onResponse(retrofit.Response<ArrayList<BeanPullRequest>> response, Retrofit retrofit) {

                ArrayList<BeanPullRequest> list = response.body();

                if (list == null || list.size() == 0) {
                    callBack.failed(ApiClient.HttpErrorCode.NO_CODE, "NO DATA");
                } else {
                    callBack.success(list);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                ApiError apiError = getApiError(t);
                callBack.failed(apiError.errorCode, apiError.msgError);
            }
        });

    }

    private class ApiError {
        public int errorCode;
        public String msgError;

        @Override
        public String toString() {
            return "ApiError{" +
                    "errorCode=" + errorCode +
                    ", msgError=" + msgError +
                    '}';
        }
    }
}
