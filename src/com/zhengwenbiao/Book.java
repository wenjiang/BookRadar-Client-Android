package com.zhengwenbiao;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Book {
	private String title;
	private int rating;
	private String[] author;

	public String getTitle() {
		return title;
	}

	public int getRating() {
		return rating;
	}

	public String[] getAuthor() {
		return author;
	}
	
	// TODO 解析字符串为Book实例
	public static Book[] parse(String str) throws JSONException {
		if (str.startsWith("{")) {
			return (handleWithSingleBook(str));

		} else {
			return (handleWithMultiBook(str));
		}
	}

	private static Book[] handleWithMultiBook(String str) throws JSONException {
		JSONArray jsonArray = new JSONArray(str);
		int length = jsonArray.length();
		Book[] books = new Book[length];
		for (int i = 0; i < length; i++) {
			books[i] = parseJsonAsBook(jsonArray.getJSONObject(i));
		}
		return books;
	}

	private static Book[] handleWithSingleBook(String str) throws JSONException {
		Book[] books = new Book[1];
		JSONObject json = new JSONObject(str);
		books[0] = parseJsonAsBook(json);
		return books;

	}

	private static Book parseJsonAsBook(JSONObject json) throws JSONException {
		Book book = new Book();
		book.title = json.getString("title");
		book.rating = json.getInt("rating");
		Object authorObj = json.get("author");
		book.author = parseAuthorObjAsAuthor(authorObj);
		return book;
	}

	private static String[] parseAuthorObjAsAuthor(Object authorObj)
			throws JSONException {
		String[] author;
		if (authorObj instanceof JSONArray) {
			JSONArray jsonAuthor = (JSONArray) authorObj;
			int length = jsonAuthor.length();
			author = new String[length];
			for (int i = 0; i < length; i++) {
				author[i] = (String) jsonAuthor.get(i);
			}
		} else {
			author = new String[1];
			String singleAuthor = (String) authorObj;
			author[0] = singleAuthor;
		}
		return author;
	}

}
