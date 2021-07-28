package wniemiec.app.nforum.dto;

public class CommentDTO {

	private Integer id;
	private String content;
	private UserDTO author;
	
	public CommentDTO() {
	}
	
	public CommentDTO(Integer id, String content, UserDTO author) {
		this.id = id;
		this.content = content;
		this.author = author;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserDTO getAuthor() {
		return author;
	}

	public void setAuthor(UserDTO author) {
		this.author = author;
	}
}
