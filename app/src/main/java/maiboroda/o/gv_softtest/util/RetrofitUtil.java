package maiboroda.o.gv_softtest.util;

import maiboroda.o.gv_softtest.data.source.remote.TaskApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Just example, there no real api
 */

public class RetrofitUtil {
    private static final String BASE_URL = "";

    private static Retrofit instance;

    public static TaskApi createApi() {
        if (instance == null) {
            synchronized (RetrofitUtil.class) {
                if (instance == null) {
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                    httpClient.addInterceptor(logging);

                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(BASE_URL)
                            .client(httpClient.build())
                            .addConverterFactory(GsonConverterFactory.create());
                    instance = builder.build();
                }
            }
        }
        return instance.create(TaskApi.class);
    }
}
