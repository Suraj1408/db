package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	public static String uploadDirectory= System.getProperty("user.dir")+"/uploads";
	
	@RequestMapping(value="/")
	public String uploadPage(Model model)
	{
		
		
		return "uploadview";
	}
	
	 @RequestMapping(value="/upload") 
	 public String upload(Model model , @RequestParam("files") MultipartFile[] files) 
	 { 
		 StringBuilder fileNames= new StringBuilder();
	  
	  
		 for(MultipartFile file:files)
	  {
			 System.out.println(file);
	  
	  Path fileNamesAndPath=Paths.get(uploadDirectory,file.getOriginalFilename());
	  
	  fileNames.append(file.getOriginalFilename());
	 
	  try{ 
		
		  Files.write(fileNamesAndPath, file.getBytes()); 
		  System.out.println("2---------------");
	  
	  } 
	  
	  catch(IOException e) 
	  {
	  
	  e.printStackTrace(); 
	  
	  System.out.println("3---------------"+e.getMessage());
	  }
	  }
	  model.addAttribute("msg","File Successfully Uploaded"  +fileNames.toString());
		 
	 // return "uploadstatusview"; 
	  
	  
	  return "uploadview";

	 }
}
