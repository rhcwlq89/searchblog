
package hj.lee.searchblog.contorller;

import hj.lee.searchblog.dto.req.BlogSearchReq;
import hj.lee.searchblog.dto.res.BlogSearchRes;
import hj.lee.searchblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class BlogController {

    private final BlogService blogService;

    @GetMapping(path = "/")
    public Mono<BlogSearchRes> searchBlog(@ModelAttribute BlogSearchReq blogSearchReq,
                                          @PageableDefault Pageable pageable) {
        return blogService.searchBlog(blogSearchReq, pageable);
    }
}
