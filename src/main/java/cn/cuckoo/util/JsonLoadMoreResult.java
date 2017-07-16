package cn.cuckoo.util;

import java.util.List;

public class JsonLoadMoreResult<E> {
	private long nextStart;
	private List<E> data;
	public JsonLoadMoreResult() {
	}
	public JsonLoadMoreResult(long nextStart,List<E> data) {
	}
	public long getNextStart() {
		return nextStart;
	}
	public void setNextStart(long nextStart) {
		this.nextStart = nextStart;
	}
	public List<E> getData() {
		return data;
	}
	public void setData(List<E> data) {
		this.data = data;
	}
}
