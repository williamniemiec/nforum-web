package wniemiec.app.nforum.dto;


/**
 * Responsible for projecting data from a new comment.
 */
public class CommentNewDTO {

	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private String content;
	private String authorId;
	private Integer topicId;
	

	//-------------------------------------------------------------------------
	//		Constructors
	//-------------------------------------------------------------------------
	public CommentNewDTO() {
	}
	
	public CommentNewDTO(String content, Integer topicId) {
		this.content = content;
		this.topicId = topicId;
	}
	
	public CommentNewDTO(String content, Integer topicId, String authorId) {
		this.content = content;
		this.topicId = topicId;
		this.authorId = authorId;
	}
	

	//-------------------------------------------------------------------------
	//		Getters & Setters
	//-------------------------------------------------------------------------
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
