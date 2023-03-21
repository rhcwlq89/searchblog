package hj.lee.searchblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    private static String NAVER_CLIENT_ID = "Vb5hWenn0I8FHWFY_Cuq";
    private static String NAVER_CLIENT_SECRET = "ghMD3tL_8D";

    @Bean
    public WebClient kakaoClient() {
        return WebClient
                .builder()
                .baseUrl("https://dapi.kakao.com/v2/search/blog?sort=accuracy&page=1&size=10&query=11")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "KakaoAK 0524ff4c49b811c947700a679659112c")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public WebClient naverClient() {
        return WebClient
                .builder()
                .baseUrl("https://openapi.naver.com/v1/search/blog.xml?query=%EB%A6%AC%EB%B7%B0&display=10&start=1&sort=sim")
                .defaultHeader("X-Naver-Client-Id", NAVER_CLIENT_ID)
                .defaultHeader("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
                .build();
    }
}
