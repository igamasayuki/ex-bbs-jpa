package com.example.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

/**
 * 記事情報を表すエンティティ.
 * 
 * @author igamasayuki
 *
 */
@Entity
@Table(name = "articles")
public class Article {
	/** ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 投稿者名 */
	private String name;
	/** 投稿内容 */
	private String content;
	/** コメントのリスト */
	@OrderBy(value = "id DESC")
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Comment> commentList;

	/**
	 * デフォルトコンストラクタ.
	 */
	public Article() {
	}

	/**
	 * 記事情報を設定するコンストラクタ.
	 * 
	 * @param id          ID
	 * @param name        投稿者名
	 * @param content     投稿内容
	 * @param commentList コメントのリスト
	 */
	public Article(Integer id, String name, String content, List<Comment> commentList) {
		this.id = id;
		this.name = name;
		this.content = content;
		this.commentList = commentList;
	}

    /**
     * コメントを追加するメソッド.<br>
     * commentListに「orphanRemoval = true」をつけているため、
     * Repositoryのsave()メソッドを呼ばなくても、Articleエンティティに
     * 追加されたCommentエンティティは自動的にデータベースに追加されます。
     * 
     * @param comment 追加するコメント
     */
    public void addComment(Comment comment) {
    	comment.setArticle(this);
        commentList.add(comment);
    }

//    /**
//     * コメントを削除するメソッド.<br>
//     * commentListに「orphanRemoval = true」をつけているため、
//     * RepositoryのdeleteById()メソッドを呼ばなくても、Articleエンティティから
//     * 削除されたCommentエンティティは自動的にデータベースからも削除されます。
//     * 
//     * @param comment 削除するコメント
//     */
//    public void removeComment(Comment comment) {
//    	comment.setArticle(null);
//        commentList.remove(comment);
//    }
    
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

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", commentList=" + commentList + "]";
	}

}
