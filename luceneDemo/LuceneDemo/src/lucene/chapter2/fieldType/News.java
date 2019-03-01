package lucene.chapter2.fieldType;

public class News {
	private int id;
	private String title;
	private String content;
	private int reply;
	public News(){}
	public News(int id, String title, String content, int reply){
		this.setId(id);
		this.setTitle(title);
		this.setContent(content);
		this.setReply(reply);
	}
	public int getReply() {
		return reply;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
