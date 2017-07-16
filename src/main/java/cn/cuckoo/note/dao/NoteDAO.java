package cn.cuckoo.note.dao;

import java.util.List;
import java.util.Map;

import cn.cuckoo.note.entity.CircleNoteBean;
import cn.cuckoo.note.entity.Note;
import cn.cuckoo.note.entity.NoteComment;
import cn.cuckoo.note.entity.NoteCover;
import cn.cuckoo.note.entity.NoteMap;
import cn.cuckoo.note.entity.NoteTranspond;
import cn.cuckoo.note.entity.TransmitNoteTranpondBean;

public interface NoteDAO {
	/**
	 * 发布保存游记
	 * @param note
	 */
	void saveNote(Note note);
	
	
	List<NoteCover> getNoteCoverDate(Map<String , Integer> map);
	
	NoteMap getNoteMapById(String id);
	
	void updateNoteBrowse(String id);
	
	void saveComment(NoteComment comment);


	void delComment(NoteComment comment);


	void saveTranspond(NoteTranspond transpond);
	
	//获取朋友圈动态
	List<CircleNoteBean> getCircleNote(Map<String , String> map);


	int getTranspondCount(String noteId);


	List<TransmitNoteTranpondBean> getNoteTranspond(String noteId);


	int getPraiseByUidAndNoteId(Map<String, String> map);


	void delPraise(Map<String, String> map);


	void addPraise(Map<String, String> map);
}
