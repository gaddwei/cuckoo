package cn.cuckoo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import cn.cuckoo.homepage.dao.HomePageDAO;
import cn.cuckoo.homepage.entity.HomeImg;

public class Util {
	private static List<HomeImg> homeImgList = null;
	
	//MD5加盐加密
	private static final String salt = 
			"你吃了吗？";
	public  static String pasteImg(String impPath,String newPath,String root)throws IOException{
		String split = File.separator;
		String userP=realPath(root)+split+newPath;
		File f = new File(userP);
		if(!f.exists()){
			f.mkdirs();
		}
		FileInputStream fis = new FileInputStream(root+split+impPath);
		String fileName = new File(impPath).getName();
		FileOutputStream fos = new FileOutputStream(userP+split+fileName);
		byte[] buf = new byte[1024*10];
		int len = -1;
		while((len = fis.read(buf))!=-1){
			fos.write(buf,0,len);
		}
		
		System.out.println("复制完毕!");
		fis.close();
		fos.close();
		new File(impPath).delete();
		newPath =  newPath+split+fileName;
		if("\\".equals(split)){
			newPath = newPath.replaceAll("\\\\", "/");
		  }
		return newPath;
	}
	
	public static String realPath(String root){
		String path = root.substring(0, root.lastIndexOf("cuckoo")-1);
		return path;
	}
	
	/**
	 * MD5码加盐
	 * @param pwd
	 * @return
	 */
	public static String crypt(String pwd){
		return DigestUtils.md5Hex(pwd+salt);
	}
	
	/**
	 * 判断字符串是否为空  为空返回false 否则返回true
	 * @param str 接收字符串
	 * @return
	 */
	public static boolean isNotNull(String str){
		if(str == null || "".equals(str.trim())){
			return false;
		}
		return true;
	}
	
	/**
	 * 存储用户文件的文件夹
	 * @param userId 用户Id
	 * @return
	 */
	public static String userFilePath(String userId){
		String split = File.separator;
		return "upload"+split+"account"+split+"user"+split+userId;
	}
	
	public static void sethomeImgList(List<HomeImg> ImgList){
		homeImgList = ImgList;
	}
	
	public static List<HomeImg>  gethomeImgList(){
		return homeImgList;
	}
	
	/**
	 * 获取cookie值
	 * @param req 
	 * @param cookieName
	 * @return
	 */
	public static String getCookie(HttpServletRequest req ,String cookieName){
		Cookie[] cookies = req.getCookies();
		for(Cookie c : cookies){
			if(c.getName().equals(cookieName)){
				return c.getValue();
			}
		}
		return null;
	}
	
	public static String getRealPath(HttpServletRequest req,String path){
		String root = req.getHeader("Host");
		return "http://"+root+"/"+path;
	}
}
