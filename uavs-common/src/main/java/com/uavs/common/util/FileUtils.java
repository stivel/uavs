package com.uavs.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.uavs.common.util.oss.OssBean;
import com.uavs.common.util.oss.OssUtils;

public class FileUtils {
	private static Logger logger = Logger.getLogger(FileUtils.class);
	
	//10M
	private static long fileSize = 10*1024*1024;
	
	public static Map<String,Object> checkFile(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("flag", true);
		try{
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
	        if (multipartResolver.isMultipart(request)) {
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
	            Iterator<String> iter = multiRequest.getFileNames();
	            while (iter.hasNext()) {
	                //取得上传文件
	                List<MultipartFile> files = multiRequest.getFiles(iter.next());
	                for(MultipartFile file:files) {
	                	//校验上传文件格式
	                	String fileName = file.getOriginalFilename();
	                	String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
	                	if(!(suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png") || suffix.equalsIgnoreCase("zip"))){
	                		map.put("flag", false);
	                		map.put("desc", "上传失败，文件格式有误，请重新上传");
	                		break ;
	                	}
	                    //校验上传文件大小
	                	if(file.getSize()>fileSize){
	                		map.put("flag", false);
	                		map.put("desc", "上传失败，图片大小不能超过10M，请重新上传");
	                		break;
	                	}
	                }
	            }
	        }
		}catch(Exception e){
			map.put("flag", false);
			map.put("desc", "上传失败，网络异常，请重新上传");
			logger.error("校验文件出错...", e);
		}
		return map;
	}
	
	
	/**
	 * FIl 
	 * @param file
	 * @param savePath
	 * @param saveName
	 * @return
	 */
	public static List<String> upload(HttpServletRequest request,String savePath) {
		List<String> list = new ArrayList<String>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            String prefix = DateUtils.getDateTime("HHmmssSSS")+getRandom();
            while (iter.hasNext()) {
                //取得上传文件
                List<MultipartFile> files = multiRequest.getFiles(iter.next());
                for(MultipartFile file:files) {
                    //取得当前上传文件的文件名称
                    String uploadFileName = file.getOriginalFilename();
                    String saveName = prefix+"."+uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
                    upload(file, savePath, saveName);
                    list.add(saveName);
                }
            }
        }
        return list;
		
	}
	
	public synchronized static String getRandom(){
		Random random = new Random();
		String result = "";;
		for (int i = 0; i < 4; i++) {
			result+=random.nextInt(10);
		}
		return result;
	}
	
	/**
	 * @description 文件上传
	 * @param file
	 * @param savePath
	 *            保存路径（绝对路径）
	 * @param saveName
	 *            要保存的文件名
	 */
	public static String upload(MultipartFile file, String savePath, String saveName) {
		if("true".equals(ConfigPropUtils.get("oss_open"))){
			try {
				OssBean upload = OssUtils.upload(savePath+"/"+saveName, file.getInputStream());
				return upload.getFilePath();
			} catch (IOException e1) {
				e1.printStackTrace();
				return null;
			}
			
		}else{
			File targetFile = new File(savePath);
			if (!targetFile.exists()) {
				mkDir(targetFile);
			}
			BufferedOutputStream bos = null;
			BufferedInputStream bis = null;
			try {
				bos = new BufferedOutputStream(new FileOutputStream(targetFile+"/"+saveName));
				bis = new BufferedInputStream(file.getInputStream());
				byte[] buf = new byte[2 * 1024];
				int i = -1;
				while ((i = bis.read(buf)) != -1) {
					bos.write(buf, 0, i);
				}
				bos.flush();
			} catch (IOException e) {
				logger.info(file.getName() + "文件上传失败");
				e.printStackTrace();
			} finally {
				if (null != bos) {
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != bis) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return targetFile.getAbsolutePath();
		}
	}

	public static void mkDir(File file) {
		if (file.getParentFile().exists()) {
			file.mkdir();
		} else {
			mkDir(file.getParentFile());
			file.mkdir();
		}
	}
	
	public static void main(String[] args) {
		
	}
	
   /** 上传文件，按时间重新生成文件名，允许.jpg|.jpeg|.gif|.png
	 *
	 * @param request
	 * @param file
	 *            定义的MultipartFile
	 * @param folderDir
	 *            文件上传目录
	 * @return path 返回一个路径|返回空串代表失败
	 * @author zhangyinghua
	 */
	public static String Upload(HttpServletRequest request, MultipartFile file, String folderDir) throws Exception {
		if (file == null || file.getSize() < 1 || folderDir == null) {
			return "";
		}
		String fileRealName = ""; // 重新生成的文件名
		if (!file.isEmpty()) {
			// 得到该文件的后缀，判断是否允许上传
			String fileName = file.getOriginalFilename();
			String ext = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
			fileRealName = DateUtils.getCreateTime() + ext;
			if (ext.toLowerCase().equals("jpg") || ext.toLowerCase().equals("gif") || ext.toLowerCase().equals("png")
					|| ext.toLowerCase().equals("jpeg")) {
				// 系统路径
				// String realBaseDir =
				// request.getSession().getServletContext().getRealPath("/") +
				File baseFile = new File(folderDir);
				if (!baseFile.exists()) {
					baseFile.mkdirs();
				}
				InputStream stream = null;
				OutputStream bos = null;
				try {
					stream = file.getInputStream();
					bos = new FileOutputStream(folderDir + File.separator + fileRealName);
					int bytesRead = 0;
					byte[] buffer = new byte[8192];
					while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
						bos.write(buffer, 0, bytesRead);
					}
					bos.close();
					stream.close();
				} catch (Exception e) {
					if (bos != null) {
						bos.close();
					}
					if (stream != null) {
						stream.close();
					}
					e.printStackTrace();
				}
			} else {
				return "";
			}
		}
		return folderDir + fileRealName;
	}
}
