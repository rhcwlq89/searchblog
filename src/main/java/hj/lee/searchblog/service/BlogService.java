package hj.lee.searchblog.service;

import hj.lee.searchblog.dto.req.SortType;
import hj.lee.searchblog.dto.res.BlogSearchRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Mono<BlogSearchRes> searchBlog(String query, SortType sortType, Pageable pageable) {
        popularService.add(query);
        Mono<BlogSearchRes> blogSearchResMono = kakaoClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/blog")
                        .queryParam("query", query)
                        .queryParam("page", pageable.getPageNumber())
                        .queryParam("size", pageable.getPageSize())
                        .queryParam("sort", sortType.getSortName())
                        .build())
                .retrieve()
                .bodyToMono(BlogSearchRes.class);
        return blogSearchResMono;
    }

}
