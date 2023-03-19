package hj.lee.searchblog.controller;


import hj.lee.searchblog.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BlogControllerTest extends BaseTest {

    @Test
    public void searchBlogTest() throws Exception {
        ResultActions actions = mockMvc.perform(get("/"));
        actions.andExpect(status().isOk()).andExpect(content().string("ok"));
    }


}
