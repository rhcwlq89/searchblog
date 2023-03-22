
package hj.lee.searchblog.contorller;

import hj.lee.searchblog.dto.req.SortType;
import hj.lee.searchblog.dto.res.BlogSearchRes;
import hj.lee.searchblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class BlogController {

    private final BlogService blogService;

    @GetMapping(path = "/blog")
    public BlogSearchRes searchBlog(@RequestParam String query,
                                          @RequestParam(name = "sort", defaultValue = "ACCURACY") SortType sortType,
                                          @PageableDefault(page = 1) Pageable pageable) {
        return blogService.searchBlog(query, sortType, pageable).block();
    }
}
