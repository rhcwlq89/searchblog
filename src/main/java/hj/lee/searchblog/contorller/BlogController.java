package hj.lee.searchblog.contorller;

import hj.lee.searchblog.service.PopularService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
public class BlogController {

    private final PopularService popularService;

    private final WebClient kakaoClient;

    private final WebClient naverClient;

    @GetMapping(path = "/")
    public ResponseEntity<String> searchBlog() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping(path = "/web")
    public Flux<String> searchBlog2(@RequestParam String query) {
        popularService.add(query);
        return kakaoClient.get().retrieve().bodyToFlux(String.class);
    }
}
