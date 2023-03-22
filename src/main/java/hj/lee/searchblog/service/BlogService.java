package hj.lee.searchblog.service;

import hj.lee.searchblog.dto.req.BlogSearchReq;
import hj.lee.searchblog.dto.res.BlogSearchRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@Service
@Transactional
public class BlogService {
    private final WebClient kakaoClient;

    private final WebClient naverClient;

    private final PopularService popularService;

    public Mono<BlogSearchRes> searchBlog(BlogSearchReq blogSearchReq, Pageable pageable) {
        popularService.add(blogSearchReq.getQuery());
        Mono<BlogSearchRes> blogSearchResMono = kakaoClient.get().exchangeToMono(res -> res.bodyToMono(BlogSearchRes.class)
                .map(blogSearchRes -> {
                    if (res.statusCode().isError()) {
                        return null;
                    }

                    return blogSearchRes;
                }));

        return blogSearchResMono;
    }

}
