package com.example.ce;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDownloader {
	 private URL url=null;
	  public String download(String urlstr){
		  StringBuffer stringBuffer=new StringBuffer();
		  String line;
		  BufferedReader bufferedReader=null;
		  try {
			//创建一个URL对象
			url=new URL(urlstr);
			//得到一个HttpURLConnection对象
			HttpURLConnection httpsURLConnection=(HttpURLConnection) url.openConnection();
			// 得到IO流，使用IO流读取数据 
			bufferedReader=new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
			while((line=bufferedReader.readLine())!=null){//一行一行的读
				stringBuffer.append(line);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return stringBuffer.toString();
		  
	  }

	  // 该函数返回整形 -1：代表下载文件出错 ;0：代表下载文件成功; 1：代表文件已经存在 
	 public int downfile(String urlStr,String path,String fileName){
		 InputStream inputstream=null;  
	     FileUtils fileUtils=new FileUtils();  
	     if(fileUtils.isExist(path+fileName))  
	         return 1;  
	     else  
	     {  
	         try {  
	             inputstream=getFromUrl(urlStr);  
	         } catch (IOException e) {  
	             e.printStackTrace();  
	         }  
	         File file=fileUtils.writeToSDPATHFromInput(path, fileName, inputstream);  
	         if(file!=null)  
	             return 0;  
	         else   
	             return -1;  
	     }  
		 
		 

		 
	 }
	//根据url字符串得到输入流  
	public InputStream getFromUrl(String urlStr) throws IOException  
	{         
	    url=new URL(urlStr);              
	    HttpURLConnection httpUrlConnection=(HttpURLConnection) url.openConnection();  
	    InputStream input=httpUrlConnection.getInputStream();     
	    return input;  
	}  
}
