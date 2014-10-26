package com.example.sample;

import java.util.ArrayList;
import java.util.List;

import com.zwyan.generlviewadapter.adapter.GeneraViewAdapter;
import com.zwyan.generlviewadapter.adapter.ViewHolder;

import android.os.Bundle;
import android.annotation.SuppressLint;
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
				mDatas.add(new Item("gernera Adapter"+i,"https://avatars3.githubusercontent.com/u/8434091?v=2&s=460","gernera Adapter sample"+i));
			}
		}
		mAdapter = new GeneraViewAdapter<Item>(this, mDatas,
				R.layout.item) {

			@SuppressLint("ResourceAsColor")
			@Override
			public void convert(ViewHolder viewHolder, Item item) {
				// TODO Auto-generated method stub
				viewHolder.setImageViewByUrl(R.id.image, item.url);
				viewHolder.setTextViewString(R.id.txt, item.name);
				viewHolder.setTextViewColor(R.id.content,R.color.red );
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
