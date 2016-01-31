package android.template.internal.di.module;

import android.template.internal.di.PerApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * @author Tom Koptel
 */
@Module
public class NetworkModule {
    @PerApplication
    @Provides
    Retrofit providesRetrofit(@Named("api_base_url") String baseUrl) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        return builder.build();
    }

    @PerApplication
    @Provides
    @Named("api_base_url")
    String provideBaseEndpoint() {
        return "http://localhost";
    }
}
