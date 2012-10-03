package com.zhengwenbiao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;

public class TabA extends Activity {
	private ListView listview;
	private List<Map<String, Object>> data;
	private Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.taba);
		this.listview = (ListView) findViewById(R.id.list);
		button = (Button) findViewById(R.id.button);
		try {
			this.data = getData();
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showBook();
			}
		});

	}

	private void showBook() {
		SimpleAdapter adapter = new SimpleAdapter(TabA.this, this.data,
				R.layout.item, new String[] { "信息", "评分" }, new int[] {
						R.id.book, R.id.rating });
		adapter.setViewBinder(new SimpleAdapter.ViewBinder() {

			@Override
			public boolean setViewValue(View view, Object data,
					String textRepresentation) {
				if ((view instanceof RatingBar) && (data instanceof Float)) {
					RatingBar ratingBar = (RatingBar) view;
					ratingBar.setRating((Float) data);
					return true;
				}
				return false;
			}
		});
		listview.setAdapter(adapter);
	}

	private List<Map<String, Object>> getData() throws JSONException {
		String str = "[{\"title\":\"java book\",\"author\":\"json\",\"rating\":3},{\"title\":\"java book\",\"author\":[\"json\",\"fuck\"],\"rating\":4},{\"title\":\"java book\",\"author\":[\"json\",\"fuck\"],\"rating\":4}]";
		Book[] books = Book.parse(str);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		sortBook(books);
		for (int i = 0; i < books.length; i++) {
			String[] author = new String[books[i].getAuthor().length];
			Map<String, Object> map = new HashMap<String, Object>();
			String inform = "";
			String authorReault = "";
			author = books[i].getAuthor();
			String rating = books[i].getRating() + "";
			for (int j = 0; j < author.length; j++) {
				authorReault += "/" + author[j];
			}

			inform += books[i].getTitle() + "   " + authorReault;
			map.put("评分", Float.parseFloat(rating));
			map.put("信息", inform);
			list.add(map);
		}

		return list;
	}

	private void sortBook(Book[] books) {
		for (int i = 0; i < books.length - 1; i++) {
			if ((books[i].getRating()) < (books[i + 1].getRating())) {
				Book tempBook = new Book();
				tempBook = books[i];
				books[i] = books[i + 1];
				books[i + 1] = tempBook;
			}
		}

	}
}
