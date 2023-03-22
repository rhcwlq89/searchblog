package hj.lee.searchblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    private static String NAVER_CLIENT_ID = "RuEh8JFv8roymXUO5uQX";
    private static String NAVER_CLIENT_SECRET = "8hnuy3FWXi";

    @Bean
    public WebClient kakaoClient() {
        return WebClient
                .builder()
                .baseUrl("https://dapi.kakao.com/v2/search1")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "KakaoAK 0524ff4c49b811c947700a679659112c")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public WebClient naverClient() {
        return WebClient
                .builder()
                .baseUrl("https://openapi.naver.com/v1/search")
                .defaultHeader("X-Naver-Client-Id", NAVER_CLIENT_ID)
                .defaultHeader("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
                .build();
    }
}
