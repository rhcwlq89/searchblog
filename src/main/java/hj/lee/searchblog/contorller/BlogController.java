package hj.lee.searchblog.contorller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

    @GetMapping(path = "/")
    public ResponseEntity<String> searchBlog() {

        return ResponseEntity.ok("ok");
    }
}
