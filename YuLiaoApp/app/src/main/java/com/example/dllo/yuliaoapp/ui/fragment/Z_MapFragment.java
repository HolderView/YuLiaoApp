package com.example.dllo.yuliaoapp.ui.fragment;

import android.os.Bundle;
import android.renderscript.Byte2;
import android.util.Log;
import android.widget.TextView;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.model.bean.L_WeatherBean;
import com.example.dllo.yuliaoapp.model.net.L_OkHttpInstanceUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dllo on 16/10/19.
 * @author 赵玲琳
 * 地图页
 * 操作人:刘航
 */
public class Z_MapFragment extends C_AbsBaseFragment{

    private String WEATHERURLSTART = "http://op.juhe.cn/onebox/weather/query?cityname=";
    private String cityname = "北京";
    private String appKey = "&key=525119bb600fc0297952b6beaae30634";
    private String strUrl;
    private TextView warnTv;
    private TextView timeTv;
    private TextView nowweaTv;
    private TextView windTv;
    private TextView morningTv;
    private TextView nightTv;
    private TextView airsum;
    private TextView airqualityTv;
    private TextView todayDetailTv;
    private TextView todayCalendarTv;
    private TextView todayTemperTv;
    private TextView tomodetail;
    private TextView tomocalendar;
    private TextView tomotemper;
    private TextView threeCalendar;
    private TextView threeDetail;
    private TextView threeTemper;
    private TextView fourCalendar;
    private TextView fourDetail;
    private TextView fourTemper;
    private TextView fiveCalendar;
    private TextView fiveDetail;
    private TextView fiveTemper;
    private TextView sixCalendar;
    private TextView sixDetail;
    private TextView sixTemper;

    public static Z_MapFragment newInstance() {

        Bundle args = new Bundle();

        Z_MapFragment fragment = new Z_MapFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayout() {
        return R.layout.z_fragment_map;
    }

    @Override
    protected void initViews() {
        warnTv = byView(R.id.z_fagment_map_weather_warning_tv);
        timeTv = byView(R.id.fragment_wheather_time_tv);
        nowweaTv = byView(R.id.z_fragment_map_weather_nowwea_tv);
        windTv = byView(R.id.l_fragment_wheather_wind_tv);
        morningTv = byView(R.id.fragment_wheather_morning_tv);
        nightTv = byView(R.id.fragment_wheather_night_tv);
        airsum = byView(R.id.z_fragment_map_weather_airsum_tv);
        airqualityTv = byView(R.id.z_fragment_map_weather_airquality_tv);
        todayDetailTv = byView(R.id.z_fragment_map_weather_todaydetail_tv);
        todayCalendarTv = byView(R.id.z_fragment_map_weather_today_calendar_tv);
        todayTemperTv = byView(R.id.z_fragment_map_weather_today_temper_tv);
        tomodetail = byView(R.id.z_fragment_map_weather_tomorrowdetail_tv);
        tomocalendar = byView(R.id.z_fragment_map_weather_tomorrow_calendar_tv);
        tomotemper = byView(R.id.z_fragment_map_weather_tomorrow_temper_tv);
        threeCalendar = byView(R.id.z_fragment_map_weather_three_calendar_tv);
        threeDetail = byView(R.id.z_fragment_map_weather_threedetail_tv);
        threeTemper = byView(R.id.z_fragment_map_weather_three_temper_tv);
        fourCalendar = byView(R.id.z_fragment_map_weather_four_calendar_tv);
        fourDetail = byView(R.id.z_fragment_map_weather_fourdetail_tv);
        fourTemper = byView(R.id.z_fragment_map_weather_four_temper_tv);
        fiveCalendar = byView(R.id.z_fragment_map_weather_five_calendar_tv);
        fiveDetail = byView(R.id.z_fragment_map_weather_fivedetail_tv);
        fiveTemper = byView(R.id.z_fragment_map_weather_five_temper_tv);
        sixCalendar = byView(R.id.z_fragment_map_weather_six_calendar_tv);
        sixDetail = byView(R.id.z_fragment_map_weather_sixdetail_tv);
        sixTemper = byView(R.id.z_fragment_map_weather_six_temper_tv);

        Log.d("Z_MapFragment", WEATHERURLSTART + strUrl + appKey);
    }

    @Override
    protected void initDatas() {



        try {
            strUrl = URLDecoder.decode(cityname,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        L_OkHttpInstanceUtil.getAsyn(WEATHERURLSTART + strUrl + appKey, new L_OkHttpInstanceUtil.ResultCallback() {

            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Object response) {

                Gson gson = new Gson();
                L_WeatherBean bean = gson.fromJson((String) response,L_WeatherBean.class);

                timeTv.setText(bean.getResult().getData().getRealtime().getDataUptime()+"更新");
                nowweaTv.setText(bean.getResult().getData().getRealtime().getWeather().getInfo());
                windTv.setText(bean.getResult().getData().getRealtime().getWind().getDirect()+bean.getResult().getData().getRealtime().getWind().getPower());
                morningTv.setText(bean.getResult().getData().getWeather().get(0).getInfo().getNight().get(2));
                nightTv.setText(bean.getResult().getData().getWeather().get(0).getInfo().getDay().get(2));
                airsum.setText(bean.getResult().getData().getPm25().getPm25().getCurPm());
                airqualityTv.setText(bean.getResult().getData().getPm25().getPm25().getQuality());
                todayDetailTv.setText(bean.getResult().getData().getWeather().get(0).getInfo().getDay().get(1));
                todayCalendarTv.setText(bean.getResult().getData().getWeather().get(0).getDate());
                todayTemperTv.setText(bean.getResult().getData().getWeather().get(0).getInfo().getNight().get(2)+"~"+bean.getResult().getData().getWeather().get(0).getInfo().getDay().get(2));
                tomodetail.setText(bean.getResult().getData().getWeather().get(1).getInfo().getDay().get(1));
                tomocalendar.setText(bean.getResult().getData().getWeather().get(1).getDate());
                tomotemper.setText(bean.getResult().getData().getWeather().get(1).getInfo().getNight().get(2)+"~"+bean.getResult().getData().getWeather().get(1).getInfo().getDay().get(2));
                threeDetail.setText(bean.getResult().getData().getWeather().get(2).getInfo().getDay().get(1));
                threeCalendar.setText(bean.getResult().getData().getWeather().get(2).getDate());
                threeTemper.setText(bean.getResult().getData().getWeather().get(2).getInfo().getNight().get(2)+"~"+bean.getResult().getData().getWeather().get(2).getInfo().getDay().get(2));
                fourDetail.setText(bean.getResult().getData().getWeather().get(3).getInfo().getDay().get(1));
                fourCalendar.setText(bean.getResult().getData().getWeather().get(3).getDate());
                fourTemper.setText(bean.getResult().getData().getWeather().get(3).getInfo().getNight().get(2)+"~"+bean.getResult().getData().getWeather().get(3).getInfo().getDay().get(2));
                fiveDetail.setText(bean.getResult().getData().getWeather().get(4).getInfo().getDay().get(1));
                fiveCalendar.setText(bean.getResult().getData().getWeather().get(4).getDate());
                fiveTemper.setText(bean.getResult().getData().getWeather().get(4).getInfo().getNight().get(2)+"~"+bean.getResult().getData().getWeather().get(4).getInfo().getDay().get(2));


            }
        });






    }
}
