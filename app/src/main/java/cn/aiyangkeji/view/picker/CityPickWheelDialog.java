package cn.aiyangkeji.view.picker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.util.StringUtil;
import cn.aiyangkeji.util.UIHelper;

public class CityPickWheelDialog extends Dialog implements OnClickListener, OnWheelChangedListener {

	public CityPickWheelDialog(Context context, int theme) {
		super(context, theme);
		this.mContext = context;
		cityWorker = new CityWorker(context);
	}

	private Context mContext;
	private WheelView wvProvince;
	private WheelView wvCity;
	private WheelView wvArea;

	private TextView btn_cancel;
	private TextView btn_sure;

	private CityButtonListener listener;
	private CityWorker cityWorker;
	private CityAdapter cityAdapter;
	private CityAdapter provinceAdapter;
	private CityAdapter areaAdapter;
	private List<CityMode> provinceList = new ArrayList<CityMode>();
	private List<CityMode> cityList = new ArrayList<CityMode>();
	private List<CityMode> areaList = new ArrayList<CityMode>();

	private enum adapterType {
		PROVINCE, CITY, AREA;
	}

	public CityPickWheelDialog(Context context) {
		super(context);
		this.mContext = context;
		cityWorker = new CityWorker(context);
	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dialog_city_pick);
		initView();
		setListener();
	}

	public CityPickWheelDialog setCityButtomListener(CityButtonListener listener) {
		this.listener = listener;
		return this;
	}

	private void initView() {
		wvProvince = (WheelView) findViewById(R.id.province_wheel);
		wvCity = (WheelView) findViewById(R.id.city_wheel);
		wvArea = (WheelView) findViewById(R.id.area_wheel);
		btn_sure = (TextView) findViewById(R.id.btn_citypick_sure);
		btn_cancel = (TextView) findViewById(R.id.btn_citypick_cancel);

		int dp_13 = UIHelper.dip2px(mContext, 20);
		wvProvince.TEXT_SIZE = dp_13;
		wvCity.TEXT_SIZE = dp_13;
		wvArea.TEXT_SIZE = dp_13;

		provinceList = cityWorker.getProvince();
		if (provinceList.size() > 0) {
			cityList = cityWorker.getCityByProvince(provinceList.get(2).getPcode());
			wvCity.setCanSrcoll(cityList.size() > 1 ? true : false);
		}
		if (cityList.size() > 0) {
			areaList = cityWorker.getAreaByCity(cityList.get(0).getPcode());
			wvArea.setCanSrcoll(areaList.size() > 1 ? true : false);
		}
		cityAdapter = new CityAdapter(adapterType.CITY);
		provinceAdapter = new CityAdapter(adapterType.PROVINCE);
		areaAdapter = new CityAdapter(adapterType.AREA);

		wvProvince.setAdapter(provinceAdapter);
		wvCity.setAdapter(cityAdapter);
		wvArea.setAdapter(areaAdapter);
        wvProvince.setCurrentItem(2);

    }

	private void setListener() {
		wvProvince.addChangingListener(this);
		wvCity.addChangingListener(this);
		wvArea.addChangingListener(this);
		btn_cancel.setOnClickListener(this);
		btn_sure.setOnClickListener(this);
	}

	/**
	 * ��ȡʡ������
	 * @return
	 */
	public String getProvince(){
		String province = "";
		if (provinceList.size() > 0) {
			province = provinceList.get(wvProvince.getCurrentItem()).getName().trim();
		}
		return province;
	}

	/**
	 * ��ȡʡ��id
	 * @return
	 */
	public int getProvinceCode(){
		String provinceCode = "";
		if (provinceList.size() > 0) {
			provinceCode = provinceList.get(wvProvince.getCurrentItem()).getPcode().trim();
		}
		return Integer.valueOf(provinceCode);
	}

	/**
	 * ��ȡ��������
	 * @return
	 */
	public String getCity(){
		String city = "";
		if (cityList.size() > 0) {
			city = cityList.get(wvCity.getCurrentItem()).getName().trim();
		}
		return city;
	}

	/**
	 * ��ȡ����id
	 * @return
	 */
	public int getCityCode(){
		String cityCode = "";
		if (cityList.size() > 0) {
			cityCode = cityList.get(wvCity.getCurrentItem()).getPcode().trim();
		}
		return Integer.valueOf(cityCode);
	}

   /**
    * ��ȡ��������
    * @return
    */
	public String getArea(){
		String area = "";
		if (areaList.size() > 0) {
			area = areaList.get(wvArea.getCurrentItem()).getName().trim();
		}
		return area;
	}

	 
	/**
	 * ��ȡ����id
	 * @return
	 */
	public int getAreaCode(){
		String areaCode = "";
		if (areaList.size() > 0) {
			areaCode = areaList.get(wvArea.getCurrentItem()).getPcode().trim();
		}
		return Integer.valueOf(areaCode);
	}


	public String getAddress() {
		StringBuilder address = new StringBuilder();
		String province = null;
		String city = null;
		String area = null;
		if (provinceList.size() > 0) {
			province = provinceList.get(wvProvince.getCurrentItem()).getName().trim()+provinceList.get(wvProvince.getCurrentItem()).getPcode();
		}
		if (cityList.size() > 0) {
			city = cityList.get(wvCity.getCurrentItem()).getName().trim()+cityList.get(wvCity.getCurrentItem()).getPcode();
		}
		if (areaList.size() > 0) {
			area = areaList.get(wvArea.getCurrentItem()).getName().trim()+areaList.get(wvArea.getCurrentItem()).getPcode();
			if (area.equals("市辖区")) {
				area = StringUtil.EMPTY;
			}
		}
		if (city.equals(province)) {
			address.append(province);
			if (!StringUtil.isEmpty(area)) {
				address.append(StringUtil.BLANK_SPACE);
				address.append(area);
			}
		} else {
			address.append(province);
			address.append(StringUtil.BLANK_SPACE);
			address.append(city);
			if (!StringUtil.isEmpty(area)) {
				address.append(StringUtil.BLANK_SPACE);
				address.append(area);
            }
		}
		return address.toString();
	}

	@Override
	public void dismiss() {
		super.dismiss();
		cityWorker.closeCityWorker();
	}

	@Override
	public void onClick(View v) {
		if (v == btn_sure) {
			listener.onPositiveButton(this);
		} else if (v == btn_cancel) {
			listener.onNegativeButton(this);
		}
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == wvProvince) {
			cityList = cityWorker.getCityByProvince(provinceList.get(newValue).getPcode());
			wvCity.setCanSrcoll(cityList.size() > 1 ? true : false);
            cityAdapter.notifiDataSetChange();
		} else if (wheel == wvCity) {
			if (newValue < cityList.size()) {
				areaList = cityWorker.getAreaByCity(cityList.get(newValue).getPcode());
				wvArea.setCanSrcoll(areaList.size() > 1 ? true : false);
				areaAdapter.notifiDataSetChange();
			}

		} else if (wheel == wvArea) {

		}
	}


	public interface CityButtonListener {
		public void onPositiveButton(CityPickWheelDialog dialog);

		public void onNegativeButton(CityPickWheelDialog dialog);
	}

	private class CityAdapter implements WheelAdapter {
		List<CityMode> currentList;
		adapterType type;

		public CityAdapter(adapterType type) {
			this.type = type;
			if (type == adapterType.PROVINCE) {
				currentList = provinceList;
			} else if (type == adapterType.CITY) {
				currentList = cityList;
			} else if (type == adapterType.AREA) {
				currentList = areaList;
			}
		}

		public void notifiDataSetChange() {
			if (type == adapterType.PROVINCE) {
				currentList = provinceList;
				wvProvince.setAdapter(this);
			} else if (type == adapterType.CITY) {
				currentList = cityList;
				wvCity.setAdapter(this);
			} else if (type == adapterType.AREA) {
				currentList = areaList;
				wvArea.setAdapter(this);
			}
		}

		@Override
		public int getItemsCount() {
			return currentList.size() == 0 ? 1 : currentList.size();
		}

		@Override
		public String getItem(int index) {
			return currentList.size() == 0 ? null : currentList.get(index).getName();
		}

		@Override
		public int getMaximumLength() {
			return currentList.size() == 0 ? 1 : currentList.size();
		}
	}

}
