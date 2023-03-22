package hj.lee.searchblog.controller;

import hj.lee.searchblog.dto.req.SortType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlogControllerDoc {

    private static final String DEFAULT_PATH = "{class-name}/{method-name}";

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void searchBlog() throws Exception {
        MockHttpServletRequestBuilder builder = get("/blog")
                .param("query", "111")
                .param("page", "1")
                .param("size", "10")
                .param("sort", SortType.ACCURACY.name());

        ResultActions resultActions = mockMvc.perform(builder);

        resultActions.andExpect(status().isOk())
                .andDo(
                        document(
                                DEFAULT_PATH,
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                queryParameters(
                                    parameterWithName("query").description("검색어"),
                                    parameterWithName("page").description("페이지 번호"),
                                    parameterWithName("size").description("페이지 사이즈"),
                                    parameterWithName("sort").description("정렬")
                                ),
                                responseFields(
                                        fieldWithPath("meta").type(JsonFieldType.OBJECT).description(""),
                                        fieldWithPath("meta.total_count").type(JsonFieldType.NUMBER).description(""),
                                        fieldWithPath("meta.pageable_count").type(JsonFieldType.NUMBER).description(""),
                                        fieldWithPath("meta.is_end").type(JsonFieldType.BOOLEAN).description(""),
                                        fieldWithPath("documents").type(JsonFieldType.ARRAY).description(""),
                                        fieldWithPath("documents[].title").type(JsonFieldType.STRING).description(""),
                                        fieldWithPath("documents[].contents").type(JsonFieldType.STRING).description(""),
                                        fieldWithPath("documents[].url").type(JsonFieldType.STRING).description(""),
                                        fieldWithPath("documents[].blogname").type(JsonFieldType.STRING).description(""),
                                        fieldWithPath("documents[].thumbnail").type(JsonFieldType.STRING).description(""),
                                        fieldWithPath("documents[].dateTime").description("")
                                )
                        )
                );

    }
}
