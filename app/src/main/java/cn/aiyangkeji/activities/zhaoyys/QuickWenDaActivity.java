package cn.aiyangkeji.activities.zhaoyys;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.activities.myinfo.YysRegisterActivity;
import cn.aiyangkeji.util.ImgAndCameraHelper;
import cn.aiyangkeji.util.ListViewUtil;
import cn.aiyangkeji.view.MyDialog;

/**
 * Created by chenzhikai on 2017/11/3.
 */

public class QuickWenDaActivity extends TakePhotoActivity implements View.OnClickListener  {

    private ImageView ivBack;
    private Button btnOne;
    private Button btnTwo;
    private Button btnThree;
    private Button btnFour;
    private Button btnFive;
    private Button btnSix;
    private List<Button> list;
    private ImgAndCameraHelper imgAndCameraHelper;
    private ImageView ivImg;
    private ImageView ivConfirmImg;
    private TextView tvImg;
    private MyDialog myDialog;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_wenda);

        initView();
        initListener();
        initData();
        imgAndCameraHelper = ImgAndCameraHelper.of();

    }


    public void initView() {
        TextView tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("快速问答");
        ivBack = (ImageView)findViewById(R.id.iv_back);
        btnOne = (Button)findViewById(R.id.btn_one);
        btnTwo = (Button)findViewById(R.id.btn_two_class);
        btnThree = (Button)findViewById(R.id.btn_three);
        btnFour = (Button)findViewById(R.id.btn_four);
        btnFive = (Button)findViewById(R.id.btn_five);
        btnSix = (Button)findViewById(R.id.btn_six);
        ivImg = (ImageView)findViewById(R.id.iv_img);
        ivConfirmImg = (ImageView)findViewById(R.id.iv_confirm_img);
        tvImg = (TextView)findViewById(R.id.tv_img);

    }

    public void initListener() {
        ivBack.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        ivImg.setOnClickListener(this);
        ivConfirmImg.setOnClickListener(this);

    }


    public void initData() {
        list = new ArrayList<Button>();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.btn_one:
                setYysClass(btnOne);
                break;
            case R.id.btn_two_class:
                setYysClass(btnTwo);
                break;
            case R.id.btn_three:
                setYysClass(btnThree);
                break;
            case R.id.btn_four:
                setYysClass(btnFour);
                break;
            case R.id.btn_five:
                setYysClass(btnFive);
                break;
            case R.id.btn_six:
                setYysClass(btnSix);
                break;
            case R.id.iv_img:
            case R.id.iv_confirm_img:
                file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                myDialog = new MyDialog(QuickWenDaActivity.this, 0.2f, 0.6f);
                myDialog.show();
                View view = LayoutInflater.from(QuickWenDaActivity.this).inflate(R.layout.img_and_camera_dialog, null);
                myDialog.addView(view);

                view.findViewById(R.id.btn_sure).setOnClickListener(this);
                view.findViewById(R.id.btn_cancel).setOnClickListener(this);
                break;
            case R.id.btn_sure:


                imgAndCameraHelper.onlick(v, getTakePhoto(), file);
                myDialog.cancel();
                break;
            case R.id.btn_cancel:
                imgAndCameraHelper.onlick(v, getTakePhoto(), file);
                myDialog.cancel();
                break;


            default:

                break;

        }


    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        showImg(result.getImages());
    }

    private void showImg(ArrayList<TImage> images) {

        String compressPath = images.get(0).getOriginalPath();
        Log.e("ddddddddddddddddddddddddd", "showImg: " + compressPath);
        Log.e("ddddddddddddddddddddddddd", "file: " + file);
        ivImg.setVisibility(View.GONE);
        tvImg.setVisibility(View.GONE);
            Glide.with(this).load(new File(images.get(images.size() - 1).getCompressPath())).into(ivConfirmImg);


    }

    /**
     * 营养选择
     * @param btn
     */
    public void setYysClass(Button btn){

        btnOne.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
        btnOne.setTextColor(getResources().getColor(R.color.text_grey));
        btnTwo.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
        btnTwo.setTextColor(getResources().getColor(R.color.text_grey));
        btnThree.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
        btnThree.setTextColor(getResources().getColor(R.color.text_grey));
        btnFour.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
        btnFour.setTextColor(getResources().getColor(R.color.text_grey));
        btnFive.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
        btnFive.setTextColor(getResources().getColor(R.color.text_grey));
        btnSix.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
        btnSix.setTextColor(getResources().getColor(R.color.text_grey));

        if (list.size()==0){
            btn .setBackgroundResource(R.drawable.bg_shape_pink_shixin_corners_small);
            btn.setTextColor(getResources().getColor(R.color.white));

            list.add(btn);
        }else {
            if (list.get(0)==btn){
                btn.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
                btn.setTextColor(getResources().getColor(R.color.text_grey));
                list.clear();
            }else {
                list.clear();
                btn .setBackgroundResource(R.drawable.bg_shape_pink_shixin_corners_small);
                btn.setTextColor(getResources().getColor(R.color.white));
                list.add(btn);
            }
        }
    }


}
