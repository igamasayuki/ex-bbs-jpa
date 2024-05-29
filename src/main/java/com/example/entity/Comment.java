package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * コメント情報を表すエンティティ.
 * 
 * @author igamasayuki
 *
 */
@Entity
@Table(name = "comments")
public class Comment {
	/** ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 名前 */
	private String name;
	/** コメント */
	private String content;
	/** 記事ID */
	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;

	/**
	 * デフォルトコンストラクタ.
	 */
	public Comment() {
	}

	/**
	 * コメント情報を設定するコンストラクタ.
	 * 
	 * @param id      ID
	 * @param name    名前
	 * @param content コメント
	 * @param article 対応する記事情報
	 */
	public Comment(Integer id, String name, String content, Article article) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.article = article;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "CommentEntity [id=" + id + ", name=" + name + ", content=" + content + ", article=" + article + "]";
	}

}
