package hj.lee.searchblog.controller;

import hj.lee.searchblog.dto.req.SortType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PopularControllerDoc {

    private static final String DEFAULT_PATH = "{class-name}/{method-name}";

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void searchBlog() throws Exception {
        MockHttpServletRequestBuilder builder = get("/blog")
                .param("query", "111")
                .param("page", "1")
                .param("size", "10")
                .param("sort", SortType.ACCURACY.name());

        mockMvc.perform(builder);
    }

    @Test
    public void getPopular() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/popular"));

        resultActions.andExpect(status().isOk())
                .andDo(
                        document(
                                DEFAULT_PATH,
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        fieldWithPath("total_count").type(JsonFieldType.NUMBER).description(""),
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description(""),
                                        fieldWithPath("data[].term").type(JsonFieldType.STRING).description(""),
                                        fieldWithPath("data[].search_count").type(JsonFieldType.NUMBER).description("")
                                )
                        )
                );
    }
}
