package hj.lee.searchblog.controller;


import hj.lee.searchblog.contorller.BlogController;
import hj.lee.searchblog.dto.req.SortType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BlogControllerTest {

    @Autowired
    BlogController blogController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(blogController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void searchBlogOrderByAccuracy() throws Exception {
        ResultActions actions = mockMvc.perform(get("/blog")
                .queryParam("query", "검색어")
                .queryParam("sortType", SortType.ACCURACY.name()));
        actions.andExpect(status().isOk());
    }

    @Test
    public void searchBlogOrderByRecency() throws Exception {
        ResultActions actions = mockMvc.perform(get("/blog")
                .queryParam("query", "검색어")
                .queryParam("sortType", SortType.ACCURACY.name()));
        actions.andExpect(status().isOk());
    }
    @Test
    public void searchBlogDefaultValue() throws Exception {
        ResultActions actions = mockMvc.perform(get("/blog")
                .queryParam("query", "검색어"));
        actions.andExpect(status().isOk());
    }

}
