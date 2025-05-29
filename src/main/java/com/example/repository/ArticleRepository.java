package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Article;

/**
 * articlesテーブルを操作するリポジトリ.
 * 
 * @author igamasayuki
 *
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
	/**
	 * 記事を全件検索します.
	 * 
	 * @return 記事一覧(IDの降順)
	 */
	List<Article> findAllByOrderByIdDesc();

}
