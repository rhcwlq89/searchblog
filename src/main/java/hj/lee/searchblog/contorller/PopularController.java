package hj.lee.searchblog.contorller;

import hj.lee.searchblog.entity.PopularSearch;
import hj.lee.searchblog.service.PopularService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PopularController {

    private final PopularService popularService;

    @GetMapping(path = "/popular")
    public ResponseEntity<String> getPopular() {
        List<PopularSearch> list = popularService.list();
        list.forEach(popularSearch -> {
            System.out.println("popularSearch.getId() = " + popularSearch.getId());
            System.out.println("popularSearch.getTerm() = " + popularSearch.getTerm());
            System.out.println("popularSearch.getCount() = " + popularSearch.getCount());
        });

        return ResponseEntity.ok("ok");
    }
}
