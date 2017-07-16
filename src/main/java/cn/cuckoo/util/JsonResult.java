package cn.cuckoo.util;

import java.io.Serializable;

public class JsonResult<T> 
implements Serializable{

private static final long serialVersionUID = -7330453556127986084L;
public static final int SUCCESS=0;
public static final int ERROR=1;

private int state;
private T data;
private String message;

public JsonResult() {
	
}

public JsonResult(T t){
	state = SUCCESS;
	data = t;
	message="";
}

public JsonResult(Throwable e){
	state = ERROR;
	data=null;
	message = e.getMessage();
}

public JsonResult(int state, 
		Throwable e) {
	this.state = state;
	this.message = e.getMessage();
	this.data = null;
}

public int getState() {
	return state;
}

public void setState(int state) {
	this.state = state;
}

public T getData() {
	return data;
}

public void setData(T data) {
	this.data = data;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}


}