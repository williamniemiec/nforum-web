package wniemiec.app.nforum.dto;

/**
 * Responsible for projecting data from a comment.
 */
public class CommentDTO {

	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private Integer id;
	private String content;
	private UserDTO author;
	

	//-------------------------------------------------------------------------
	//		Constructors
	//-------------------------------------------------------------------------
	public CommentDTO() {
	}
	
	public CommentDTO(Integer id, String content, UserDTO author) {
		this.id = id;
		this.content = content;
		this.author = author;
	}


	//-------------------------------------------------------------------------
	//		Getters & Setters
	//-------------------------------------------------------------------------
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
