package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Article;
import com.example.entity.Comment;
import com.example.form.InsertArticleForm;
import com.example.form.InsertCommentForm;
import com.example.repository.ArticleRepository;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 掲示板の記事やコメントを操作するコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/")
@Transactional
public class ArticleController {
	@Autowired
	private ArticleRepository articleRepository;
	
	/**
	 * 掲示板画面を表示します.
	 * 
	 * @param model       requestスコープを扱うためのオブジェクト
	 * @param articleForm 記事情報の登録時に使用されるフォーム
	 * @param commentForm コメント情報の登録時に使用されるフォーム
	 * @return 掲示板画面
	 */
	@GetMapping("")
	public String index(Model model, InsertArticleForm articleForm, InsertCommentForm commentForm) {

		List<Article> articles = (List<Article>) articleRepository.findAllByOrderByIdDesc();

		// 以下のようにも書けます
		// List<Article> articles = (List<Article>) articleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

		model.addAttribute("articles", articles);

		return "bbs-jpa";
	}

	/**
	 * 記事の追加を行います.
	 * 
	 * @param articleForm 記事情報の登録時に使用されるフォーム
	 * @param result      バリデーションチェックの結果
	 * @param model       requestスコープを扱うためのオブジェクト
	 * @param commentForm コメント情報の登録時に使用されるフォーム
	 * @return 掲示板画面
	 */
	@PostMapping("/insert")
	public String insert(@Validated InsertArticleForm articleForm, BindingResult result, Model model,
			InsertCommentForm commentForm) {
		if (result.hasErrors()) {
			return index(model, articleForm, commentForm);
		}
		Article article = new Article();
		BeanUtils.copyProperties(articleForm, article);

		articleRepository.save(article);

		return "redirect:/";
	}

	/**
	 * 記事にコメントの追加を行います.
	 * 
	 * @param commentForm コメント情報の登録時に使用されるフォーム
	 * @param result      バリデーションチェックの結果
	 * @param model       requestスコープを扱うためのオブジェクト
	 * @param articleForm 記事情報の登録時に使用されるフォーム
	 * @return 掲示板画面
	 */
	@PostMapping("/comment")
	public String insertComment(@Validated InsertCommentForm commentForm, BindingResult result, Model model,
			InsertArticleForm articleForm) {
		if (result.hasErrors()) {
			return index(model, articleForm, commentForm);
		}
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentForm, comment);

		Article article = articleRepository.findById(commentForm.getIntArticleId()).get();

		// ArticleにCommentオブジェクトを追加すれば、自動的にテーブルにインサートされる
		article.addComment(comment);

//		commentRepository.save(comment);
		return "redirect:/";
	}

	/**
	 * 記事と記事に対するコメントの削除を行います.
	 * 
	 * @param id 削除する記事のID
	 * @return 掲示板画面
	 */
	@PostMapping("/delete")
	public String deleteArticle(Integer id) {

		articleRepository.deleteById(id);
		return "redirect:/";
	}
}
