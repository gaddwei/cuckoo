package cn.cuckoo.web;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.cuckoo.util.JsonResult;

@Controller
@RequestMapping("/util")
public class GeneralController {

    @RequestMapping("/hello.do")
    @ResponseBody
    public Object hello(){
        return 
            new String[]{"Hello", "World!"};
    }
    @RequestMapping("/tempImg.do")
	@ResponseBody
	public String tempImg(HttpServletRequest request){
		String patha = request.getRealPath("/");
		System.out.println(patha);
		try {
	        //����ǰ�����ĳ�ʼ����  CommonsMutipartResolver ���ಿ�ֽ�������
	       CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	               request.getSession().getServletContext());
	       String path = "account/temp/ico/"+UUID.randomUUID().toString();
	       //���form���Ƿ���enctype="multipart/form-data"
	       if(multipartResolver.isMultipart(request))
	       {
	           //��request��ɶಿ��request
	           MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
	          //��ȡmultiRequest �����е��ļ���
	           Iterator iter=multiRequest.getFileNames();
	           while(iter.hasNext())
	           {
	               //һ�α��������ļ�
	               MultipartFile file=multiRequest.getFile(iter.next().toString());
	               if(file!=null)
	               {	
	            	   String fileName = file.getOriginalFilename();
	                   path += fileName.substring(fileName.lastIndexOf("."));
	                   //�ϴ�
	                   file.transferTo(new File(patha+path));
	               }
	           }
	       }
	       System.out.println(path);
	       return path; 
		} catch (Exception e) {
			
		}
		return "error";
	}
    
   
    
}
