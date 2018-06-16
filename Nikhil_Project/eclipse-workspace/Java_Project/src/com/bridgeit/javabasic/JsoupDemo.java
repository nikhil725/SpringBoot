package com.bridgeit.javabasic;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupDemo {
	
	public static void main(String[] args) throws IOException {
		
		Document document = Jsoup.connect("https://keep.google.com").get();
		String title = document.title();	
		System.out.println("Title is : "+title);
		
		String keywords = document.select("meta[name=keywords]").first().attr("content");
		System.out.println("keyword: "+keywords);
		
	}

}
