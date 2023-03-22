package hj.lee.searchblog.contorller;

import hj.lee.searchblog.dto.res.PopularSearchTermRes;
import hj.lee.searchblog.service.PopularService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PopularController {

    private final PopularService popularService;

    @GetMapping(path = "/popular")
    public PopularSearchTermRes getPopular() {
        return popularService.list();
    }
}
