package hj.lee.searchblog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "popular_search")
public class PopularSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "term")
    private String term;

    @Column(name = "count")
    private Long count;

    public void increaseCount() {
        this.count++;
    }
}
