package com.example.dllo.yuliaoapp.model.bean;

import java.util.List;

/**
 * Created by Yu on 16/10/24.
 */
public class L_WeatherBean {

    /**
     * reason : successed!
     * result : {"data":{"realtime":{"city_code":"101210701","city_name":"温州","date":"2016-10-26","time":"16:00:00","week":3,"moon":"九月廿六","dataUptime":1477470965,"weather":{"temperature":"29","humidity":"75","info":"多云","img":"1"},"wind":{"direct":"西风","power":"1级","offset":null,"windspeed":null}},"life":{"date":"2016-10-26","info":{"chuanyi":["热","天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"],"ganmao":["少发","各项气象条件适宜，无明显降温过程，发生感冒机率较低。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"yundong":["较适宜","天气较好，户外运动请注意防晒。推荐您进行室内运动。"],"ziwaixian":["弱","紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"]}},"weather":[{"date":"2016-10-26","info":{"day":["1","多云","29","东北风","微风","06:04"],"night":["1","多云","23","东北风","微风","17:17"]},"week":"三","nongli":"九月廿六"},{"date":"2016-10-27","info":{"dawn":["1","多云","23","东北风","微风","17:17"],"day":["1","多云","29","东北风","微风","06:05"],"night":["1","多云","23","东北风","微风","17:17"]},"week":"四","nongli":"九月廿七"},{"date":"2016-10-28","info":{"dawn":["1","多云","23","东北风","微风","17:17"],"day":["3","阵雨","31","东北风","微风","06:06"],"night":["3","阵雨","21","东北风","微风","17:16"]},"week":"五","nongli":"九月廿八"},{"date":"2016-10-29","info":{"dawn":["3","阵雨","21","东北风","微风","17:16"],"day":["3","阵雨","25","东北风","微风","06:06"],"night":["2","阴","20","东北风","微风","17:15"]},"week":"六","nongli":"九月廿九"},{"date":"2016-10-30","info":{"dawn":["2","阴","20","东北风","微风","17:15"],"day":["1","多云","23","东北风","微风","06:07"],"night":["1","多云","18","东北风","微风","17:14"]},"week":"日","nongli":"九月三十"}],"f3h":{"temperature":[{"jg":"20161026170000","jb":"29"},{"jg":"20161026200000","jb":"25"},{"jg":"20161026230000","jb":"24"},{"jg":"20161027020000","jb":"24"},{"jg":"20161027050000","jb":"23"},{"jg":"20161027080000","jb":"24"},{"jg":"20161027110000","jb":"28"},{"jg":"20161027140000","jb":"29"},{"jg":"20161027170000","jb":"26"}],"precipitation":[{"jg":"20161026170000","jf":"0"},{"jg":"20161026200000","jf":"0"},{"jg":"20161026230000","jf":"0"},{"jg":"20161027020000","jf":"0"},{"jg":"20161027050000","jf":"0"},{"jg":"20161027080000","jf":"0"},{"jg":"20161027110000","jf":"0"},{"jg":"20161027140000","jf":"0"},{"jg":"20161027170000","jf":"0"}]},"pm25":{"key":"Wenzhou","show_desc":0,"pm25":{"curPm":"63","pm25":"34","pm10":"70","level":2,"quality":"良","des":"可以接受的，除极少数对某种污染物特别敏感的人以外，对公众健康没有危害。"},"dateTime":"2016年10月26日16时","cityName":"温州"},"jingqu":"","jingqutq":"","date":"","isForeign":"0"}}
     * error_code : 0
     */

    private String reason;
    /**
     * data : {"realtime":{"city_code":"101210701","city_name":"温州","date":"2016-10-26","time":"16:00:00","week":3,"moon":"九月廿六","dataUptime":1477470965,"weather":{"temperature":"29","humidity":"75","info":"多云","img":"1"},"wind":{"direct":"西风","power":"1级","offset":null,"windspeed":null}},"life":{"date":"2016-10-26","info":{"chuanyi":["热","天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"],"ganmao":["少发","各项气象条件适宜，无明显降温过程，发生感冒机率较低。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"yundong":["较适宜","天气较好，户外运动请注意防晒。推荐您进行室内运动。"],"ziwaixian":["弱","紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"]}},"weather":[{"date":"2016-10-26","info":{"day":["1","多云","29","东北风","微风","06:04"],"night":["1","多云","23","东北风","微风","17:17"]},"week":"三","nongli":"九月廿六"},{"date":"2016-10-27","info":{"dawn":["1","多云","23","东北风","微风","17:17"],"day":["1","多云","29","东北风","微风","06:05"],"night":["1","多云","23","东北风","微风","17:17"]},"week":"四","nongli":"九月廿七"},{"date":"2016-10-28","info":{"dawn":["1","多云","23","东北风","微风","17:17"],"day":["3","阵雨","31","东北风","微风","06:06"],"night":["3","阵雨","21","东北风","微风","17:16"]},"week":"五","nongli":"九月廿八"},{"date":"2016-10-29","info":{"dawn":["3","阵雨","21","东北风","微风","17:16"],"day":["3","阵雨","25","东北风","微风","06:06"],"night":["2","阴","20","东北风","微风","17:15"]},"week":"六","nongli":"九月廿九"},{"date":"2016-10-30","info":{"dawn":["2","阴","20","东北风","微风","17:15"],"day":["1","多云","23","东北风","微风","06:07"],"night":["1","多云","18","东北风","微风","17:14"]},"week":"日","nongli":"九月三十"}],"f3h":{"temperature":[{"jg":"20161026170000","jb":"29"},{"jg":"20161026200000","jb":"25"},{"jg":"20161026230000","jb":"24"},{"jg":"20161027020000","jb":"24"},{"jg":"20161027050000","jb":"23"},{"jg":"20161027080000","jb":"24"},{"jg":"20161027110000","jb":"28"},{"jg":"20161027140000","jb":"29"},{"jg":"20161027170000","jb":"26"}],"precipitation":[{"jg":"20161026170000","jf":"0"},{"jg":"20161026200000","jf":"0"},{"jg":"20161026230000","jf":"0"},{"jg":"20161027020000","jf":"0"},{"jg":"20161027050000","jf":"0"},{"jg":"20161027080000","jf":"0"},{"jg":"20161027110000","jf":"0"},{"jg":"20161027140000","jf":"0"},{"jg":"20161027170000","jf":"0"}]},"pm25":{"key":"Wenzhou","show_desc":0,"pm25":{"curPm":"63","pm25":"34","pm10":"70","level":2,"quality":"良","des":"可以接受的，除极少数对某种污染物特别敏感的人以外，对公众健康没有危害。"},"dateTime":"2016年10月26日16时","cityName":"温州"},"jingqu":"","jingqutq":"","date":"","isForeign":"0"}
     */

    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * realtime : {"city_code":"101210701","city_name":"温州","date":"2016-10-26","time":"16:00:00","week":3,"moon":"九月廿六","dataUptime":1477470965,"weather":{"temperature":"29","humidity":"75","info":"多云","img":"1"},"wind":{"direct":"西风","power":"1级","offset":null,"windspeed":null}}
         * life : {"date":"2016-10-26","info":{"chuanyi":["热","天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"],"ganmao":["少发","各项气象条件适宜，无明显降温过程，发生感冒机率较低。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"yundong":["较适宜","天气较好，户外运动请注意防晒。推荐您进行室内运动。"],"ziwaixian":["弱","紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"]}}
         * weather : [{"date":"2016-10-26","info":{"day":["1","多云","29","东北风","微风","06:04"],"night":["1","多云","23","东北风","微风","17:17"]},"week":"三","nongli":"九月廿六"},{"date":"2016-10-27","info":{"dawn":["1","多云","23","东北风","微风","17:17"],"day":["1","多云","29","东北风","微风","06:05"],"night":["1","多云","23","东北风","微风","17:17"]},"week":"四","nongli":"九月廿七"},{"date":"2016-10-28","info":{"dawn":["1","多云","23","东北风","微风","17:17"],"day":["3","阵雨","31","东北风","微风","06:06"],"night":["3","阵雨","21","东北风","微风","17:16"]},"week":"五","nongli":"九月廿八"},{"date":"2016-10-29","info":{"dawn":["3","阵雨","21","东北风","微风","17:16"],"day":["3","阵雨","25","东北风","微风","06:06"],"night":["2","阴","20","东北风","微风","17:15"]},"week":"六","nongli":"九月廿九"},{"date":"2016-10-30","info":{"dawn":["2","阴","20","东北风","微风","17:15"],"day":["1","多云","23","东北风","微风","06:07"],"night":["1","多云","18","东北风","微风","17:14"]},"week":"日","nongli":"九月三十"}]
         * f3h : {"temperature":[{"jg":"20161026170000","jb":"29"},{"jg":"20161026200000","jb":"25"},{"jg":"20161026230000","jb":"24"},{"jg":"20161027020000","jb":"24"},{"jg":"20161027050000","jb":"23"},{"jg":"20161027080000","jb":"24"},{"jg":"20161027110000","jb":"28"},{"jg":"20161027140000","jb":"29"},{"jg":"20161027170000","jb":"26"}],"precipitation":[{"jg":"20161026170000","jf":"0"},{"jg":"20161026200000","jf":"0"},{"jg":"20161026230000","jf":"0"},{"jg":"20161027020000","jf":"0"},{"jg":"20161027050000","jf":"0"},{"jg":"20161027080000","jf":"0"},{"jg":"20161027110000","jf":"0"},{"jg":"20161027140000","jf":"0"},{"jg":"20161027170000","jf":"0"}]}
         * pm25 : {"key":"Wenzhou","show_desc":0,"pm25":{"curPm":"63","pm25":"34","pm10":"70","level":2,"quality":"良","des":"可以接受的，除极少数对某种污染物特别敏感的人以外，对公众健康没有危害。"},"dateTime":"2016年10月26日16时","cityName":"温州"}
         * jingqu :
         * jingqutq :
         * date :
         * isForeign : 0
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * city_code : 101210701
             * city_name : 温州
             * date : 2016-10-26
             * time : 16:00:00
             * week : 3
             * moon : 九月廿六
             * dataUptime : 1477470965
             * weather : {"temperature":"29","humidity":"75","info":"多云","img":"1"}
             * wind : {"direct":"西风","power":"1级","offset":null,"windspeed":null}
             */

            private RealtimeBean realtime;
            /**
             * date : 2016-10-26
             * info : {"chuanyi":["热","天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"],"ganmao":["少发","各项气象条件适宜，无明显降温过程，发生感冒机率较低。"],"kongtiao":["较少开启","您将感到很舒适，一般不需要开启空调。"],"xiche":["较适宜","较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"],"yundong":["较适宜","天气较好，户外运动请注意防晒。推荐您进行室内运动。"],"ziwaixian":["弱","紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"]}
             */

            private LifeBean life;
            private F3hBean f3h;
            /**
             * key : Wenzhou
             * show_desc : 0
             * pm25 : {"curPm":"63","pm25":"34","pm10":"70","level":2,"quality":"良","des":"可以接受的，除极少数对某种污染物特别敏感的人以外，对公众健康没有危害。"}
             * dateTime : 2016年10月26日16时
             * cityName : 温州
             */

            private Pm25Bean pm25;
            private String jingqu;
            private String jingqutq;
            private String date;
            private String isForeign;
            /**
             * date : 2016-10-26
             * info : {"day":["1","多云","29","东北风","微风","06:04"],"night":["1","多云","23","东北风","微风","17:17"]}
             * week : 三
             * nongli : 九月廿六
             */

            private List<WeatherBean> weather;

            public RealtimeBean getRealtime() {
                return realtime;
            }

            public void setRealtime(RealtimeBean realtime) {
                this.realtime = realtime;
            }

            public LifeBean getLife() {
                return life;
            }

            public void setLife(LifeBean life) {
                this.life = life;
            }

            public F3hBean getF3h() {
                return f3h;
            }

            public void setF3h(F3hBean f3h) {
                this.f3h = f3h;
            }

            public Pm25Bean getPm25() {
                return pm25;
            }

            public void setPm25(Pm25Bean pm25) {
                this.pm25 = pm25;
            }

            public String getJingqu() {
                return jingqu;
            }

            public void setJingqu(String jingqu) {
                this.jingqu = jingqu;
            }

            public String getJingqutq() {
                return jingqutq;
            }

            public void setJingqutq(String jingqutq) {
                this.jingqutq = jingqutq;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getIsForeign() {
                return isForeign;
            }

            public void setIsForeign(String isForeign) {
                this.isForeign = isForeign;
            }

            public List<WeatherBean> getWeather() {
                return weather;
            }

            public void setWeather(List<WeatherBean> weather) {
                this.weather = weather;
            }

            public static class RealtimeBean {
                private String city_code;
                private String city_name;
                private String date;
                private String time;
                private int week;
                private String moon;
                private int dataUptime;
                /**
                 * temperature : 29
                 * humidity : 75
                 * info : 多云
                 * img : 1
                 */

                private WeatherBean weather;
                /**
                 * direct : 西风
                 * power : 1级
                 * offset : null
                 * windspeed : null
                 */

                private WindBean wind;

                public String getCity_code() {
                    return city_code;
                }

                public void setCity_code(String city_code) {
                    this.city_code = city_code;
                }

                public String getCity_name() {
                    return city_name;
                }

                public void setCity_name(String city_name) {
                    this.city_name = city_name;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public int getWeek() {
                    return week;
                }

                public void setWeek(int week) {
                    this.week = week;
                }

                public String getMoon() {
                    return moon;
                }

                public void setMoon(String moon) {
                    this.moon = moon;
                }

                public int getDataUptime() {
                    return dataUptime;
                }

                public void setDataUptime(int dataUptime) {
                    this.dataUptime = dataUptime;
                }

                public WeatherBean getWeather() {
                    return weather;
                }

                public void setWeather(WeatherBean weather) {
                    this.weather = weather;
                }

                public WindBean getWind() {
                    return wind;
                }

                public void setWind(WindBean wind) {
                    this.wind = wind;
                }

                public static class WeatherBean {
                    private String temperature;
                    private String humidity;
                    private String info;
                    private String img;

                    public String getTemperature() {
                        return temperature;
                    }

                    public void setTemperature(String temperature) {
                        this.temperature = temperature;
                    }

                    public String getHumidity() {
                        return humidity;
                    }

                    public void setHumidity(String humidity) {
                        this.humidity = humidity;
                    }

                    public String getInfo() {
                        return info;
                    }

                    public void setInfo(String info) {
                        this.info = info;
                    }

                    public String getImg() {
                        return img;
                    }

                    public void setImg(String img) {
                        this.img = img;
                    }
                }

                public static class WindBean {
                    private String direct;
                    private String power;
                    private Object offset;
                    private Object windspeed;

                    public String getDirect() {
                        return direct;
                    }

                    public void setDirect(String direct) {
                        this.direct = direct;
                    }

                    public String getPower() {
                        return power;
                    }

                    public void setPower(String power) {
                        this.power = power;
                    }

                    public Object getOffset() {
                        return offset;
                    }

                    public void setOffset(Object offset) {
                        this.offset = offset;
                    }

                    public Object getWindspeed() {
                        return windspeed;
                    }

                    public void setWindspeed(Object windspeed) {
                        this.windspeed = windspeed;
                    }
                }
            }

            public static class LifeBean {
                private String date;
                private InfoBean info;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public InfoBean getInfo() {
                    return info;
                }

                public void setInfo(InfoBean info) {
                    this.info = info;
                }

                public static class InfoBean {
                    private List<String> chuanyi;
                    private List<String> ganmao;
                    private List<String> kongtiao;
                    private List<String> xiche;
                    private List<String> yundong;
                    private List<String> ziwaixian;

                    public List<String> getChuanyi() {
                        return chuanyi;
                    }

                    public void setChuanyi(List<String> chuanyi) {
                        this.chuanyi = chuanyi;
                    }

                    public List<String> getGanmao() {
                        return ganmao;
                    }

                    public void setGanmao(List<String> ganmao) {
                        this.ganmao = ganmao;
                    }

                    public List<String> getKongtiao() {
                        return kongtiao;
                    }

                    public void setKongtiao(List<String> kongtiao) {
                        this.kongtiao = kongtiao;
                    }

                    public List<String> getXiche() {
                        return xiche;
                    }

                    public void setXiche(List<String> xiche) {
                        this.xiche = xiche;
                    }

                    public List<String> getYundong() {
                        return yundong;
                    }

                    public void setYundong(List<String> yundong) {
                        this.yundong = yundong;
                    }

                    public List<String> getZiwaixian() {
                        return ziwaixian;
                    }

                    public void setZiwaixian(List<String> ziwaixian) {
                        this.ziwaixian = ziwaixian;
                    }
                }
            }

            public static class F3hBean {
                /**
                 * jg : 20161026170000
                 * jb : 29
                 */

                private List<TemperatureBean> temperature;
                /**
                 * jg : 20161026170000
                 * jf : 0
                 */

                private List<PrecipitationBean> precipitation;

                public List<TemperatureBean> getTemperature() {
                    return temperature;
                }

                public void setTemperature(List<TemperatureBean> temperature) {
                    this.temperature = temperature;
                }

                public List<PrecipitationBean> getPrecipitation() {
                    return precipitation;
                }

                public void setPrecipitation(List<PrecipitationBean> precipitation) {
                    this.precipitation = precipitation;
                }

                public static class TemperatureBean {
                    private String jg;
                    private String jb;

                    public String getJg() {
                        return jg;
                    }

                    public void setJg(String jg) {
                        this.jg = jg;
                    }

                    public String getJb() {
                        return jb;
                    }

                    public void setJb(String jb) {
                        this.jb = jb;
                    }
                }

                public static class PrecipitationBean {
                    private String jg;
                    private String jf;

                    public String getJg() {
                        return jg;
                    }

                    public void setJg(String jg) {
                        this.jg = jg;
                    }

                    public String getJf() {
                        return jf;
                    }

                    public void setJf(String jf) {
                        this.jf = jf;
                    }
                }
            }

            public static class Pm25Bean {
                private String key;
                private int show_desc;
                /**
                 * curPm : 63
                 * pm25 : 34
                 * pm10 : 70
                 * level : 2
                 * quality : 良
                 * des : 可以接受的，除极少数对某种污染物特别敏感的人以外，对公众健康没有危害。
                 */

                private mPm25Bean pm25;
                private String dateTime;
                private String cityName;

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public int getShow_desc() {
                    return show_desc;
                }

                public void setShow_desc(int show_desc) {
                    this.show_desc = show_desc;
                }

                public mPm25Bean getPm25() {
                    return pm25;
                }

                public void setPm25(mPm25Bean pm25) {
                    this.pm25 = pm25;
                }

                public String getDateTime() {
                    return dateTime;
                }

                public void setDateTime(String dateTime) {
                    this.dateTime = dateTime;
                }

                public String getCityName() {
                    return cityName;
                }

                public void setCityName(String cityName) {
                    this.cityName = cityName;
                }

                public static class mPm25Bean {
                    private String curPm;
                    private String pm25;
                    private String pm10;
                    private int level;
                    private String quality;
                    private String des;

                    public String getCurPm() {
                        return curPm;
                    }

                    public void setCurPm(String curPm) {
                        this.curPm = curPm;
                    }

                    public String getPm25() {
                        return pm25;
                    }

                    public void setPm25(String pm25) {
                        this.pm25 = pm25;
                    }

                    public String getPm10() {
                        return pm10;
                    }

                    public void setPm10(String pm10) {
                        this.pm10 = pm10;
                    }

                    public int getLevel() {
                        return level;
                    }

                    public void setLevel(int level) {
                        this.level = level;
                    }

                    public String getQuality() {
                        return quality;
                    }

                    public void setQuality(String quality) {
                        this.quality = quality;
                    }

                    public String getDes() {
                        return des;
                    }

                    public void setDes(String des) {
                        this.des = des;
                    }
                }
            }

            public static class WeatherBean {
                private String date;
                private InfoBean info;
                private String week;
                private String nongli;

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public InfoBean getInfo() {
                    return info;
                }

                public void setInfo(InfoBean info) {
                    this.info = info;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getNongli() {
                    return nongli;
                }

                public void setNongli(String nongli) {
                    this.nongli = nongli;
                }

                public static class InfoBean {
                    private List<String> day;
                    private List<String> night;

                    public List<String> getDay() {
                        return day;
                    }

                    public void setDay(List<String> day) {
                        this.day = day;
                    }

                    public List<String> getNight() {
                        return night;
                    }

                    public void setNight(List<String> night) {
                        this.night = night;
                    }
                }
            }
        }
    }
}
