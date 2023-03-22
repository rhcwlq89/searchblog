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
                .param("size", "1")
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
                                    parameterWithName("page").description("페이지 번호 : 기본값 1").optional(),
                                    parameterWithName("size").description("페이지 사이즈 : 기본값 10").optional(),
                                    parameterWithName("sort").description("정렬 - 내림차순 (정확도순=ACCURACY, 최신순=RECENCY) : 기본값 ACCURACY")
                                            .optional()
                                ),
                                responseFields(
                                        fieldWithPath("meta").type(JsonFieldType.OBJECT).description(""),
                                        fieldWithPath("meta.total_count").type(JsonFieldType.NUMBER).description("검색된 문서 수"),
                                        fieldWithPath("meta.pageable_count").type(JsonFieldType.NUMBER).description("total_count 중 노출 가능 문서 수"),
                                        fieldWithPath("meta.is_end").type(JsonFieldType.BOOLEAN).description("현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음"),
                                        fieldWithPath("documents").type(JsonFieldType.ARRAY).description(""),
                                        fieldWithPath("documents[].title").type(JsonFieldType.STRING).description("문서 제목"),
                                        fieldWithPath("documents[].contents").type(JsonFieldType.STRING).description("문서 본문 중 일부"),
                                        fieldWithPath("documents[].url").type(JsonFieldType.STRING).description("문서 URL"),
                                        fieldWithPath("documents[].blogname").type(JsonFieldType.STRING).description("블로그명"),
                                        fieldWithPath("documents[].thumbnail").type(JsonFieldType.STRING).description("썸네일"),
                                        fieldWithPath("documents[].datetime").description("문서 글 작성시간, ISO 8601\n" +
                                                "[YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]")
                                )
                        )
                );

    }
}
