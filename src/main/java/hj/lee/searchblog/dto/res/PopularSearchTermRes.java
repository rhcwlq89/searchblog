package hj.lee.searchblog.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PopularSearchTermRes {
    private String term;
    private Long count;
}
