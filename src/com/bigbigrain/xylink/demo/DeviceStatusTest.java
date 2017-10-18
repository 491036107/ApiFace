package com.bigbigrain.xylink.demo;

import com.xylink.model.DeviceInfo;
import com.xylink.sdk.device.DeviceStatusApi;
import com.xylink.util.Result;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DeviceStatusTest
 * @Description: 设备相关信息
 * @author B1gB1gRAin
 * @date 2017年10月18日 上午9:36:34
 *
 */
public class DeviceStatusTest {
    
	//当前设备相关信息如下
	//callState:idle;deviceSn:8D1725088C00E18B;number:496329
	
	/**
     * @Title: getDeviceInfo
     * @Description: 按照nemoNo
     * @author B1gB1gRAin
	 * @throws IOException 
     * @date 2017年10月18日 上午9:30:29 
     *
     */
    @Test
    public void getDeviceInfo() throws IOException{
        DeviceStatusApi dsa=new DeviceStatusApi();
        String number="496329";
        Result<DeviceInfo> result = dsa.getDeviceInfo(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, number);
        System.out.println(result);
    }
    
    /**
     * @Title: getDeviceInfos
     * @Description: 获取所有鱼类状态
     * @author B1gB1gRAin
     * @throws IOException 
     * @date 2017年10月18日 上午9:30:48 
     *
     */
    @Test
    public void getDeviceInfos() throws IOException{
        DeviceStatusApi dsa=new DeviceStatusApi();
        	Result<DeviceInfo[]> result = dsa.getDeviceInfo(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN);
        	DeviceInfo[] deviceInfos = result.getData();
        	for(DeviceInfo deviceInfo : deviceInfos){
        		System.out.println("callState:"+deviceInfo.getCallState()+";deviceSn:"+deviceInfo.getDeviceSN()+";number:"+deviceInfo.getNumber());
        	}
    }
    
    /**
     * @Title: getDeviceInfoBySn
     * @Description:通过sn列表查询设备信息 
     * @author B1gB1gRAin
     * @throws IOException 
     * @date 2017年10月18日 上午9:31:17 
     *
     */
    @Test
    public void getDeviceInfoBySn() throws IOException{
        DeviceStatusApi dsa=new DeviceStatusApi();
        List<String> snLst=new ArrayList<String>();
        snLst.add("8D1725088C00E18B");
        Result<DeviceInfo[]> result = dsa.getDeviceInfo(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, snLst);
        DeviceInfo[] deviceInfos = result.getData();
    	for(DeviceInfo deviceInfo : deviceInfos){
    		System.out.println("callState:"+deviceInfo.getCallState()+";deviceSn:"+deviceInfo.getDeviceSN()+";number:"+deviceInfo.getNumber());
    	}
    }
}
