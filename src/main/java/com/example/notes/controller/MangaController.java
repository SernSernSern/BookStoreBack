package com.example.notes.controller;

import com.example.notes.model.MangaInfo;
import com.example.notes.service.CategoryService;
import com.example.notes.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin()
public class MangaController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    MangaService mangaService;

    /**
     * Show All Categories
     */

    @GetMapping("/product")
    public Page<MangaInfo> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        return mangaService.findAll(request);
    }

    @GetMapping("/product/{productId}")
    public MangaInfo showOne(@PathVariable("productId") String productId) {

        MangaInfo mangaInfo = mangaService.findOne(productId);

        return mangaInfo;
    }

    @PostMapping("/seller/product/new")
    public ResponseEntity create(@Valid @RequestBody MangaInfo product,
                                 BindingResult bindingResult) {
        MangaInfo productIdExists = mangaService.findOne(product.getProductId());
        if (productIdExists != null) {
            bindingResult
                    .rejectValue("productId", "error.product",
                            "There is already a product with the code provided");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return ResponseEntity.ok(mangaService.save(product));
    }

    @PutMapping("/seller/product/{id}/edit")
    public ResponseEntity edit(@PathVariable("id") String productId,
                               @Valid @RequestBody MangaInfo product,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult);
        }
        if (!productId.equals(product.getProductId())) {
            return ResponseEntity.badRequest().body("Id Not Matched");
        }

        return ResponseEntity.ok(mangaService.update(product));
    }

    @DeleteMapping("/seller/product/{id}/delete")
    public ResponseEntity delete(@PathVariable("id") String productId) {
        mangaService.delete(productId);
        return ResponseEntity.ok().build();
    }

}
