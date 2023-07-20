package fr.norsys.einvoice.controllers;

import fr.norsys.einvoice.article.Article;
import fr.norsys.einvoice.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/articles")
public class ArticleController {
    @Autowired
    ArticleService articleService;


    @PostMapping("/save")
    public Article save(@RequestBody Article p) {
        return articleService.save(p);
    }

    @GetMapping("/all")
    public List<Article> findAll() {
        return articleService.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Article> findById(@PathVariable UUID id) {
        return articleService.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Article p) {
        articleService.update(p);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Article p) {
        articleService.delete(p);
    }
}
