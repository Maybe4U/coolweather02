package com.android.coolweather02.util;

import android.text.TextUtils;

import com.android.coolweather02.db.City;
import com.android.coolweather02.db.County;
import com.android.coolweather02.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/11/14.
 */
public class Utility {
    /**
     *      解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for(int i=0;i<allProvinces.length();i++){
                    JSONObject provincesObject =allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provincesObject.getString("name"));
                    province.setProvinceCode(provincesObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     *      解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response,int provinceCode){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCities = new JSONArray(response);
                for(int i=0;i<allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceCode);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     *      解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response,int cityCode){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);
                for(int i=0;i<allCounties.length();i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    //county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityCode);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
