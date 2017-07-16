package cn.cuckoo.note.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cn.cuckoo.note.entity.NoteCover;
import cn.cuckoo.note.entity.NoteMap;
import cn.cuckoo.note.entity.TransmitNote;
import cn.cuckoo.note.service.NoteService;
import cn.cuckoo.util.JsonResult;

@Controller
@RequestMapping("/note")
public class NoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/logging/publisNote.do")
	@ResponseBody
	public JsonResult<Boolean> publisNote(HttpServletRequest req){
		String root = req.getRealPath("/");
		String title = req.getParameter("title");
		String siteId = req.getParameter("siteId");
		String imgPath = req.getParameter("imgPath");
		String content = req.getParameter("content");
		Boolean sure = noteService.publisNote(title,siteId,imgPath,content,root,req);
		return new JsonResult<Boolean>(sure);
	}
	
	@RequestMapping("/NoteCoverDate.do")
	@ResponseBody
	public JsonResult<List<NoteCover>> NoteCoverDate(HttpServletRequest req){
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		List<NoteCover> noteCovers= noteService.getNoteCoverDate(start, end);
		return new JsonResult<List<NoteCover>>(noteCovers);
	}
	
	@RequestMapping("/no.do")
	public String no(HttpServletRequest req,HttpServletResponse res){
		String noteId = req.getParameter("noteId");
		NoteMap noteMap= noteService.getNoteMap(noteId);
		req.setAttribute("noteMap", noteMap);
		Gson gson = new Gson();
		String str = gson.toJson(noteMap);
		req.setAttribute("noteMapStr", str);
		return "note";
	}
	
	@RequestMapping("/logging/upcomment.do")
	@ResponseBody
	public JsonResult<Boolean> upcomment(HttpServletRequest req){
		String noteId = req.getParameter("noteId");
		String comment = req.getParameter("comment");
		noteService.saveComment(noteId, comment,req);
		return new JsonResult<Boolean>(true);
	}
	
	@RequestMapping("/logging/delComment.do")
	@ResponseBody
	public JsonResult<Boolean> delComment(HttpServletRequest req){
		String id = req.getParameter("id");
		noteService.delComment(id , req);
		return new JsonResult<Boolean>(true);
	}
	
	
	@RequestMapping("/logging/saveTranspond.do")
	@ResponseBody
	public JsonResult<Boolean> saveTranspond(HttpServletRequest req){
		String noteId = req.getParameter("noteId");
		String transmit = req.getParameter("transmit");
		noteService.saveTranspond(noteId, transmit,req);
		return new JsonResult<Boolean>(true);
	}
	
	@RequestMapping("/getCircleTranspond.do")
	@ResponseBody
	public JsonResult<TransmitNote> getCircleTranspond(HttpServletRequest req){
		String noteId = req.getParameter("noteId");
		TransmitNote transmitNote =  noteService.getCircleTranspond(noteId);
		return new JsonResult<TransmitNote>(transmitNote);
	}
	@RequestMapping("/logging/ifPraise.do")
	@ResponseBody
	public JsonResult<Boolean> ifPraise(HttpServletRequest req){
		String noteId = req.getParameter("noteId");
		Boolean  ifPraise=  noteService.ifPraise(noteId,req);
		JsonResult<Boolean> result = new JsonResult<Boolean>(ifPraise);
		return result;
	}
	@RequestMapping("/logging/praiseActtion.do")
	@ResponseBody
	public JsonResult<String> praiseActtion(HttpServletRequest req){
		String noteId = req.getParameter("noteId");
		String  operatePraise=  noteService.praiseActtion(noteId,req);
		return new JsonResult<String>(operatePraise);
	}
}
