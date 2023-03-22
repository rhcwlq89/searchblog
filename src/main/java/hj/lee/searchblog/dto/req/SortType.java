package hj.lee.searchblog.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortType {
    ACCURACY("accuracy", "sim", "정확도순"),
    RECENCY("recency", "date", "최신순");

    private String kakaoSortName;
    private String naverSortName;
    private String description;
}
