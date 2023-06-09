package hj.lee.searchblog.service;

import hj.lee.searchblog.dto.req.SortType;
import hj.lee.searchblog.dto.res.BlogSearchRes;
import hj.lee.searchblog.dto.res.NaverBlogSearchRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Transactional
public class BlogService {
    private final WebClient kakaoClient;

    private final WebClient naverClient;

    private final PopularService popularService;

    public BlogSearchRes searchBlog(String query, SortType sortType, Pageable pageable) {
        popularService.add(query);
        Mono<BlogSearchRes> blogSearchResMono = kakaoClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/blog")
                        .queryParam("query", query)
                        .queryParam("page", pageable.getPageNumber())
                        .queryParam("size", pageable.getPageSize())
                        .queryParam("sort", sortType.getKakaoSortName())
                        .build())
                .retrieve()
                .bodyToMono(BlogSearchRes.class)
                .onErrorResume(t-> naverClient.get().uri(uriBuilder -> uriBuilder
                        .path("/blog.json")
                        .queryParam("query", query)
                        .queryParam("start", pageable.getPageNumber())
                        .queryParam("display", pageable.getPageSize())
                        .queryParam("sort", sortType.getNaverSortName()).build()
                ).retrieve().bodyToMono(NaverBlogSearchRes.class));

        return blogSearchResMono.block();
    }

}
