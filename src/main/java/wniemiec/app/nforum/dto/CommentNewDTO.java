package wniemiec.app.nforum.dto;

public class CommentNewDTO {

	private String content;
	private String authorId;
	private Integer topicId;
	
	public CommentNewDTO() {
	}
	
	public CommentNewDTO(String content, Integer topicId) {
		this.content = content;
		this.topicId = topicId;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthor(String authorId) {
		this.authorId = authorId;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
}
