package com.example.sample;

import java.util.ArrayList;
import java.util.List;

import com.zwyan.generlviewadapter.adapter.GeneraViewAdapter;
import com.zwyan.generlviewadapter.adapter.ViewHolder;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	List<Item> mDatas = new ArrayList<Item>();
	GeneraViewAdapter<Item> mAdapter;
	ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.listView1);
		for (int i = 0; i < 80; i++) {
			if (i % 2 == 0) {
				mDatas.add(new Item("gernera Adapter"+i,"http://avatar.csdn.net/D/D/6/1_itbailei.jpg","gernera Adapter sample"+i));
			} else {
				mDatas.add(new Item("gernera Adapter"+i,"http://git-scm.com/images/icons/nav-read-book.png","gernera Adapter sample"+i));
			}
		}
		mAdapter = new GeneraViewAdapter<Item>(this, mDatas,
				R.layout.item) {

			@Override
			public void convert(ViewHolder viewHolder, Item item) {
				// TODO Auto-generated method stub
				viewHolder.setImageViewByUrl(R.id.image, item.url);
				viewHolder.setTextViewString(R.id.txt, item.name);
				viewHolder.setTextViewString(R.id.content, item.content);
			}
		};
		mListView.setAdapter(mAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class Item {
		public String name;
		public String url;
		public String content;
		public Item(String name, String url,String content) {
			this.name = name;
			this.url = url;
			this.content=content;
		}
	}
}
