package logic;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

// 빈클래스로 만들어 유효성 검증을 함
public class Board {
	private int num;
	@NotEmpty(message = "글쓴이를 입력하세요")
	private String name;
	@NotEmpty(message = "비밀번호를 입력하세요")
	private String pass;
	@NotEmpty(message = "제목을 입력하세요")
	private String subject;
	@NotEmpty(message = "내용을 입력하세요")
	private String content;
	private int readcnt;
	private String fileurl;
	private Date regdate;
	private int ref;
	private int reflevel;
	private int refstep;
	private MultipartFile file1;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}

	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getReflevel() {
		return reflevel;
	}

	public void setReflevel(int reflevel) {
		this.reflevel = reflevel;
	}

	public int getRefstep() {
		return refstep;
	}

	public void setRefstep(int refstep) {
		this.refstep = refstep;
	}

	public MultipartFile getFile1() {
		return file1;
	}

	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}

	@Override
	public String toString() {
		return "Board [num=" + num + ", name=" + name + ", pass=" + pass + ", subject=" + subject + ", content="
				+ content + ", readcnt=" + readcnt + ", fileurl=" + fileurl + ", regdate=" + regdate + ", ref=" + ref
				+ ", reflevel=" + reflevel + ", refstep=" + refstep + ", file1=" + file1 + "]";
	}
	
}
