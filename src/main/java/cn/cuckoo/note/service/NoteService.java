package cn.cuckoo.note.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.cuckoo.core.service.GeneralException;
import cn.cuckoo.homepage.service.HomePageException;
import cn.cuckoo.note.dao.NoteDAO;
import cn.cuckoo.note.entity.Note;
import cn.cuckoo.note.entity.NoteComment;
import cn.cuckoo.note.entity.NoteCover;
import cn.cuckoo.note.entity.NoteMap;
import cn.cuckoo.note.entity.NoteTranspond;
import cn.cuckoo.note.entity.TransmitNote;
import cn.cuckoo.note.entity.TransmitNoteTranpondBean;
import cn.cuckoo.util.Util;

@Service
public class NoteService {
	@Resource
	private NoteDAO dao;
	
	public boolean publisNote(String title, String siteId, String imgPath, String content, String root, HttpServletRequest req){
		if(!Util.isNotNull(title)){
			throw new GeneralException("标题为空");
		}
		if(!Util.isNotNull(imgPath)){
			throw new GeneralException("封面图片路径为空");
		}
		if(!Util.isNotNull(content)){
			throw new GeneralException("游记内容为空");
		}
		if(!Util.isNotNull(siteId)){
			throw new GeneralException("位置为空");
		}
		if(!siteId.matches("^\\d{0,8}$")){
			throw new GeneralException("位置信息不合法");
		}
		Integer site = Integer.valueOf(siteId);
		if(site == -1){
			site = null;
		}
		String userId = Util.getCookie(req, "userId");
		Note note = new Note();
		try {
			String imgLoc = Util.pasteImg(imgPath, Util.userFilePath(userId), root);
			note.setImgLoc(imgLoc);
		} catch (IOException e) {
			e.printStackTrace();
			throw new HomePageException("存储路径错误，请重新上传图片。");
		}
		String id = UUID.randomUUID().toString();
		note.setId(id);
		note.setUserId(userId);
		note.setTitle(title);
		note.setContent(content);
		note.setSite(site);
		dao.saveNote(note);
		return true;
	}
	
	
	public List< NoteCover > getNoteCoverDate(String start, String end)throws GeneralException{
		if(!Util.isNotNull(start)){
			throw new GeneralException("请重新刷新网页！");
		}
		if(!start.matches("\\d{0,6}")){
			throw new GeneralException("请重新刷新网页！");
		}
		if(!Util.isNotNull(end)){
			throw new GeneralException("请重新刷新网页！");
		}
		if(!end.matches("\\d{0,6}")){
			throw new GeneralException("请重新刷新网页！");
		}
		Map<String , Integer> map = new HashMap<String , Integer>();
		map.put("start", Integer.valueOf(start));
		map.put("end", Integer.valueOf(end));
		List< NoteCover > noteCovers = dao.getNoteCoverDate(map);
		return noteCovers;
		
	}
	@Cacheable(value="cuckooCache",key="#id")
	public NoteMap getNoteMap(String id){
		System.out.println("连接数据库。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
		System.out.println("连接数据库。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
		System.out.println("连接数据库。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
		System.out.println("连接数据库。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
		System.out.println("连接数据库。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
		System.out.println("连接数据库。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
		NoteMap noteMap= dao.getNoteMapById(id);
		dao.updateNoteBrowse(id);
		return noteMap;
	}
	
	public Boolean saveComment(String noteId , String comment , HttpServletRequest req){
		NoteComment comm = new NoteComment();
		String id = UUID.randomUUID().toString();
		String userId = Util.getCookie(req, "userId");
		comm.setId(id);
		comm.setNoteId(noteId);
		comm.setUserId(userId);
		comm.setComment(comment);
		dao.saveComment(comm);
		return true;
	}


	public Boolean delComment(String id, HttpServletRequest req) {
		String userId = Util.getCookie(req, "userId");
		NoteComment comment = new NoteComment();
		comment.setId(id);
		comment.setUserId(userId);
		dao.delComment(comment);
		return true;
	}


	public Boolean saveTranspond(String noteId, String transmit, HttpServletRequest req) {
		NoteTranspond transpond = new NoteTranspond();
		String id = UUID.randomUUID().toString();
		String userId = Util.getCookie(req, "userId");
		transpond.setId(id);
		transpond.setPid(userId);
		transpond.setNoteid(noteId);
		transpond.setComment(transmit);
		dao.saveTranspond(transpond);
		return true;
	}


	public TransmitNote getCircleTranspond(String noteId) {
		TransmitNote transmitNote = new TransmitNote();
		int total = dao.getTranspondCount(noteId);
		transmitNote.setTotal(total);
		List<TransmitNoteTranpondBean> tranponds = dao.getNoteTranspond(noteId);
		transmitNote.setTranponds(tranponds);
		return transmitNote;
		
	}


	public Boolean ifPraise(String noteId, HttpServletRequest req) {
		if(!Util.isNotNull(noteId)){
			throw new GeneralException("请重新刷新网页！");
		}
		String userId = Util.getCookie(req, "userId");
		Map<String,String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("noteId", noteId);
		int num = dao.getPraiseByUidAndNoteId(map);
		if(num > 0){
			return true;
		}
		return false;
	}


	public String praiseActtion(String noteId, HttpServletRequest req) {
		if(!Util.isNotNull(noteId)){
			throw new GeneralException("请重新刷新网页！");
		}
		String userId = Util.getCookie(req, "userId");
		Map<String,String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("noteId", noteId);
		if(ifPraise(noteId, req)){
			dao.delPraise(map);
			return "delPraise";
		}else{
			String id = UUID.randomUUID().toString();
			map.put("id", id);
			dao.addPraise(map);
			return "addPraise";
		}
	}
}
