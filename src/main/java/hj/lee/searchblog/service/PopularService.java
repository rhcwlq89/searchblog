package hj.lee.searchblog.service;

import hj.lee.searchblog.entity.PopularSearch;
import hj.lee.searchblog.repository.PopularSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PopularService {

    private final PopularSearchRepository popularSearchRepository;

    public void add(String query) {
        PopularSearch popularSearch = popularSearchRepository.findByTerm(query)
                .orElse(PopularSearch.builder().term(query).count(0l).build());
        popularSearch.increaseCount();
        popularSearchRepository.save(popularSearch);
    }

    public List<PopularSearch> list() {
        return popularSearchRepository.findTop10ByOrderByCount();
    }


}
