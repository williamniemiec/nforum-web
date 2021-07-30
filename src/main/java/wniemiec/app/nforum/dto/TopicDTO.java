package wniemiec.app.nforum.dto;

import java.util.ArrayList;
import java.util.List;


/**
 * Responsible for projecting data from a topic.
 */
public class TopicDTO {

	//-------------------------------------------------------------------------
	//		Attributes
	//-------------------------------------------------------------------------
	private Integer id;
	private String title;
	private String content;
	private UserDTO author;
	private List<CommentDTO> comments;
	

	//-------------------------------------------------------------------------
	//		Constructors
	//-------------------------------------------------------------------------
	public TopicDTO() {
		comments = new ArrayList<>();
	}
	
	public TopicDTO(String title, String content) {
		this(null, title, content, (UserDTO) null);
	}
	
	public TopicDTO(Integer id, String title, String content, UserDTO author) {
		this();
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = author;
	}
	
	public TopicDTO(Integer id, String title, String content, String authorId) {
		this();
		this.id = id;
		this.title = title;
		this.content = content;
		this.author = new UserDTO(authorId);
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}
}
