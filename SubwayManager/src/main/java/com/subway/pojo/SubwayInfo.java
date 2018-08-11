package com.subway.pojo;

/**
 * 地铁线路信息类
 */
public class SubwayInfo {
    private Integer id;
    private String subwayName;
    private String startStation;
    private String endStation;
    private Integer  stationNum;
    private String startTime;
    private Integer price;

    @Override
    public String toString() {
        return "SubwayInfo{" +
                "id=" + id +
                ", subwayName='" + subwayName + '\'' +
                ", startStation='" + startStation + '\'' +
                ", endStation='" + endStation + '\'' +
                ", stationNum=" + stationNum +
                ", startTime='" + startTime + '\'' +
                ", price=" + price +
                '}';
    }

    public SubwayInfo() {
    }

    public SubwayInfo(Integer id, String subwayName, String startStation, String endStation, Integer stationNum, String startTime, Integer price) {
        this.id = id;
        this.subwayName = subwayName;
        this.startStation = startStation;
        this.endStation = endStation;
        this.stationNum = stationNum;
        this.startTime = startTime;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubwayName() {
        return subwayName;
    }

    public void setSubwayName(String subwayName) {
        this.subwayName = subwayName;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public Integer getStationNum() {
        return stationNum;
    }

    public void setStationNum(Integer stationNum) {
        this.stationNum = stationNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
