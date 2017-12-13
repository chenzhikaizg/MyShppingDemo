package cn.aiyangkeji.view.picker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CityWorker {

	private CityDataHelper cityData;
	private SQLiteDatabase db;

	public CityWorker(Context context) {
		cityData = new CityDataHelper(context);
		cityData.openDatabase();
		db = cityData.getDatabase();
	}

	public List<CityMode> getProvince() {
		List<CityMode> list = new ArrayList<CityMode>();
		Cursor cursor = null;
		try {
			String sql = "select * from ShopProvince";
			cursor = db.rawQuery(sql, null);		 
			while (cursor.moveToNext()) {		 
				CityMode proc = new CityMode();
				proc.setName(cursor.getString(cursor.getColumnIndex("name")));
				proc.setPcode(cursor.getString(cursor.getColumnIndex("id")));
				list.add(proc);
			 
			} 		 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(cursor != null){
				cursor.close();
			}
		}
		return list;
	}

	public List<CityMode> getCityByProvince(String pcode) {
		List<CityMode> list = new ArrayList<CityMode>();
		Cursor cursor = null;
		try {
			String sql = "select * from ShopCity where province_id='" + pcode + "'";
			cursor = db.rawQuery(sql, null);			 
			while (cursor.moveToNext()) {//		 
				CityMode city = new CityMode();
				city.setName(cursor.getString(cursor.getColumnIndex("name")));
				city.setPcode(cursor.getString(cursor.getColumnIndex("id")));
				list.add(city);			
			} 

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(cursor != null){
				cursor.close();
			}
		}
		return list;
	}

	public List<CityMode> getAreaByCity(String city) {
		List<CityMode> list = new ArrayList<CityMode>();
		Cursor cursor = null;
		try {
			String sql = "select * from ShopCountry where city_id='" + city + "'";
			cursor = db.rawQuery(sql, null);			 
			while (cursor.moveToNext()) { 		 
				CityMode area = new CityMode();
				area.setName(cursor.getString(cursor.getColumnIndex("name")));
				area.setPcode(cursor.getString(cursor.getColumnIndex("id")));
				list.add(area);			 
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(cursor != null){
				cursor.close();
			}
		}
		return list;
	}

	public void closeCityWorker() {
		cityData.closeDatabase();
		db.close();
	}

}
