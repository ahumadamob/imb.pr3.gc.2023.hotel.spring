package imb.pr3.hotel.controllers;

import java.util.List;

public class APIResponse <T>{
	private int status;
	private List<String> messages;
	private T data;
	
	public APIResponse(int status, List<String> messages, Object buscareservacion) {
		super();
		this.status = status;
		this.messages = messages;
		this.data = (T) buscareservacion;
	}

	public APIResponse(int value, Object messages2, Object buscareservacion) {
		// TODO Auto-generated constructor stub
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	

}
     