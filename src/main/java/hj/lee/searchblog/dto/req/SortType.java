package hj.lee.searchblog.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum SortType {
    ACCURACY("accuracy", List.of("kakao"), "정확도순"),
    RECENCY("recency", List.of("kakao"), "최신순");

    private String sortName;
    private List<String> target;
    private String description;
}
