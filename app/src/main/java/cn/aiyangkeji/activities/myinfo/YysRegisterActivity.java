package cn.aiyangkeji.activities.myinfo;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aiyangkeji.R;

import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.app.AppConfig;
import cn.aiyangkeji.bean.YysRegisterBean;
import cn.aiyangkeji.newwork.MyAPI;
import cn.aiyangkeji.newwork.MyBaseSubscriber;
import cn.aiyangkeji.newwork.MyRetrofit;
import cn.aiyangkeji.util.ImgAndCameraHelper;
import cn.aiyangkeji.util.JsontoRequestBody;
import cn.aiyangkeji.util.UserInfoUtil;
import cn.aiyangkeji.view.MyDialog;

/**
 * Created by chenzhikai on 2017/11/9.
 * 营养师入驻注册
 */

public class YysRegisterActivity extends TakePhotoActivity implements View.OnClickListener {

    private TextView tvAgree;
    private EditText etPhoneNum;
    private EditText etrRealName;
    private EditText etSchool;
    private EditText etGoodAt;
    private EditText etPorermance;
    private EditText etExprience;
    private Button btnOne;
    private Button btnThree;
    private Button btnTwoClass;
    private Button btnClinic;
    private Button btnAci;
    private Button btnYqYy;
    private Button btnYqFy;
    private Button btnYqRs;
    private Button btnMrWy;
    private Button btnChFp;
    private Button btnYzSp;
    private Button btnYyeWy;
    private Button btnFsTj;
    private Button btnYsTy;
    private Button btnCzFy;
    private Button btnYyGy;
    private Button btnRtYsTy;
    private Button btnZYy;
    private Button btnJfSs;
    private Button btnSx;
    private Button btTnb;
    private Button btnXzYc;
    private Button btnMbTl;
    private FrameLayout flHeadPhoto;
    private ImageView ivheadPhoto;
    private FrameLayout flYysZhengshu;
    private ImageView ivYysZhengShu;
    private FrameLayout flIdCradZ;
    private ImageView ivIdCardZ;
    private FrameLayout flIdCardF;
    private ImageView ivIdCardF;
    private ImageView ivCheck;
    private TextView tvComplete;
    private ImageView ivBack;
    private int CHECK_OR_NO_CHECK = 0;
    private List<Button> list;
    private List<Button> listYqYy;
    private List<Button> listBuRuQiYy;
    private List<Button> listYingYouErYy;
    private List<Button> listErTongYy;
    private List<Button> listYunDongYy;
    private List<Button> listSanGaoYy;
    private Map<String, List<Button>> map;
    private TextView teYqYy;
    private TextView tvBrqYy;
    private TextView tvYyerYy;
    private TextView tvErTyY;
    private TextView tvYunDYy;
    private TextView tvSanGyY;
    private ImageView ivheadJia;
    private String path;
    private Uri photoUri;
    private String mFileName;

    private ImageView ivZhengmianJia;
    private ImageView ivFanMianJia;
    private ImageView ivCertifcate;
    private ImageView ivCertificateJia;
    private ImageView ivIdCardZm;
    private ImageView ivIdCardFm;
    private String mFileYysZhengshuName;
    private Map<String, Object> parameters = new HashMap<String, Object>();

    private int ivstatus = 0;
    private RelativeLayout rlBottom;
    private MyDialog myDialog;
    private ImgAndCameraHelper imgAndCameraHelper;

    private File file;
    private File file1;
    private File newFile;
    private ImgAndCameraHelper imgAndCameraHelper1;
    private ImgAndCameraHelper helper;
    private File file2;
    private File file3;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yys_register);

        initView();
        initListener();
        initData();
        imgAndCameraHelper = ImgAndCameraHelper.of();
        imgAndCameraHelper1 = ImgAndCameraHelper.of();
        //分别代表地下四个上传图片的文件


    }


    public void initView() {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("营养师入驻");
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvAgree = (TextView) findViewById(R.id.tv_agree);
        tvAgree.setText("点击完成认证代表您已阅读并同意《营养师入驻协议》");
        SpannableStringBuilder style = new SpannableStringBuilder("点击完成认证代表您已阅读并同意《营养师入驻协议》");
        style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.xieyi)), 15, 24, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvAgree.setText(style);
        etPhoneNum = (EditText) findViewById(R.id.et_phonenum);
        etrRealName = (EditText) findViewById(R.id.et_real_name);
        etSchool = (EditText) findViewById(R.id.et_school);
        etGoodAt = (EditText) findViewById(R.id.et_good_at);
        etPorermance = (EditText) findViewById(R.id.et_porfermance);
        etExprience = (EditText) findViewById(R.id.et_experience);
        btnOne = (Button) findViewById(R.id.btn_one);
        btnTwoClass = (Button) findViewById(R.id.btn_two_class);
        btnThree = (Button) findViewById(R.id.btn_three);
        btnClinic = (Button) findViewById(R.id.btn_linchuang_yys);
        btnAci = (Button) findViewById(R.id.btn_aci);
        btnYqYy = (Button) findViewById(R.id.btn_yq_yy);
        btnYqFy = (Button) findViewById(R.id.btn_yq_fy);
        btnYqRs = (Button) findViewById(R.id.btn_yq_rs);
        btnMrWy = (Button) findViewById(R.id.btn_mrwy);
        btnChFp = (Button) findViewById(R.id.btn_chfp);
        btnYzSp = (Button) findViewById(R.id.btn_yzsp);
        btnYyeWy = (Button) findViewById(R.id.btn_yyewy);
        btnFsTj = (Button) findViewById(R.id.btn_fstj);
        btnYsTy = (Button) findViewById(R.id.btn_ysty);
        btnCzFy = (Button) findViewById(R.id.btn_czfy);
        btnYyGy = (Button) findViewById(R.id.btn_yygy);
        btnRtYsTy = (Button) findViewById(R.id.btn_etyy_ysty);
        btnZYy = (Button) findViewById(R.id.btn_zjyy);
        btnJfSs = (Button) findViewById(R.id.btn_jfss);
        btnSx = (Button) findViewById(R.id.btn_sx);
        btTnb = (Button) findViewById(R.id.btn_tnb);
        btnXzYc = (Button) findViewById(R.id.btn_xzyc);
        btnMbTl = (Button) findViewById(R.id.btn_mbtl);
        flHeadPhoto = (FrameLayout) findViewById(R.id.fl_head_photo);
        ivheadJia = (ImageView) findViewById(R.id.iv_head_jia);
        ivheadPhoto = (ImageView) findViewById(R.id.iv_head_photo);
        flYysZhengshu = (FrameLayout) findViewById(R.id.fl_yys_certificate);
        ivYysZhengShu = (ImageView) findViewById(R.id.iv_yys_certificate);
        flIdCradZ = (FrameLayout) findViewById(R.id.fl_idcard_zhengmian);
        ivIdCardZ = (ImageView) findViewById(R.id.iv_idcard_zhengm);
        flIdCardF = (FrameLayout) findViewById(R.id.fl_idcard_fanmian);
        ivIdCardF = (ImageView) findViewById(R.id.iv_idcard__fanm);
        ivCheck = (ImageView) findViewById(R.id.iv_check);
        tvComplete = (TextView) findViewById(R.id.tv_complete);
        //擅长领域标题
        teYqYy = (TextView) findViewById(R.id.tv_yuqi_yy);
        tvBrqYy = (TextView) findViewById(R.id.tv_brq_yy);
        tvYyerYy = (TextView) findViewById(R.id.tv_yye_yy);
        tvErTyY = (TextView) findViewById(R.id.tv_et_yy);
        tvYunDYy = (TextView) findViewById(R.id.tv_yd_yy);
        tvSanGyY = (TextView) findViewById(R.id.tv_sg_yy);
        ivCertifcate = (ImageView) findViewById(R.id.iv_yys_certificate);
        ivCertificateJia = (ImageView) findViewById(R.id.iv_certifcate_jia);
        ivIdCardZm = (ImageView) findViewById(R.id.iv_idcard_zhengm);
        ivZhengmianJia = (ImageView) findViewById(R.id.iv_zhengmian_jia);
        ivIdCardFm = (ImageView) findViewById(R.id.iv_idcard__fanm);
        ivFanMianJia = (ImageView) findViewById(R.id.iv_fanmian_jia);
        //底部完成
        rlBottom = (RelativeLayout) findViewById(R.id.rl_bottom);
    }


    public void initListener() {
        ivBack.setOnClickListener(this);
        tvAgree.setOnClickListener(this);
        etPhoneNum.setOnClickListener(this);
        etrRealName.setOnClickListener(this);
        etSchool.setOnClickListener(this);
        etGoodAt.setOnClickListener(this);
        etPorermance.setOnClickListener(this);
        etExprience.setOnClickListener(this);
        btnOne.setOnClickListener(this);

        btnTwoClass.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnClinic.setOnClickListener(this);
        btnAci.setOnClickListener(this);
        btnYqYy.setOnClickListener(this);
        btnYqFy.setOnClickListener(this);
        btnYqRs.setOnClickListener(this);
        btnMrWy.setOnClickListener(this);
        btnChFp.setOnClickListener(this);
        btnYzSp.setOnClickListener(this);
        btnYyeWy.setOnClickListener(this);
        btnFsTj.setOnClickListener(this);
        btnYsTy.setOnClickListener(this);
        btnCzFy.setOnClickListener(this);
        btnYyGy.setOnClickListener(this);
        btnRtYsTy.setOnClickListener(this);
        btnZYy.setOnClickListener(this);
        btnJfSs.setOnClickListener(this);
        btnSx.setOnClickListener(this);
        btTnb.setOnClickListener(this);
        btnXzYc.setOnClickListener(this);
        btnMbTl.setOnClickListener(this);
        flHeadPhoto.setOnClickListener(this);
        ivheadPhoto.setOnClickListener(this);
        flYysZhengshu.setOnClickListener(this);
        ivYysZhengShu.setOnClickListener(this);
        flIdCradZ.setOnClickListener(this);
        ivIdCardZ.setOnClickListener(this);
        flIdCardF.setOnClickListener(this);
        ivIdCardF.setOnClickListener(this);
        ivCheck.setOnClickListener(this);
        tvComplete.setOnClickListener(this);

        ivheadJia.setOnClickListener(this);
        ivCertificateJia.setOnClickListener(this);
        ivZhengmianJia.setOnClickListener(this);
        ivFanMianJia.setOnClickListener(this);
        rlBottom.setOnClickListener(this);
    }


    public void initData() {
        list = new ArrayList<Button>();
        listYqYy = new ArrayList<Button>();
        listBuRuQiYy = new ArrayList<Button>();
        listYingYouErYy = new ArrayList<Button>();
        listErTongYy = new ArrayList<Button>();
        listYunDongYy = new ArrayList<Button>();
        listSanGaoYy = new ArrayList<Button>();
        map = new HashMap<String, List<Button>>();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_agree:
                Intent intent = new Intent(this, AiYangXieYiActivity.class);
                startActivity(intent);
                break;
            case R.id.et_phonenum:

                break;
            case R.id.et_real_name:

                break;
            case R.id.et_school:

                break;
            case R.id.et_good_at:

                break;
            case R.id.et_porfermance:

                break;
            case R.id.et_experience:

                break;
            case R.id.btn_one:
                setYysClass(btnOne);

                break;
            case R.id.btn_two_class:
                setYysClass(btnTwoClass);


                break;
            case R.id.btn_three:
                setYysClass(btnThree);


                break;
            case R.id.btn_linchuang_yys:
                setYysClass(btnClinic);

                break;
            case R.id.btn_aci:
                setYysClass(btnAci);

                break;
            case R.id.btn_yq_rs:
            case R.id.btn_yq_fy:
            case R.id.btn_yq_yy:
                if (v.getId() == R.id.btn_yq_fy) {
                    btn = btnYqFy;
                } else if (v.getId() == R.id.btn_yq_yy) {
                    btn = btnYqYy;
                } else if (v.getId() == R.id.btn_yq_rs) {
                    btn = btnYqRs;
                }

                if (map.get("1") == null) {
                    if (map.size() >= 3) {
                        showShortMsg("您歇会");

                    } else {
                        beGoodAtAll(listYqYy, btn);
                        map.put("1", listYqYy);
                    }
                } else {
                    beGoodAtAll(listYqYy, btn);

                    if (listYqYy.size() == 0) {
                        map.remove("1");
                    } else {
                        map.put("1", listYqYy);
                    }
                }
                changTitle(listYqYy, teYqYy);


                break;
            case R.id.btn_yzsp:
            case R.id.btn_chfp:
            case R.id.btn_mrwy:
                if (v.getId() == R.id.btn_chfp) {
                    btn = btnChFp;
                } else if (v.getId() == R.id.btn_mrwy) {
                    btn = btnMrWy;
                } else if (v.getId() == R.id.btn_yzsp) {
                    btn = btnYzSp;
                }
                if (map.get("2") == null) {
                    if (map.size() >= 3) {
                        showShortMsg("您歇会");
                    } else {
                        beGoodAtAll(listBuRuQiYy, btn);
                        map.put("2", listBuRuQiYy);
                    }
                } else {
                    beGoodAtAll(listBuRuQiYy, btn);
                    if (listBuRuQiYy.size() == 0) {
                        map.remove("2");
                    } else {
                        map.put("2", listBuRuQiYy);
                    }
                }
                changTitle(listBuRuQiYy, tvBrqYy);


                break;
            case R.id.btn_ysty:
            case R.id.btn_fstj:
            case R.id.btn_yyewy:
                if (v.getId() == R.id.btn_fstj) {
                    btn = btnFsTj;
                } else if (v.getId() == R.id.btn_yyewy) {
                    btn = btnYyeWy;
                } else if (v.getId() == R.id.btn_ysty) {
                    btn = btnYsTy;
                }

                if (map.get("3") == null) {
                    if (map.size() >= 3) {
                        showShortMsg("您歇会");
                    } else {
                        beGoodAtAll(listYingYouErYy, btn);
                        map.put("3", listYingYouErYy);
                    }
                } else {
                    beGoodAtAll(listYingYouErYy, btn);
                    if (listYingYouErYy.size() == 0) {
                        map.remove("3");
                    } else {
                        map.put("3", listYingYouErYy);
                    }

                }
                changTitle(listYingYouErYy, tvYyerYy);

                break;
            case R.id.btn_etyy_ysty:
            case R.id.btn_yygy:
            case R.id.btn_czfy:
                if (v.getId()==R.id.btn_yygy){
                    btn=btnYyGy;
                }else if (v.getId()==R.id.btn_czfy){
                    btn=btnCzFy;
                }else if (v.getId()==R.id.btn_etyy_ysty){
                    btn= btnRtYsTy;
                }

                if (map.get("4") == null) {
                    if (map.size() >= 3) {
                        showShortMsg("您歇会");
                    } else {
                        beGoodAtAll(listErTongYy, btn);
                        map.put("4", listErTongYy);
                    }
                } else {
                    beGoodAtAll(listErTongYy, btn);
                    if (listErTongYy.size() == 0) {
                        map.remove("4");
                    } else {
                        map.put("4", listErTongYy);
                    }
                }

                changTitle(listErTongYy, tvErTyY);
                break;
            case R.id.btn_sx:
            case R.id.btn_jfss:
            case R.id.btn_zjyy:
                if (v.getId()==R.id.btn_sx){
                    btn=btnSx;
                }else if (v.getId()==R.id.btn_jfss){
                    btn=btnJfSs;
                }else if (v.getId()==R.id.btn_zjyy){
                    btn=btnZYy;
                }

                if (map.get("5") == null) {
                    if (map.size() >= 3) {
                        showShortMsg("您歇会");
                    } else {
                        beGoodAtAll(listYunDongYy, btn);
                        map.put("5", listYunDongYy);
                    }
                } else {
                    beGoodAtAll(listYunDongYy, btn);

                    if (listYunDongYy.size() == 0) {
                        map.remove("5");
                    } else {
                        map.put("5", listYunDongYy);
                    }

                }
                changTitle(listYunDongYy, tvYunDYy);
                break;
            case R.id.btn_mbtl:
            case R.id.btn_xzyc:
            case R.id.btn_tnb:
                if (v.getId()==R.id.btn_mbtl){
                    btn=btnMbTl;
                }else if (v.getId()==R.id.btn_xzyc){
                    btn= btnXzYc;
                }else if (v.getId()==R.id.btn_tnb){
                    btn= btTnb;
                }

                if (map.get("6") == null) {
                    if (map.size() >= 3) {
                        showShortMsg("您歇会");
                    } else {
                        beGoodAtAll(listSanGaoYy, btn);
                        map.put("6", listSanGaoYy);
                    }
                } else {
                    beGoodAtAll(listSanGaoYy, btn);
                    if (listSanGaoYy.size() == 0) {
                        map.remove("6");
                    } else {
                        map.put("6", listSanGaoYy);
                    }
                }

                changTitle(listSanGaoYy, tvSanGyY);
                break;

            case R.id.fl_head_photo:

                break;
            case R.id.iv_fanmian_jia:
            case R.id.iv_zhengmian_jia:
            case R.id.iv_certifcate_jia:
            case R.id.iv_head_jia:
                file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                file1 = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                file2 = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                file3 = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                if (v.getId() == R.id.iv_certifcate_jia) {
                 //   helper= imgAndCameraHelper;

                    newFile = file1;
                } else if (v.getId() == R.id.iv_head_jia) {
               //      helper= imgAndCameraHelper1;

                    newFile = file;
                } else if (v.getId() == R.id.iv_zhengmian_jia) {


                    newFile = file2;
                } else if (v.getId() == R.id.iv_fanmian_jia) {


                    newFile = file3;
                }

                myDialog = new MyDialog(YysRegisterActivity.this, 0.3f, 0.6f);
                myDialog.show();
                View view = LayoutInflater.from(YysRegisterActivity.this).inflate(R.layout.img_and_camera_dialog, null);
                myDialog.addView(view);
                ((TextView) view.findViewById(R.id.tv_content)).setText("您确定要退出登录吗？");
                view.findViewById(R.id.btn_sure).setOnClickListener(this);
                view.findViewById(R.id.btn_cancel).setOnClickListener(this);
                break;
            case R.id.btn_sure:


                imgAndCameraHelper.onlick(v, getTakePhoto(), newFile);
                myDialog.cancel();
                break;
            case R.id.btn_cancel:
                imgAndCameraHelper.onlick(v, getTakePhoto(), newFile);
                myDialog.cancel();
                break;

            case R.id.fl_yys_certificate:


                break;

            case R.id.fl_idcard_zhengmian:

                break;

            case R.id.fl_idcard_fanmian:

                break;

            case R.id.iv_check:

                if (ivstatus == 0) {
                    ivCheck.setImageResource(R.mipmap.xieyicheck_ok);
                    rlBottom.setBackgroundResource(R.color.pink_word_home);
                    ivstatus = 1;
                } else {
                    ivCheck.setImageResource(R.mipmap.xieyi_check_off);
                    rlBottom.setBackgroundResource(R.color.text_color_grey);
                    ivstatus = 0;
                }
                break;
            case R.id.tv_complete:
                if (ivstatus == 0) {
                    showShortMsg("请先同意协议");
                } else {
                    cmplete();
                }

                break;


        }


    }


    public void showShortMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
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
        Log.e("ddddddddddddddddddddddddd", "file1: " + file1);

        String s = file.toString();
        String s1 = file1.toString();
        String s2 = file2.toString();
        String s3 = file3.toString();
        if (s.equals(compressPath)) {
            Glide.with(this).load(new File(images.get(images.size() - 1).getCompressPath())).into(ivheadPhoto);
        } else if (s1.equals(compressPath)) {
            Glide.with(this).load(new File(images.get(images.size() - 1).getCompressPath())).into(ivCertifcate);

        } else if (s2.equals(compressPath)) {
            Glide.with(this).load(new File(images.get(images.size() - 1).getCompressPath())).into(ivIdCardZm);
        } else if (s3.equals(compressPath)) {
            Glide.with(this).load(new File(images.get(images.size() - 1).getCompressPath())).into(ivIdCardFm);
        }

    }


    private void changTitle(List<Button> list, TextView tv) {
        if (list.size() == 0) {

            tv.setTextColor(getResources().getColor(R.color.text_grey));
        } else {
            tv.setTextColor(getResources().getColor(R.color.pink_word_home));
        }
    }

    private void cmplete() {
        parameters.clear();
        String strRealName = etrRealName.getText().toString().toString();
        String strPhoneNum = etPhoneNum.getText().toString().trim();
        String strSchool = etSchool.getText().toString().trim();
        String strBegoodAt = etGoodAt.getText().toString().trim();
        String strPorermance = etPorermance.getText().toString().trim();
        String strExprience = etExprience.getText().toString().trim();
        if (strRealName.equals("")) {
            showShortMsg("真实姓名不能为空");
            return;
        } else if (strPhoneNum.equals("")) {
            showShortMsg("电话号码不能为空");
            return;
        } else if (strSchool.equals("")) {
            showShortMsg("学校不能为空");
            return;
        } else if (strBegoodAt.equals("")) {
            showShortMsg("个人擅长不能为空");
            return;
        } else if (strPorermance.equals("")) {
            showShortMsg("经验业绩不能为空");
            return;
        } else if (strExprience.equals("")) {
            showShortMsg("工作阅历不能为空");
            return;
        }
        parameters.put("customerId", UserInfoUtil.getUserId(this));
        parameters.put("dietName", strRealName);
        parameters.put("mobile", strPhoneNum);
        parameters.put("education", strSchool);
        parameters.put("goodAt", strBegoodAt);
        parameters.put("description", strPorermance);
        parameters.put("workBackground", strExprience);
        parameters.put("askprice", 0.7);

        if (list.size() == 0) {
            showShortMsg("营养师等级必须选择");
            return;
        }
        if (list.get(0) == btnOne) {
            parameters.put("grade", "一级公共营养");
        } else if (list.get(0) == btnTwoClass) {
            parameters.put("grade", "二级公共营养");
        } else if (list.get(0) == btnThree) {
            parameters.put("grade", "三级公共营养");
        } else if (list.get(0) == btnClinic) {
            parameters.put("grade", "临床营养师");
        } else if (list.get(0) == btnAci) {
            parameters.put("grade", "ACI国际注册营养师");
        }
        List<Button> buttons = map.get("1");
        StringBuilder sb = new StringBuilder();
        String syqyy = "1:";
        if (buttons != null) {
            if (buttons.contains(btnYqYy) && buttons.size() == 1) {
                syqyy = syqyy + "1;";
            }
            if (buttons.contains(btnYqYy) && buttons.size() != 1) {
                syqyy = syqyy + "1,";

            }
            if (buttons.contains(btnYqFy) && buttons.size() == 1) {
                syqyy = syqyy + "2;";
            }
            if (buttons.contains(btnYqFy) && buttons.contains(btnYqRs)) {
                syqyy = syqyy + "2,";
            }
            if (buttons.contains(btnYqRs)) {
                syqyy = syqyy + "3;";
            }
        } else {
            syqyy = "";
        }
        List<Button> buttons2 = map.get("2");
        String sBrqYy = "2:";
        if (buttons2 != null) {
            if (buttons2.contains(btnMrWy) && buttons2.size() == 1) {
                sBrqYy = sBrqYy + "1;";
            }
            if (buttons2.contains(btnMrWy) && buttons2.size() != 1) {
                sBrqYy = sBrqYy + "1,";

            }
            if (buttons2.contains(btnChFp) && buttons2.size() == 1) {
                sBrqYy = sBrqYy + "2;";
            }
            if (buttons2.contains(btnChFp) && buttons2.contains(btnYzSp)) {
                sBrqYy = sBrqYy + "2,";
            }
            if (buttons2.contains(btnYzSp)) {
                sBrqYy = sBrqYy + "3;";
            }
        } else {
            sBrqYy = "";
        }
        List<Button> buttons3 = map.get("3");
        String sYyerYy = "3:";
        if (buttons3 != null) {
            if (buttons3.contains(btnYyeWy) && buttons3.size() == 1) {
                sYyerYy = sYyerYy + "1;";
            }
            if (buttons3.contains(btnYyeWy) && buttons3.size() != 1) {
                sYyerYy = sYyerYy + "1,";

            }
            if (buttons3.contains(btnFsTj) && buttons3.size() == 1) {
                sYyerYy = sYyerYy + "2;";
            }
            if (buttons3.contains(btnFsTj) && buttons3.contains(btnYsTy)) {
                sYyerYy = sYyerYy + "2,";
            }
            if (buttons3.contains(btnYsTy)) {
                sYyerYy = sYyerYy + "3;";
            }
        } else {
            sYyerYy = "";
        }
        List<Button> buttons4 = map.get("4");
        String sEtYy = "4:";
        if (buttons4 != null) {
            if (buttons4.contains(btnCzFy) && buttons4.size() == 1) {
                sEtYy = sEtYy + "1;";
            }
            if (buttons4.contains(btnCzFy) && buttons4.size() != 1) {
                sEtYy = sEtYy + "1,";

            }
            if (buttons4.contains(btnYyGy) && buttons4.size() == 1) {
                sEtYy = sEtYy + "2;";
            }
            if (buttons4.contains(btnYyGy) && buttons4.contains(btnRtYsTy)) {
                sEtYy = sEtYy + "2,";
            }
            if (buttons4.contains(btnRtYsTy)) {
                sEtYy = sEtYy + "3;";
            }
        } else {
            sEtYy = "";
        }

        List<Button> buttons5 = map.get("5");
        String sYdYy = "5:";
        if (buttons5 != null) {
            if (buttons5.contains(btnZYy) && buttons5.size() == 1) {
                sYdYy = sYdYy + "1;";
            }
            if (buttons5.contains(btnZYy) && buttons5.size() != 1) {
                sYdYy = sYdYy + "1,";

            }
            if (buttons5.contains(btnJfSs) && buttons5.size() == 1) {
                sYdYy = sYdYy + "2;";
            }
            if (buttons5.contains(btnJfSs) && buttons5.contains(btnSx)) {
                sYdYy = sYdYy + "2,";
            }
            if (buttons5.contains(btnSx)) {
                sYdYy = sYdYy + "3;";
            }
        } else {
            sYdYy = "";
        }
        List<Button> buttons6 = map.get("6");
        String sSgYy = "6:";
        if (buttons6 != null) {
            if (buttons6.contains(btTnb) && buttons6.size() == 1) {
                sSgYy = sSgYy + "1;";
            }
            if (buttons6.contains(btTnb) && buttons6.size() != 1) {
                sSgYy = sSgYy + "1,";

            }
            if (buttons6.contains(btnXzYc) && buttons6.size() == 1) {
                sSgYy = sSgYy + "2;";
            }
            if (buttons6.contains(btnXzYc) && buttons6.contains(btnMbTl)) {
                sSgYy = sSgYy + "2,";
            }
            if (buttons6.contains(btnMbTl)) {
                sSgYy = sSgYy + "3;";
            }
        } else {
            sSgYy = "";
        }

        String s = sb.append(syqyy).append(sBrqYy).append(sYyerYy).append(sEtYy).append(sYdYy).append(sSgYy).toString();
        parameters.put("goodType", s);
        parameters.put("mgpic", "");
        parameters.put("zspic", "");
        parameters.put("zhengpic", "");
        parameters.put("fanpic", "");
        // showShortMsg(s);


        Novate novate = new MyRetrofit(this).getMyApiService();
        MyAPI myAPI = novate.create(MyAPI.class);

        novate.call(myAPI.yYsRegister(JsontoRequestBody.parameters2json(parameters)), new MyBaseSubscriber<YysRegisterBean>(this) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(YysRegisterBean s) {
                showShortMsg("haole ");
                setResult(AppConfig.LOGIN_RESULT_SUCESS_CODE);
                YysRegisterActivity.this.finish();
            }
        });


    }

    /**
     * 营养师等级
     *
     * @param btn
     */
    public void setYysClass(Button btn) {

        btnOne.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
        btnOne.setTextColor(getResources().getColor(R.color.text_grey));
        btnTwoClass.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
        btnTwoClass.setTextColor(getResources().getColor(R.color.text_grey));
        btnThree.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
        btnThree.setTextColor(getResources().getColor(R.color.text_grey));
        btnClinic.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
        btnClinic.setTextColor(getResources().getColor(R.color.text_grey));
        btnAci.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
        btnAci.setTextColor(getResources().getColor(R.color.text_grey));

        if (list.size() == 0) {
            btn.setBackgroundResource(R.drawable.bg_shape_pink_shixin_corners_small);
            btn.setTextColor(getResources().getColor(R.color.white));

            list.add(btn);
        } else {
            if (list.get(0) == btn) {
                btn.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
                btn.setTextColor(getResources().getColor(R.color.text_grey));
                list.clear();
            } else {
                list.clear();
                btn.setBackgroundResource(R.drawable.bg_shape_pink_shixin_corners_small);
                btn.setTextColor(getResources().getColor(R.color.white));
                list.add(btn);
            }
        }
    }


    public void beGoodAtAll(List<Button> list, Button btn) {

        if (list.contains(btn)) {

            btn.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
            btn.setTextColor(getResources().getColor(R.color.text_grey));
            list.remove(btn);

        } else {
            btn.setBackgroundResource(R.drawable.bg_shape_pink_shixin_corners_small);
            btn.setTextColor(getResources().getColor(R.color.white));
            list.add(btn);
        }

    }

}
