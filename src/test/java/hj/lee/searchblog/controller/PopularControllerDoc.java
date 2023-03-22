package hj.lee.searchblog.controller;

import hj.lee.searchblog.service.BlogService;
import hj.lee.searchblog.service.PopularService;
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
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    @Test
    public void getPopular() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/popular"));

//        resultActions.andExpect(status().isOk())
//                .andDo(
//                        document(
//                                DEFAULT_PATH,
//                                preprocessRequest(prettyPrint()),
//                                preprocessResponse(prettyPrint()),
//                                responseFields(
//                                        fieldWithPath("meta").type(JsonFieldType.OBJECT),
//                                        fieldWithPath("meta.total_count").type(JsonFieldType.NUMBER).description(""),
//                                        fieldWithPath("meta.pageable_count").type(JsonFieldType.NUMBER).description(""),
//                                        fieldWithPath("meta.is_end").type(JsonFieldType.BOOLEAN).description("")
//                                )
//                        )
//                );
    }
}
