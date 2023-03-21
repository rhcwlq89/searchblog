package hj.lee.searchblog.service;

import hj.lee.searchblog.entity.PopularSearch;
import hj.lee.searchblog.repository.PopularSearchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class PopularServiceTest {

    @InjectMocks
    private PopularService popularService;

    @Mock
    private PopularSearchRepository popularSearchRepository;

    @Test
    public void test() {
        PopularSearch build = PopularSearch.builder().build();
        given(popularSearchRepository.findByTerm(any())).willReturn(Optional.of(build));
    }
}
