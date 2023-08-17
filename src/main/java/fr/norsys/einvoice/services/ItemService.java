package fr.norsys.einvoice.services;

import fr.norsys.einvoice.article.Article;
import fr.norsys.einvoice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService implements IArticleService{
    @Autowired
    public ItemRepository articleRepository;

    @Override
    public Article save(Article p) {
        return articleRepository.save(p);
    }
    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }
    public Optional<Article> findById(UUID id) {
        return articleRepository.findById(id);
    }
    @Override
    public void update(Article p) {
        articleRepository.save(p);
    }

    public void delete(Article p) {
        articleRepository.delete(p);

    }
}
