package hj.lee.searchblog.service;

import hj.lee.searchblog.dto.res.PopularSearchTermRes;
import hj.lee.searchblog.entity.PopularSearch;
import hj.lee.searchblog.repository.PopularSearchRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class PopularServiceTest {

    @InjectMocks
    private PopularService popularService;

    @Mock
    private PopularSearchRepository popularSearchRepository;

    @Test
    public void addTest() {
        PopularSearch search = PopularSearch.builder().id(1l).term("111").count(1l).build();
        given(popularSearchRepository.save(any())).willReturn(search);

        PopularSearch add = popularService.add("111");

        assertEquals(1, add.getId());
        assertEquals(1, add.getCount());
        assertEquals("111", add.getTerm());
    }

    @Test
    public void listTest() {
        PopularSearch term1 = PopularSearch.builder().term("111").count(1l).build();
        PopularSearch term2 = PopularSearch.builder().term("222").count(2l).build();

        given(popularSearchRepository.findTop10ByOrderByCount()).willReturn(List.of(term1, term2));

        PopularSearchTermRes list = popularService.list();
        assertEquals(2, list.getTotalCount());
        assertEquals(2, list.getData().size());
    }
}
