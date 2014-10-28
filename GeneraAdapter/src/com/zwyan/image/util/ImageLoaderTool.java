package com.zwyan.image.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.example.testlibary.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 * 浣跨敤寮�簮椤圭洰Image-Loader鏉ュ姞杞界綉缁滀笂鐨勫浘鐗�
 * 
 * @author zw.yan
 * 
 */
public class ImageLoaderTool {

	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	private ImageLoaderConfiguration config;
	private Context context;

	public ImageLoaderTool(Context context) {
		this.context = context;
		initConfig();
	}

	private void initConfig() {
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.cosmos_no_image_icon)
				.showImageForEmptyUri(R.drawable.cosmos_no_image_icon)
				.showImageOnFail(R.drawable.cosmos_no_image_icon)
				// 涓嬭級澶辨晽寰岄’绀虹殑鍦栫墖
				.cacheInMemory()
				// 鏄惁绶╁瓨鍦ㄥ収瀛樹腑
				.cacheOnDisc()
				// 鏄惁绶╁瓨鍦⊿D鍗¤闈�
				.displayer(new SimpleBitmapDisplayer())
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED).build();
		config = new ImageLoaderConfiguration.Builder(
				context.getApplicationContext())
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.discCacheFileCount(60)
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		imageLoader.init(config);
	}

	public void loadImageView(String imageUrl, ImageView image) {
		imageLoader
				.displayImage(imageUrl, image, options, animateFirstListener);
	}

	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}
}
