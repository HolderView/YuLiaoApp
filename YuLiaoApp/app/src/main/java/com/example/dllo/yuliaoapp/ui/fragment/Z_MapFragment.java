package com.example.dllo.yuliaoapp.ui.fragment;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.renderscript.Byte2;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.model.bean.L_WeatherBean;
import com.example.dllo.yuliaoapp.model.net.L_OkHttpInstanceUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dllo on 16/10/19.
 *
 * @author 赵玲琳
 *         地图页
 *         操作人:刘航
 */
public class Z_MapFragment extends C_AbsBaseFragment implements View.OnClickListener {

    private LineChartView lineChart;

    private String WEATHERURLSTART = "http://op.juhe.cn/onebox/weather/query?cityname=";
    private String cityname = "大连市";
    private String appKey = "&key=525119bb600fc0297952b6beaae30634";
    private String strUrl;
    private TextView timeTv;
    private TextView nowweaTv;
    private TextView windTv;
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
    private TextView presentTime;
    private L_WeatherBean bean;
    private Gson gson;
    private String[] date;
    private int[] weather;
    private TextView chuanyiTv;
    private TextView chuanyiconTv;
    private TextView ganmaoTv;
    private TextView ganmaoconTv;
    private TextView kongtiaoTv;
    private TextView kongtiaoconTv;
    private TextView xicheTv;
    private TextView xicheconTv;
    private TextView yundongTv;
    private TextView yundongconTv;
    private TextView zwxTv;
    private TextView zwxconTv;
    private TextView placeTv;
    private View popview;
    private PopupWindow popupWindow;
    private boolean isPop = false;
    private TextView weekThree;
    private TextView weekFour;
    private TextView weekFive;
    private TextView beijingTv;
    private TextView shanghaiTv;
    private TextView guangzhouTv;
    private TextView nanjingTv;
    private TextView hangzhouTv;


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

        lineChart = byView(R.id.z_fragment_map_linechart);
        timeTv = byView(R.id.fragment_wheather_time_tv);
        nowweaTv = byView(R.id.z_fragment_map_weather_nowwea_tv);
        windTv = byView(R.id.l_fragment_wheather_wind_tv);
        presentTime = byView(R.id.z_fragment_map_presenttime_tv);
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
        chuanyiTv = byView(R.id.l_tab_fragment_map_chuanyititle_content_tv);
        chuanyiconTv = byView(R.id.l_tab_fragment_map_chuanyicontent_tv);
        ganmaoTv = byView(R.id.l_tab_fragment_map_title_content_ganmaotv);
        ganmaoconTv = byView(R.id.l_tab_fragment_map_content_ganmaotv);
        kongtiaoTv = byView(R.id.l_tab_fragment_map_title_content_kongtiaotv);
        kongtiaoconTv = byView(R.id.l_tab_fragment_map_content_kongtiaotv);
        xicheTv = byView(R.id.l_tab_fragment_map_title_content_xichetv);
        xicheconTv = byView(R.id.l_tab_fragment_map_content_xichetv);
        yundongTv = byView(R.id.l_tab_fragment_map_title_content_yundongtv);
        yundongconTv = byView(R.id.l_tab_fragment_map_content_yundongtv);
        zwxTv = byView(R.id.l_tab_fragment_map_title_content_zwxtv);
        zwxconTv = byView(R.id.l_tab_fragment_map_content_zwxtv);
        placeTv = byView(R.id.l_fragment_wheather_place_tv);
        weekThree = byView(R.id.z_fragment_map_weather_weekthree_tv);
        weekFour = byView(R.id.z_fragment_map_weather_weekfour_tv);
        weekFive = byView(R.id.z_fragment_map_weather_weekfive_tv);


        popview = LayoutInflater.from(context).inflate(R.layout.l_popupwindow_place,null);
        popupWindow = new PopupWindow();
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(popview);
        beijingTv = (TextView) popview.findViewById(R.id.beijing_tv);
        shanghaiTv = (TextView) popview.findViewById(R.id.shanghai_tv);
        nanjingTv = (TextView) popview.findViewById(R.id.nanjing_tv);
        guangzhouTv = (TextView) popview.findViewById(R.id.guangzhou_tv);
        hangzhouTv = (TextView) popview.findViewById(R.id.hangzhou_tv);
        beijingTv.setOnClickListener(this);
        shanghaiTv.setOnClickListener(this);
        nanjingTv.setOnClickListener(this);
        guangzhouTv.setOnClickListener(this);
        hangzhouTv.setOnClickListener(this);



    }

    @Override
    protected void initDatas() {
        placeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPop == false) {
                    popupWindow.showAsDropDown(placeTv);
                    isPop = true;
                }else {
                    popupWindow.dismiss();
                    isPop = false;
                }

            }
        });


        weatherForecast();






    }

    private void weatherForecast() {
        try {
            strUrl = URLDecoder.decode(cityname, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        L_OkHttpInstanceUtil.getAsyn(WEATHERURLSTART + strUrl + appKey, new L_OkHttpInstanceUtil.ResultCallback() {

            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(Object response) {

                gson = new Gson();
                bean = gson.fromJson((String) response, L_WeatherBean.class);

                String time = String.valueOf(bean.getResult().getData().getRealtime().getDataUptime());

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

                long times = Long.valueOf(time);

                String finaltimes = sdf.format(times);


                presentTime.setText(bean.getResult().getData().getRealtime().getWeather().getTemperature() + "°");
                timeTv.setText(finaltimes + "发布");
                nowweaTv.setText(bean.getResult().getData().getRealtime().getWeather().getInfo());
                windTv.setText(bean.getResult().getData().getRealtime().getWind().getDirect() + bean.getResult().getData().getRealtime().getWind().getPower());
                airsum.setText(bean.getResult().getData().getPm25().getPm25().getCurPm());
                airqualityTv.setText(bean.getResult().getData().getPm25().getPm25().getQuality());
                todayDetailTv.setText(bean.getResult().getData().getWeather().get(0).getInfo().getDay().get(1));
                todayCalendarTv.setText(bean.getResult().getData().getWeather().get(0).getDate());
                todayTemperTv.setText(bean.getResult().getData().getWeather().get(0).getInfo().getNight().get(2) + "~" + bean.getResult().getData().getWeather().get(0).getInfo().getDay().get(2));
                tomodetail.setText(bean.getResult().getData().getWeather().get(1).getInfo().getDay().get(1));
                tomocalendar.setText(bean.getResult().getData().getWeather().get(1).getDate());
                tomotemper.setText(bean.getResult().getData().getWeather().get(1).getInfo().getNight().get(2) + "~" + bean.getResult().getData().getWeather().get(1).getInfo().getDay().get(2));
                threeDetail.setText(bean.getResult().getData().getWeather().get(2).getInfo().getDay().get(1));
                threeCalendar.setText(bean.getResult().getData().getWeather().get(2).getDate());
                threeTemper.setText(bean.getResult().getData().getWeather().get(2).getInfo().getNight().get(2) + "~" + bean.getResult().getData().getWeather().get(2).getInfo().getDay().get(2));
                fourDetail.setText(bean.getResult().getData().getWeather().get(3).getInfo().getDay().get(1));
                fourCalendar.setText(bean.getResult().getData().getWeather().get(3).getDate());
                fourTemper.setText(bean.getResult().getData().getWeather().get(3).getInfo().getNight().get(2) + "~" + bean.getResult().getData().getWeather().get(3).getInfo().getDay().get(2));
//                fiveDetail.setText(bean.getResult().getData().getWeather().get(4).getInfo().getDay().get(1));
//                fiveCalendar.setText(bean.getResult().getData().getWeather().get(4).getDate());
//                fiveTemper.setText(bean.getResult().getData().getWeather().get(4).getInfo().getNight().get(2) + "~" + bean.getResult().getData().getWeather().get(4).getInfo().getDay().get(2));
                chuanyiTv.setText(bean.getResult().getData().getLife().getInfo().getChuanyi().get(0));
                chuanyiconTv.setText(bean.getResult().getData().getLife().getInfo().getChuanyi().get(1));
                ganmaoTv.setText(bean.getResult().getData().getLife().getInfo().getGanmao().get(0));
                ganmaoconTv.setText(bean.getResult().getData().getLife().getInfo().getGanmao().get(1));
                kongtiaoTv.setText(bean.getResult().getData().getLife().getInfo().getKongtiao().get(0));
                kongtiaoconTv.setText(bean.getResult().getData().getLife().getInfo().getKongtiao().get(1));
                xicheTv.setText(bean.getResult().getData().getLife().getInfo().getXiche().get(0));
                xicheconTv.setText(bean.getResult().getData().getLife().getInfo().getXiche().get(1));
                yundongTv.setText(bean.getResult().getData().getLife().getInfo().getYundong().get(0));
                yundongconTv.setText(bean.getResult().getData().getLife().getInfo().getYundong().get(1));
                zwxTv.setText(bean.getResult().getData().getLife().getInfo().getZiwaixian().get(0));
                zwxconTv.setText(bean.getResult().getData().getLife().getInfo().getZiwaixian().get(1));
                weekThree.setText(bean.getResult().getData().getWeather().get(2).getWeek());
                weekFour.setText(bean.getResult().getData().getWeather().get(3).getWeek());




                long time1 = Long.valueOf(bean.getResult().getData().getF3h().getTemperature().get(0).getJg());
//                long finaltimesx1 = (time1%1000000)/100;
                String finaltimes1 = sdf.format(time1);


                long time2 = Long.valueOf(bean.getResult().getData().getF3h().getTemperature().get(1).getJg());
//                long finaltimesx2 = (time2%1000000)/100;
                String finaltimes2 = sdf.format(time2);



                long time3 = Long.valueOf(bean.getResult().getData().getF3h().getTemperature().get(2).getJg());
                long finaltimesx3 = (time3%1000000)/100;
                String finaltimes3 = sdf.format(finaltimesx3);



                long time4 = Long.valueOf(bean.getResult().getData().getF3h().getTemperature().get(3).getJg());
                long finaltimesx4 = (time4%1000000)/100;
                String finaltimes4 = sdf.format(finaltimesx4);



                long time5 = Long.valueOf(bean.getResult().getData().getF3h().getTemperature().get(4).getJg());

                long finaltimesx5 = (time5%1000000)/100;
                String finaltimes5 = sdf.format(finaltimesx5);


                long time6 = Long.valueOf(bean.getResult().getData().getF3h().getTemperature().get(5).getJg());

                long finaltimesx6 = (time6%1000000)/100;
                String finaltimes6 = sdf.format(finaltimesx6);


                long time7 = Long.valueOf(bean.getResult().getData().getF3h().getTemperature().get(6).getJg());
                long finaltimesx7 = (time7%1000000)/100;

                String finaltimes7 = sdf.format(finaltimesx7);


                long time8 = Long.valueOf(bean.getResult().getData().getF3h().getTemperature().get(7).getJg());

                long finaltimesx8 = (time8%1000000)/100;

                String finaltimes8 = sdf.format(finaltimesx8);


                //X轴的标注
                date = new String[]{String.valueOf(finaltimes1),
                        String.valueOf(finaltimes2),
                        String.valueOf(finaltimes3),
                        String.valueOf(finaltimes4),
                        String.valueOf(finaltimes5),
                        String.valueOf(finaltimes6),
                        String.valueOf(finaltimes7),
                        String.valueOf(finaltimes8),
                };


                //图表的数据点
                weather = new int[]{Integer.parseInt(bean.getResult().getData().getF3h().getTemperature().get(0).getJb()),
                        Integer.parseInt(bean.getResult().getData().getF3h().getTemperature().get(1).getJb()),
                        Integer.parseInt(bean.getResult().getData().getF3h().getTemperature().get(2).getJb()),
                        Integer.parseInt(bean.getResult().getData().getF3h().getTemperature().get(3).getJb()),
                        Integer.parseInt(bean.getResult().getData().getF3h().getTemperature().get(4).getJb()),
                        Integer.parseInt(bean.getResult().getData().getF3h().getTemperature().get(5).getJb()),
                        Integer.parseInt(bean.getResult().getData().getF3h().getTemperature().get(6).getJb()),
                        Integer.parseInt(bean.getResult().getData().getF3h().getTemperature().get(7).getJb())
                };
                lineChartView();
            }
        });
    }


    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();


    private void lineChartView() {
        getAxisXLables();//获取x轴的标注
        getAxisPoints();//获取坐标点
        initLineChart();//初始化


    }

    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables() {
        for (int i = 0; i < 8; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }

    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints() {
        for (int i = 0; i < 8; i++) {
            mPointValues.add(new PointValue(i, weather[i]));

        }
    }


    private void initLineChart() {
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//      line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.WHITE);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边


        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 2);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right = 7;
        lineChart.setCurrentViewport(v);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.beijing_tv:
                placeTv.setText("北京市");
                cityname = "北京市";
                weatherForecast();
                popupWindow.dismiss();
                break;
            case R.id.shanghai_tv:
                placeTv.setText("上海市");
                cityname = "上海";
                weatherForecast();
                popupWindow.dismiss();
            break;
            case R.id.nanjing_tv:
                placeTv.setText("南京市");
                cityname = "南京";
                weatherForecast();
                popupWindow.dismiss();
            break;
            case R.id.guangzhou_tv:
                placeTv.setText("广州市");
                cityname = "广州";
                weatherForecast();
                popupWindow.dismiss();
                break;
            case R.id.hangzhou_tv:
                placeTv.setText("杭州市");
                cityname = "北京市";
                weatherForecast();
                popupWindow.dismiss();
                break;


        }


    }
}
