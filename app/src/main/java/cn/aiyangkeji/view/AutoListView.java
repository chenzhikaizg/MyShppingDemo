package cn.aiyangkeji.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.aiyangkeji.R;
import cn.aiyangkeji.util.TimeUtil;
import cn.aiyangkeji.util.UIHelper;


/**
 * @author SunnyCoffee
 * @create 2013-10-24
 * @version 1.0
 * @desc 自定义Listview　下拉刷新,上拉加载更多
 */

public class AutoListView extends ListView implements OnScrollListener{

	// 区分当前操作是刷新还是加载
	public static final int REFRESH = 0;
	public static final int LOAD = 1;

	// 区分PULL和RELEASE的距离的大小
	private static final int SPACE = 20;

	// 定义header的四种状态和当前状态
	private static final int NONE = 0;
	private static final int PULL = 1;
	private static final int RELEASE = 2;
	private static final int REFRESHING = 3;
	private int state;

	private Context context;
	private LayoutInflater inflater;
	private View header;
	private View footer;
	private View banner;
	private ProgressBar refreshing;
	private TextView tip;
	private RelativeLayout rlPull;
	private ImageView arrow;
	private TextView tvPullTip;
	private TextView tvLastTime;
	private TextView noData;
	private TextView loadFull;
	private TextView more;
	private ProgressBar loading;

	private RotateAnimation animation;
	private RotateAnimation reverseAnimation;

	private int startY;

	public int firstVisibleItem;
	public int scrollState;
	private int headerContentInitialHeight;
	private int headerContentHeight;

	// 只有在listview第一个item显示的时候（listview滑到了顶部）才进行下拉刷新， 否则此时的下拉只是滑动listview
	private boolean isRecorded;
	private boolean isLoading;// 判断是否正在加载
	private boolean loadEnable = true;// 开启或者关闭加载更多功能
	private boolean isLoadFull;
	private int pageSize = 9;
	private int curScrollY=0;

	private OnRefreshListener onRefreshListener;
	private OnLoadListener onLoadListener;
	private MyScrollListener myScrollListener;

	public AutoListView(Context context) {
		super(context);
		this.context=context;

	}

	public AutoListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;

	}

	public AutoListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context=context;

	}

	// 下拉刷新监听
	public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
		this.onRefreshListener = onRefreshListener;
	}

	// 加载更多监听
	public void setOnLoadListener(OnLoadListener onLoadListener) {
		this.loadEnable = true;
		this.onLoadListener = onLoadListener;
	}

	//滚动监听
	public void setMyScrollListener(MyScrollListener myScrollListener){
		this.myScrollListener=myScrollListener;
	}

	public boolean isLoadEnable() {
		return loadEnable;
	}

	// 这里的开启或者关闭加载更多，并不支持动态调整
	public void setLoadEnable(boolean loadEnable) {
		this.loadEnable = loadEnable;
		this.removeFooterView(footer);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// 初始化组件
	public void initView(Context context,View banner) {

		// 设置箭头特效
		animation = new RotateAnimation(0, -180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		animation.setInterpolator(new LinearInterpolator());
		animation.setDuration(100);
		animation.setFillAfter(true);

		reverseAnimation = new RotateAnimation(-180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		reverseAnimation.setInterpolator(new LinearInterpolator());
		reverseAnimation.setDuration(100);
		reverseAnimation.setFillAfter(true);

		inflater = LayoutInflater.from(context);
		footer = inflater.inflate(R.layout.listview_footer_load_more, null);
		loadFull = (TextView) footer.findViewById(R.id.loadFull);
		noData = (TextView) footer.findViewById(R.id.noData);
		more = (TextView) footer.findViewById(R.id.more);
		loading = (ProgressBar) footer.findViewById(R.id.loading);
		header = inflater.inflate(R.layout.listview_pull_refresh_header, null);
		rlPull=(RelativeLayout)header.findViewById(R.id.rl_pull);
		arrow = (ImageView) header.findViewById(R.id.arrow);
		tvPullTip=(TextView)header.findViewById(R.id.tv_pull_tip);
		tvLastTime=(TextView)header.findViewById(R.id.tv_last_time);
		tip = (TextView) header.findViewById(R.id.tip);
		refreshing = (ProgressBar) header.findViewById(R.id.refreshing);

		// 为listview添加头部和尾部，并进行初始化
		headerContentInitialHeight = header.getPaddingTop();
		measureView(header);
		headerContentHeight = header.getMeasuredHeight();
		if(banner!=null)
		{
			((ViewGroup)header).addView(banner);
		}
		topPadding(-headerContentHeight);
		this.addHeaderView(header, null, false);
		this.addFooterView(footer,null,false);
		this.setOnScrollListener(this);
		footer.setVisibility(View.INVISIBLE);
	}

	public void onRefresh() {
		if (onRefreshListener != null) {
			onRefreshListener.onRefresh();
		}
	}

	public void onLoad() {
		if (onLoadListener != null) {
			onLoadListener.onLoad();
		}
	}

	public void onRefreshComplete(String updateTime) {
		state = NONE;
		refreshHeaderViewByState();
	}


	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		this.scrollState = scrollState;
		ifNeedLoad(view, scrollState);
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		this.firstVisibleItem = firstVisibleItem;
		View c = view.getChildAt(0);
		if (c == null) {
			return ;
		}

		int firstVisiblePosition = view.getFirstVisiblePosition();
		int top = c.getTop();

		int headerHeight = 0;
		if (firstVisiblePosition >= 1) {
			headerHeight = view.getHeight();
		}
		curScrollY = -top + firstVisiblePosition * c.getHeight() + headerHeight;
		if(myScrollListener!=null) {
			myScrollListener.onScrollY(curScrollY);
		}
	}


	public void footViewShow()
	{
		footer.setVisibility(View.VISIBLE);
	}

	public void footViewHide()
	{
		footer.setVisibility(View.INVISIBLE);
	}

	public void headViewHide(){
		header.setVisibility(View.GONE);
	}

	public void headViewShow(){
		header.setVisibility(View.VISIBLE);
	}

	// 用于下拉刷新结束后的回调
	public void onRefreshComplete() {
		String currentTime = TimeUtil.getCurrentTime();
		onRefreshComplete(currentTime);
	}

	// 用于加载更多结束后的回调
	public void onLoadComplete() {
		isLoading = false;
	}

	// 根据listview滑动的状态判断是否需要加载更多
	public void ifNeedLoad(AbsListView view, int scrollState) {
		if (!loadEnable) {
			return;
		}
		try {
			if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
					&& !isLoading
					&& view.getLastVisiblePosition() == view
					.getPositionForView(footer) && !isLoadFull) {
				onLoad();
				isLoading = true;
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 监听触摸事件，解读手势
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (firstVisibleItem == 0&&curScrollY==0) {
					isRecorded = true;
					startY = (int) ev.getY();
				}
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				if (state == PULL) {
					state = NONE;
					refreshHeaderViewByState();
				} else if (state == RELEASE) {
					state = REFRESHING;
					refreshHeaderViewByState();
					onRefresh();
				}
				isRecorded = false;
				break;
			case MotionEvent.ACTION_MOVE:
				whenMove(ev);
				break;
		}
		return super.onTouchEvent(ev);
	}

	// 解读手势，刷新header状态
	private void whenMove(MotionEvent ev) {
		if (!isRecorded) {
			return;
		}
		int tmpY = (int) ev.getY();
		int space = tmpY - startY;
		int topPadding = space - headerContentHeight;
		switch (state) {
			case NONE:
				if (space > 0) {
					state = PULL;
					refreshHeaderViewByState();
				}
				break;
			case PULL:
				topPadding(topPadding);
				if (scrollState ==OnScrollListener.SCROLL_STATE_TOUCH_SCROLL
						&& space > headerContentHeight + SPACE) {
					state = RELEASE;
					refreshHeaderViewByState();
				}
				break;
			case RELEASE:
				topPadding(topPadding);
				if (space > 0 && space < headerContentHeight + SPACE) {
					state = PULL;
					refreshHeaderViewByState();
				} else if (space <= 0) {
					state = NONE;
					refreshHeaderViewByState();
				}
				break;
		}

	}

	// 调整header的大小。其实调整的只是距离顶部的高度。
	private void topPadding(int topPadding) {
		if(topPadding< UIHelper.dip2px(context,40)) {//在小于40dip内允许继续下拉
			header.setPadding(header.getPaddingLeft(), topPadding,
					header.getPaddingRight(), header.getPaddingBottom());
			header.invalidate();
		}
	}

	/**
	 * 这个方法是根据结果的大小来决定footer显示的。
	 * <p>
	 * 这里假定每次请求的条数为10。如果请求到了10条。则认为还有数据。如过结果不足10条，则认为数据已经全部加载，这时footer显示已经全部加载
	 * </p>
	 *
	 * @param resultSize
	 */
	public void setResultSize(int resultSize) {
		if (resultSize == 0) {
			isLoadFull = true;
			loadFull.setVisibility(View.GONE);
			loading.setVisibility(View.GONE);
			more.setVisibility(View.GONE);
			noData.setVisibility(View.VISIBLE);
		} else if (resultSize > 0 && resultSize < pageSize) {
			isLoadFull = true;
			loadFull.setVisibility(View.VISIBLE);
			loading.setVisibility(View.GONE);
			more.setVisibility(View.GONE);
			noData.setVisibility(View.GONE);
		} else if (resultSize == pageSize) {
			isLoadFull = false;
			loadFull.setVisibility(View.GONE);
			loading.setVisibility(View.VISIBLE);
			more.setVisibility(View.VISIBLE);
			noData.setVisibility(View.GONE);
		}

	}

	// 根据当前状态，调整header
	private void refreshHeaderViewByState() {
		switch (state) {
			case NONE:
				topPadding(-headerContentHeight);
				refreshing.setVisibility(View.GONE);
				tip.setVisibility(View.GONE);
				//arrow.clearAnimation();
				break;
			case PULL:
				rlPull.setVisibility(View.VISIBLE);
				refreshing.setVisibility(View.GONE);
				tvPullTip.setText("下拉刷新");
				tvLastTime.setText("最后更新："+TimeUtil.getLastRefreshTime(context));
				//arrow.clearAnimation();
				//arrow.setAnimation(reverseAnimation);
				break;
			case RELEASE:
				tvPullTip.setText("松开立即刷新");
				//arrow.clearAnimation();
				//arrow.setAnimation(animation);
				break;
			case REFRESHING:
				topPadding(headerContentInitialHeight);
				refreshing.setVisibility(View.VISIBLE);
				tip.setVisibility(View.VISIBLE);
				//arrow.clearAnimation();
				rlPull.setVisibility(View.GONE);
				tip.setText("正在载入...");
				//tip.setVisibility(View.GONE);
				break;
		}
	}

	// 用来计算header大小的。比较隐晦。因为header的初始高度就是0,貌似可以不用。
	private void measureView(View child) {
		ViewGroup.LayoutParams p = child.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}


	private float xDistance, yDistance, xLast, yLast;

	/**
	 * 在scrollView 嵌套viewPager 时阻止横线滑动viewPager时 垂直方向偏移量大造成的scrollView 滚动 	(non-Javadoc)
	 * @see android.widget.ScrollView#onInterceptTouchEvent(MotionEvent)
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				xDistance = yDistance = 0f;
				xLast = ev.getX();
				yLast = ev.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				final float curX = ev.getX();
				final float curY = ev.getY();

				xDistance += Math.abs(curX - xLast);
				yDistance += Math.abs(curY - yLast);
				xLast = curX;
				yLast = curY;

				if(xDistance > yDistance){
					return false;
				}else {
					if (firstVisibleItem == 0&&curScrollY==0) {
						isRecorded = true;
						startY = (int) ev.getY();

					}
				}

		}

		return super.onInterceptTouchEvent(ev);
	}


	/*
	 * 定义下拉刷新接口
	 */
	public interface OnRefreshListener {
		public void onRefresh();
	}

	/*
	 * 定义加载更多接口
	 */
	public interface OnLoadListener {
		public void onLoad();
	}

	/*
	 *获取ScrollY的值
	 */
	public interface MyScrollListener{
		public void onScrollY(int y);
	}

}
