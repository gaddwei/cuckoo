package cn.cuckoo.note.entity;

import java.io.Serializable;
import java.util.List;

public class TransmitNote implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7358020843372082860L;
	private int total;
	private List<TransmitNoteTranpondBean> tranponds;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<TransmitNoteTranpondBean> getTranponds() {
		return tranponds;
	}
	public void setTranponds(List<TransmitNoteTranpondBean> tranponds) {
		this.tranponds = tranponds;
	}
	
}
