package hj.lee.searchblog.repository;

import hj.lee.searchblog.entity.PopularSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PopularSearchRepository extends JpaRepository<PopularSearch, Long> {
    Optional<PopularSearch> findByTerm(String term);

    List<PopularSearch> findTop10ByOrderByCount();
}
