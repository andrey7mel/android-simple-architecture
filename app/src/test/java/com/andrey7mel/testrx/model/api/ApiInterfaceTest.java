package com.andrey7mel.testrx.model.api;

import com.andrey7mel.testrx.model.dto.RepositoryDTO;
import com.andrey7mel.testrx.other.BaseTest;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observer;

public class ApiInterfaceTest extends BaseTest {


    MockWebServer server;
    ApiInterface apiInterface;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        final Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

                if (request.getPath().equals("users/test/repos")) {
                    return new MockResponse().setResponseCode(200).setBody("test");
                } else if (request.getPath().equals("v1/check/version/")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody("version=9");
                } else if (request.getPath().equals("/v1/profile/info")) {
                    return new MockResponse().setResponseCode(200).setBody("{\\\"info\\\":{\\\"name\":\"Lucas Albuquerque\",\"age\":\"21\",\"gender\":\"male\"}}");
                }
                return new MockResponse().setResponseCode(404);
            }
        };
        server.setDispatcher(dispatcher);
        HttpUrl baseUrl = server.url("/v1/chat/");

        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient();
        httpClient.interceptors().add(interceptor);

        builder.client(httpClient);

        apiInterface = builder.build().create(ApiInterface.class);
    }


    @Test
    public void testGetRepositories() throws Exception {
        System.out.print("AAA");
//        apiInterface.getRepositories("a").toBlocking().forEach(new Action1<List<RepositoryDTO>>() {
//            @Override
//            public void call(List<RepositoryDTO> repositoryDTOs) {
//                System.out.println("BBB");
//
//            }
//        });

        apiInterface.getRepositories("test").subscribe(new Observer<List<RepositoryDTO>>() {
            @Override
            public void onCompleted() {
                testUtils.log("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                testUtils.log("onError");
            }

            @Override
            public void onNext(List<RepositoryDTO> list) {
                testUtils.log("onNext");

            }
        });
        ;
    }

    public void testGetContributors() {
    }

    public void testGetBranches() {

    }
}