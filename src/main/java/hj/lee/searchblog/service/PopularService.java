package hj.lee.searchblog.service;

import hj.lee.searchblog.dto.res.PopularSearchTermRes;
import hj.lee.searchblog.entity.PopularSearch;
import hj.lee.searchblog.repository.PopularSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class PopularService {

    private final PopularSearchRepository popularSearchRepository;

    public PopularSearch add(String query) {
        PopularSearch popularSearch = popularSearchRepository.findByTerm(query)
                .orElse(PopularSearch.builder().term(query).count(0l).build());
        popularSearch.increaseCount();
        return popularSearchRepository.save(popularSearch);
    }

    public PopularSearchTermRes list() {
        List<PopularSearch> list = popularSearchRepository.findTop10ByOrderByCount();
        List<PopularSearchTermRes.PopularSearchTerm> collect = list.stream()
                .map(t -> new PopularSearchTermRes.PopularSearchTerm(t.getTerm(), t.getCount()))
                .collect(Collectors.toList());

        return new PopularSearchTermRes(collect.size(), collect);
    }


}
