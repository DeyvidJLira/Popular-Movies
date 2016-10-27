package br.com.deyvidjlira.popularmovies.data.services;

import java.io.IOException;

import br.com.deyvidjlira.popularmovies.BuildConfig;
import br.com.deyvidjlira.popularmovies.util.Constants;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Deyvid on 25/10/2016.
 */
public class MovieClient {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <TService> TService createService(Class<TService> serviceClass) {
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request requestPrimary = chain.request();
                HttpUrl url = requestPrimary.url()
                        .newBuilder()
                        .addQueryParameter(Constants.APP_KEY_PARAM, BuildConfig.MOVIEDB_API_KEY)
                        .build();
                Request requestSecondary = requestPrimary.newBuilder().url(url).build();
                return chain.proceed(requestSecondary);
            }
        });
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return  retrofit.create(serviceClass);
    }
}
