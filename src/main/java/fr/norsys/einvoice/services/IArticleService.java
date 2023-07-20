package fr.norsys.einvoice.services;

import fr.norsys.einvoice.article.Article;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IArticleService {
    Article save(Article o);
    List<Article> findAll();
    Optional<Article> findById(UUID id);
    void update(Article o);
    void delete(Article o);
}
