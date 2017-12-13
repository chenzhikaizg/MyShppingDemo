package cn.aiyangkeji.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

public class GridViewTool {
	/*
	 *verticalSpacing 纵向边句
	 * columnNum  每个的item 个数
	 */
	public static void adaptiveHight(GridView gridView, Context context, float verticalSpacing, int columnNum) {
		ListAdapter listAdapter = gridView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		int itemHeight=0;
		if (listAdapter.getCount() > 0) {
			View listItem;
//			for(int i=0;i<listAdapter.getCount();i++)
//			{
				listItem = listAdapter.getView(0, null, gridView);
				listItem.measure(0, 0);
				itemHeight=listItem.getMeasuredHeight();
			//}

			 
			int rows = listAdapter.getCount()%columnNum!=0?listAdapter.getCount()/columnNum+1:listAdapter.getCount()/columnNum;
			totalHeight = itemHeight*rows+((rows-1)*UIHelper.dip2px(context,verticalSpacing));
		}
		
		ViewGroup.LayoutParams params = gridView.getLayoutParams();
		params.height = totalHeight;
		gridView.setLayoutParams(params);
	}
}
