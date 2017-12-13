/*
 * File Name: MyDialog.java 
 * History:
 * Created by GaoWeiWei on 2014-12-19
 */
package cn.aiyangkeji.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;

import cn.aiyangkeji.R;


public class MyDialog extends Dialog {

	 
	//==========================================================================
	// Constants
	//==========================================================================

	//==========================================================================
	// Fields
	//==========================================================================
      private View parentView;
	//==========================================================================
	// Constructors
	//==========================================================================

	//==========================================================================
	// Getters
	//==========================================================================

	//==========================================================================
	// Setters
	//==========================================================================

	//==========================================================================
	// Methods
	//==========================================================================
	@SuppressWarnings("deprecation")
	public MyDialog(Context context, float height, float width) {
		super(context, R.style.my_dialog_style);
		LayoutInflater inflater = LayoutInflater.from(context);
		parentView = inflater.inflate(R.layout.my_dialog, null);
		setContentView(parentView);
	    Window window=getWindow();
	    WindowManager m=getWindow().getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * height); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * width); // 宽度设置为屏幕的0.65
        window.setAttributes(p);
		//调用这个方法时，按对话框以外的地方不起作用。按返回键还起作用,返回true就可以点击框外，使dialog消失

	   setCanceledOnTouchOutside(true);
		//调用这个方法时，按对话框以外的地方不起作用。按返回键也不起作用
	  setCancelable(true);

	}

	
	
    public void setMyCancelable(boolean flag)
    {
    	 setCanceledOnTouchOutside(flag);
 	     setCancelable(flag);

    }
	
	/** 
	 * @param view
	 * @param layoutParams
	 */
	public void addView(View view, LayoutParams layoutParams)
	{		 
		((ViewGroup)parentView).addView(view, layoutParams);
	}

	public void setLayout(float height,float width){
		Window window=getWindow();
		WindowManager m=getWindow().getWindowManager();
		Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
		WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
		p.height = (int) (d.getHeight() * height); // 高度设置为屏幕的0.6
		p.width = (int) (d.getWidth() * width); // 宽度设置为屏幕的0.65
		window.setAttributes(p);

	}
	/**
	 * 
	 * @param view
	 */
	@SuppressWarnings("deprecation")
	public void addView(View view)
	{
		((ViewGroup)parentView).removeAllViews();
		((ViewGroup)parentView).addView(view, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	}
	
	//==========================================================================
	// Inner/Nested Classes
	//==========================================================================
}
