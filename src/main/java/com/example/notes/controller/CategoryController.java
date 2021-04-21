package com.example.notes.controller;
import com.example.notes.model.MangaCategory;
import com.example.notes.model.MangaInfo;
import com.example.notes.service.CategoryService;
import com.example.notes.service.MangaService;
import com.example.notes.vo.response.CategoryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    MangaService mangaService;


    /**
     * Show products in category
     *
     * @param categoryType
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/category/{type}")
    public CategoryPage showOne(@PathVariable("type") Integer categoryType,
                                @RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "size", defaultValue = "3") Integer size) {

        MangaCategory cat = categoryService.findByCategoryType(categoryType);
        PageRequest request = PageRequest.of(page - 1, size);
        Page<MangaInfo> mangaInCategory = mangaService.findAllInCategory(categoryType, request);
        var tmp = new CategoryPage("", mangaInCategory);
        tmp.setCategory(cat.getCategoryName());
        return tmp;
    }
}

