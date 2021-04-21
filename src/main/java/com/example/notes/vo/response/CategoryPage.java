package com.example.notes.vo.response;

import com.example.notes.model.MangaInfo;
import org.springframework.data.domain.Page;

public class CategoryPage {
    private String category;
    private Page<MangaInfo> page;

    public CategoryPage(String category, Page<MangaInfo> page) {
        this.category = category;
        this.page = page;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Page<MangaInfo> getPage() {
        return page;
    }

    public void setPage(Page<MangaInfo> page) {
        this.page = page;
    }
}
