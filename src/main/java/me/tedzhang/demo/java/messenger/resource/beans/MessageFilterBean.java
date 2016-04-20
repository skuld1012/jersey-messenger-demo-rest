package me.tedzhang.demo.java.messenger.resource.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

//import javax.ws.rs.QueryParam;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageFilterBean {

	//private @QueryParam("year") int year;
	//private @QueryParam("start") int start;
	//private @QueryParam("size") int size;
	
	private int year;
	private int start;
	private int size;
	
	public MessageFilterBean(int year, int start, int size) {
		super();
		this.year = year;
		this.start = start;
		this.size = size;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
