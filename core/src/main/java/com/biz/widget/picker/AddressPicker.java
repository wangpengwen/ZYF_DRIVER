package com.biz.widget.picker;


import com.biz.util.Lists;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * 地址选择器（包括省级、地级、县级）。
 */
public class AddressPicker extends WheelPicker {
    private ArrayList<String> provinceList = new ArrayList<String>();
    private ArrayList<ArrayList<String>> cityList = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<ArrayList<String>>> areaList = new ArrayList<ArrayList<ArrayList<String>>>();
    private OnAddressPickListener onAddressPickListener;
    private String selectedProvince = "", selectedCity = "", selectedCounty = "";
    private int selectedProvinceIndex = 0, selectedCityIndex = 0, selectedCountyIndex = 0;
    private boolean hideProvince = false, hideCounty;
    private WheelView provinceView, cityView, countyView;

    /**
     * Instantiates a new Address picker.
     *
     * @param activity the activity
     */
    public AddressPicker(Activity activity, List<ProvinceEntity> list) {
        super(activity);
        //添加省
        provinceList = Lists.newArrayList();
        for (ProvinceEntity p : list) {
            provinceList.add(p.getName());
            ArrayList<ProvinceEntity.City> cities = p.getCities();
            ArrayList<String> xCities = new ArrayList<String>();
            ArrayList<ArrayList<String>> xCounties = new ArrayList<ArrayList<String>>();

            //添加地市
            for (ProvinceEntity.City c : cities) {
                xCities.add(c.getName());
                ArrayList<ProvinceEntity.City.Districty> counties = c.getDistricts();
                ArrayList<String> yCounties = new ArrayList<String>();
                int countySize = counties.size();
                //添加区县
                if (countySize == 0) {
                    yCounties.add(c.getName());
                } else {
                    for (int z = 0; z < countySize; z++) {
                        yCounties.add(counties.get(z).getName());
                    }
                }
                xCounties.add(yCounties);
            }
            cityList.add(xCities);
            areaList.add(xCounties);

        }
    }

    /**
     * Sets selected item.
     *
     * @param province the province
     * @param city     the city
     * @param county   the county
     */
    public void setSelectedItem(String province, String city, String county) {
        if (TextUtils.isEmpty(province) || TextUtils.isEmpty(city) || TextUtils.isEmpty(county))
            return;
        for (int i = 0; i < provinceList.size(); i++) {
            String pro = provinceList.get(i);
            if (pro.contains(province)) {
                selectedProvince = pro;
                selectedProvinceIndex = i;
                if (provinceView != null)
                    provinceView.setSelectedItem(selectedProvince);
                break;
            }
        }
        ArrayList<String> cities = cityList.get(selectedProvinceIndex);
        for (int j = 0; j < cities.size(); j++) {
            String cit = cities.get(j);
            if (cit.contains(city)) {
                selectedCity = cit;
                selectedCityIndex = j;
                if (cityView != null)
                    cityView.setSelectedItem(selectedCity);
                break;
            }
        }
        ArrayList<String> counties = areaList.get(selectedProvinceIndex).get(selectedCityIndex);
        for (int k = 0; k < counties.size(); k++) {
            String cou = counties.get(k);
            if (cou.contains(county)) {
                selectedCounty = cou;
                selectedCountyIndex = k;
                if (countyView != null)
                    countyView.setSelectedItem(selectedCounty);
                break;
            }
        }
    }
    public void setSelectedItem(String province, String city) {
        if (TextUtils.isEmpty(province) || TextUtils.isEmpty(city))
            return;
        for (int i = 0; i < provinceList.size(); i++) {
            String pro = provinceList.get(i);
            if (pro.contains(province)) {
                selectedProvince = pro;
                selectedProvinceIndex = i;
                if (provinceView != null)
                    provinceView.setSelectedItem(selectedProvince);
                break;
            }
        }
        ArrayList<String> cities = cityList.get(selectedProvinceIndex);
        for (int j = 0; j < cities.size(); j++) {
            String cit = cities.get(j);
            if (cit.contains(city)) {
                selectedCity = cit;
                selectedCityIndex = j;
                if (cityView != null)
                    cityView.setSelectedItem(selectedCity);
                break;
            }
        }
    }

    /**
     * 隐藏省级行政区，只显示地市级和区县级。
     * 设置为true的话，地址数据中只需要某个省份的即可
     * 参见示例中的“city2.json”
     *
     * @param hideProvince the hide province
     */
    public void setHideProvince(boolean hideProvince) {
        this.hideProvince = hideProvince;
    }

    public void setHideCounty(boolean hideCounty) {
        this.hideCounty = hideCounty;
    }

    /**
     * Sets on address pick listener.
     *
     * @param listener the listener
     */
    public void setOnAddressPickListener(OnAddressPickListener listener) {
        this.onAddressPickListener = listener;
    }

    @Override
    @NonNull
    protected View makeCenterView() {
        if (provinceList.size() == 0) {
            throw new IllegalArgumentException("please initial options at first, can't be empty");
        }
        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);
        provinceView = new WheelView(activity);
        provinceView.setLayoutParams(new LinearLayout.LayoutParams(screenWidthPixels / 3, WRAP_CONTENT));
        provinceView.setTextSize(textSize);
        provinceView.setTextColor(textColorNormal, textColorFocus);
        provinceView.setLineVisible(lineVisible);
        provinceView.setLineColor(lineColor);
        provinceView.setOffset(offset);
        layout.addView(provinceView);
        if (hideProvince) {
            provinceView.setVisibility(View.GONE);
        }
        cityView = new WheelView(activity);
        cityView.setLayoutParams(new LinearLayout.LayoutParams(screenWidthPixels / 3, WRAP_CONTENT));
        cityView.setTextSize(textSize);
        cityView.setTextColor(textColorNormal, textColorFocus);
        cityView.setLineVisible(lineVisible);
        cityView.setLineColor(lineColor);
        cityView.setOffset(offset);
        layout.addView(cityView);
        countyView = new WheelView(activity);
        countyView.setLayoutParams(new LinearLayout.LayoutParams(screenWidthPixels / 3, WRAP_CONTENT));
        countyView.setTextSize(textSize);
        countyView.setTextColor(textColorNormal, textColorFocus);
        countyView.setLineVisible(lineVisible);
        countyView.setLineColor(lineColor);
        countyView.setOffset(offset);
        layout.addView(countyView);
        if (hideCounty) {
            countyView.setVisibility(View.GONE);
        }
        provinceView.setItems(provinceList, selectedProvinceIndex);
        provinceView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                selectedProvince = item;
                selectedProvinceIndex = selectedIndex;
                selectedCountyIndex = 0;
                //根据省份获取地市
                cityView.setItems(cityList.get(selectedProvinceIndex), isUserScroll ? 0 : selectedCityIndex);
                //根据地市获取区县
                countyView.setItems(areaList.get(selectedProvinceIndex).get(0), isUserScroll ? 0 : selectedCountyIndex);
            }
        });
        cityView.setItems(cityList.get(selectedProvinceIndex), selectedCityIndex);
        cityView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                selectedCity = item;
                selectedCityIndex = selectedIndex;
                //根据地市获取区县
                if (selectedProvinceIndex < areaList.size() && selectedCityIndex < areaList.get(selectedProvinceIndex).size())
                    countyView.setItems(areaList.get(selectedProvinceIndex).get(selectedCityIndex), isUserScroll ? 0 : selectedCountyIndex);
            }
        });
        countyView.setItems(areaList.get(selectedProvinceIndex).get(selectedCityIndex), selectedCountyIndex);
        countyView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                selectedCounty = item;
                selectedCountyIndex = selectedIndex;
            }
        });
        return layout;
    }

    @Override
    public void onSubmit() {
        if (onAddressPickListener != null) {
            onAddressPickListener.onAddressPicked(selectedProvince, selectedCity, selectedCounty);
            onAddressPickListener.onPicked(selectedProvinceIndex, selectedCityIndex, selectedCountyIndex);
        }
    }

    /**
     * The interface On address pick listener.
     */
    public interface OnAddressPickListener {

        /**
         * On address picked.
         *
         * @param province the province
         * @param city     the city
         * @param county   the county
         */
        void onAddressPicked(String province, String city, String county);

        void onPicked(int i, int j, int p);
    }


}
