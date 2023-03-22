package hj.lee.searchblog.dto.res;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class NaverBlogSearchRes extends BlogSearchRes {

    private Integer total;

    private Integer start;

    private Integer display;

    private List<Item> items;

    public NaverBlogSearchRes() {
        this.meta = new Meta();
        this.documents = new ArrayList<>();
    }

    public void setTotal(Integer total) {
        this.meta.setTotalCount(total);
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public void setItems(List<Item> items) {
        this.documents = items.stream().map(Item::toDocument).collect(Collectors.toList());
    }

    @Setter
    public static class Item {
        private String title;
        private String link;
        private String description;
        private String bloggerlink;
        private String bloggername;
        private String postdate;

        public Document toDocument() {
            Document document = new Document();
            document.setTitle(title);
            document.setUrl(link);
            document.setContents(description);
            document.setBlogname(bloggername);
            document.setDatetime(postdate);

            return document;
        }
    }
}
