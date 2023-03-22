package hj.lee.searchblog.controller;

import hj.lee.searchblog.service.BlogService;
import hj.lee.searchblog.service.PopularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PopularControllerDoc {

    private static final String DEFAULT_PATH = "{class-name}/{method-name}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BlogService blogService;

    @Autowired
    private PopularService popularService;


}
