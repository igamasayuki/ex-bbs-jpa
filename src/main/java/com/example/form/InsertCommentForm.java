package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * コメント情報の登録時に使用されるフォーム.
 * 
 * @author moemi.seki
 *
 */
public class InsertCommentForm {
	/** 名前 */
	@NotBlank(message = "名前を入力してください")
	@Size(max = 50, message = "名前は50字以内で入力してください")
	private String name;
	/** コメント */
	@NotBlank(message = "コメントを入力してください")
	private String content;
	/** 記事ID */
	private String articleId;

	/**
	 * 整数型の記事IDを取得します.
	 * 
	 * @return Integer型の記事ID
	 */
	public Integer getIntArticleId() {
		return Integer.parseInt(articleId);
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

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	@Override
	public String toString() {
		return "InsertCommentForm [name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}

}
