package com.bigbigrain.xylink.demo;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.xylink.model.CallInviteRequest;
import com.xylink.model.CurrentMeeting;
import com.xylink.model.Device;
import com.xylink.model.DeviceInfo;
import com.xylink.model.DeviceNotification;
import com.xylink.model.MeetingRoom;
import com.xylink.model.MeetingStatus;
import com.xylink.model.MeetingSubtitle;
import com.xylink.model.MeetingSubtitleRequest;
import com.xylink.model.SdkMeeting;
import com.xylink.model.SdkMeetingReq;
import com.xylink.model.ShareAuthTarget;
import com.xylink.sdk.conferenceControl.ConferenceControlApi;
import com.xylink.sdk.conferenceControl.CreateMeetingApi;
import com.xylink.util.Result;


public class ConferenceControlTest {

	private String callNumber ="913581819843";
	
    private String nemoNumber ="702301";
    
    @Test
    public void testCreateMeeting() throws IOException {
        CreateMeetingApi createMeetingApi = new CreateMeetingApi();
        SdkMeetingReq sdkMeetingReq = new SdkMeetingReq();
        sdkMeetingReq.setMeetingName("zhangy_test");
        SdkMeeting sdkMeeting = createMeetingApi.createMeeting(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, sdkMeetingReq);
        System.out.println(sdkMeeting);
    }
    
    /**
     * @Title: testInviteCall
     * @Description:邀请终端入会测试 
     * @author B1gB1gRAin
     * @throws IOException 
     * @date 2017年10月17日 下午5:27:21 
     *
     */
    @Test
    public void testInviteCall() throws IOException {
        ConferenceControlApi cca=new ConferenceControlApi();

        CallInviteRequest callInviteRequest=new CallInviteRequest();
        List<DeviceInfo> devices=new ArrayList<DeviceInfo>();
        DeviceInfo device=new DeviceInfo();
        device.setNumber(nemoNumber);
        devices.add(device);
        callInviteRequest.setCallNumber(callNumber);
        callInviteRequest.setDeviceList(devices);
        Result result = cca.inviteCall(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, callInviteRequest);
        System.out.println(result);
    }
    
    /**
     * @Title: getMeetingStatus
     * @Description: 测试获取会议室状态方法
     * @author B1gB1gRAin
     * @date 2017年10月17日 下午5:32:19 
     *
     * @throws IOException
     */
    @Test
    public void getMeetingStatus() throws IOException{
        ConferenceControlApi cca=new ConferenceControlApi();
        Result<MeetingStatus> meetingStatus = cca.getMeetingStatus(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, callNumber);
        System.out.println(meetingStatus);
    }

    /**
     * @Title: inviteNemoCall
     * @Description: 邀请一个设备进入会议
     * @author B1gB1gRAin
     * @date 2017年10月17日 下午5:34:26 
     *
     * @throws IOException
     */
    @Test
    public void inviteNemoCall() throws IOException{
        ConferenceControlApi cca=new ConferenceControlApi();

        MeetingRoom  meetingRoom =new MeetingRoom();
        meetingRoom.setMeetingRoomNumber(callNumber);
        Result result= cca.inviteNemoCall(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, nemoNumber,meetingRoom);
        System.out.println(result);
    }

    /**
     * @Title: setMainImage
     * @Description: 设置主画面
     * @author B1gB1gRAin
     * @throws IOException 
     * @date 2017年10月17日 下午5:35:58 
     *
     */
    @Test
    public void setMainImage() throws IOException{
        ConferenceControlApi cca=new ConferenceControlApi();
        Device device =new Device();
        device.setId(100120772);
        device.setType(2);
        device.setParticipantNumber(nemoNumber);
        device.setExternalUserId(null);
        Result result = cca.setMainImage(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, callNumber,device);
        System.out.println(result);
    }
    
    /**
     * @Title: getEnterpriseCurrentMeeting
     * @Description: 获取某公司当前正在进行的会议
     * @author B1gB1gRAin
     * @throws IOException 
     * @date 2017年10月17日 下午5:37:16 
     *
     */
    @Test
    public void getEnterpriseCurrentMeeting() throws IOException{
        ConferenceControlApi cca=new ConferenceControlApi();
        Result<CurrentMeeting[]> result = cca.getEnterpriseCurrentMeeting(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN);
        int errStatus = result.getErrorStatus();
        CurrentMeeting [] currentMeetings= result.getData();
        for(CurrentMeeting currentMeeting:currentMeetings){
            System.out.println("===="+currentMeeting.getMeetingRoomNumber());
        }
        if(errStatus<300){
            System.out.println("测试成功！"+errStatus);
        }else {
            System.out.println("测试失败！"+errStatus);
        }
    }
    
    /**
     * @Title: mute
     * @Description:静音 
     * @author B1gB1gRAin
     * @throws IOException 
     * @date 2017年10月17日 下午5:44:18 
     *
     */
    @Test
    public void mute() throws IOException{
        ConferenceControlApi cca=new ConferenceControlApi();
        Device device=new Device();
        device.setId(100120772);
        device.setType(2);
        device.setParticipantId("393344");
        device.setParticipantNumber("702301");
        device.setExternalUserId(null);
        Device[] devices={device};
        Result result = cca.mute(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN,callNumber,devices);
        System.out.println(result);
        int errStatus = result.getErrorStatus();
        if(errStatus<300){
            System.out.println("测试成功！"+errStatus);
        }else {
            System.out.println("测试失败！"+errStatus);
        }
    }
    
    /**
     * @Title: muteall
     * @Description: 全部静音
     * @author B1gB1gRAin
     * @throws IOException 
     * @date 2017年10月17日 下午5:45:27 
     *
     */
    @Test
    public void muteall() throws IOException{
        ConferenceControlApi cca=new ConferenceControlApi();
        Result result = cca.muteall(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, callNumber);
        int errStatus = result.getErrorStatus();
        System.out.println(result);
        if(errStatus<300){
            System.out.println("测试成功！"+errStatus);
        }else {
            System.out.println("测试失败！"+errStatus);
        }
    }
    
    /**
     * @Title: unmute
     * @Description: 取消静音
     * @author B1gB1gRAin
     * @throws IOException 
     * @date 2017年10月17日 下午5:46:26 
     *
     */
    @Test
    public void unmute() throws IOException{
        ConferenceControlApi cca=new ConferenceControlApi();
        Device device=new Device();
        device.setId(100120772);
        device.setType(2);
        device.setParticipantId("393344");
        device.setParticipantNumber("702301");
        device.setExternalUserId(null);
        Device[] devices={device};
        Result result = cca.unmute(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, callNumber, devices);
        int errStatus = result.getErrorStatus();
        System.out.println(result);
        if(errStatus<300){
            System.out.println("测试成功！"+errStatus);
        }else {
            System.out.println("测试失败！"+errStatus);
        }
    }
    
    /**
     * @Title: end
     * @Description: 结束会议
     * @author B1gB1gRAin
     * @throws IOException 
     * @date 2017年10月17日 下午5:48:25 
     *
     */
    @Test
    public void end() throws IOException{
        ConferenceControlApi cca=new ConferenceControlApi();
        Result result = cca.end(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, callNumber);
        int errStatus = result.getErrorStatus();
        System.out.println(result);
        if(errStatus<300){
            System.out.println("测试成功！"+errStatus);
        }else {
            System.out.println("测试失败！"+errStatus);
        }
    }
    
    /**
     * @Title: endandrelease
     * @Description: 结束会议并且释放它，如果是临时会议就会清除掉（不可再次使用）
     * @author B1gB1gRAin
     * @throws IOException 
     * @date 2017年10月17日 下午5:48:14 
     *
     */
    @Test
    public void endandrelease() throws IOException{
        ConferenceControlApi cca=new ConferenceControlApi();
        Result result = cca.endAndReleaseNumber(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, callNumber);
        int errStatus = result.getErrorStatus();
        System.out.println(result);
        if(errStatus<300){
            System.out.println("测试成功！"+errStatus);
        }else {
            System.out.println("测试失败！"+errStatus);
        }
    }
    
    /**
     * @Title: notifyDevice
     * @Description: 发通知
     * @author B1gB1gRAin
     * @date 2017年10月17日 下午5:51:35 
     *
     * @throws IOException
     */
    @Test
    public void notifyDevice() throws IOException{
        ConferenceControlApi cca=new ConferenceControlApi();
        Device device=new Device();
        device.setId(100120772);
        device.setType(2);
        device.setParticipantId("393344");
        device.setParticipantNumber("702301");
        device.setExternalUserId(null);
        List<Device> devices=new ArrayList<Device>();
        devices.add(device);
        DeviceNotification deviceNotification=new DeviceNotification();
        deviceNotification.setDeviceList(devices);
        deviceNotification.setContent("我二哈恶搞");
        deviceNotification.setNotificationType(1);
        Result result = cca.notifyDevice(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, deviceNotification);
        int errStatus = result.getErrorStatus();
        System.out.println(result);
        if(errStatus<300){
            System.out.println("测试成功！"+errStatus);
        }else {
            System.out.println("测试失败！"+errStatus);
        }
    }
    
    /**
     * @Title: contentAuthShare
     * @Description: 授权分享权限
     * @author B1gB1gRAin
     * @throws IOException 
     * @date 2017年10月17日 下午5:51:54 
     *
     */
    @Test
    public void contentAuthShare() throws IOException{
        ConferenceControlApi cca=new ConferenceControlApi();
        ShareAuthTarget sat=new ShareAuthTarget();
        Device device=new Device();
        device.setId(100120772);
        device.setType(2);
        device.setParticipantId("393344");
        device.setParticipantNumber("702301");
        device.setExternalUserId(null);
        Device[] devices={device};
        sat.setIsAll(false);
        sat.setDevice(device);
        Result result = cca.authShare(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, callNumber,sat);
        if(result!=null) {
            int errStatus = result.getErrorStatus();
            System.out.println(result);
            if (errStatus < 300) {
                System.out.println("测试成功！" + errStatus);
            } else {
                System.out.println("测试失败！" + errStatus);
            }
        }
    }
    
    /**
     * @Title: lock
     * @Description: 会议锁定
     * @author B1gB1gRAin
     * @throws IOException 
     * @date 2017年10月17日 下午5:53:44 
     *
     */
    @Test
    public void lock() throws IOException{
        ConferenceControlApi cca=new ConferenceControlApi();
        Result result = cca.meetingLock(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, callNumber);
        int errStatus = result.getErrorStatus();
       System.out.println(result);
        if(errStatus<300){
            System.out.println("测试成功！"+errStatus);
        }else {
            System.out.println("测试失败！"+errStatus);
        }
    }
    
    /**
     * @Title: unlock
     * @Description: 会议解锁
     * @author B1gB1gRAin
     * @throws IOException 
     * @date 2017年10月17日 下午5:54:10 
     *
     */
    @Test
    public void unlock() throws IOException{
        ConferenceControlApi cca=new ConferenceControlApi();
        Result result = cca.meetingUnlock(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, callNumber);
        int errStatus = result.getErrorStatus();
        System.out.println(result);
        if(errStatus<300){
            System.out.println("测试成功！"+errStatus);
        }else {
            System.out.println("测试失败！"+errStatus);
        }
    }
    
    /**
     * @Title: sendMsg
     * @Description: 发送消息
     * @author B1gB1gRAin
     * @throws IOException 
     * @date 2017年10月17日 下午5:55:13 
     *
     */
    @Test
    public void sendMsg() throws IOException{
        ConferenceControlApi cca=new ConferenceControlApi();
        MeetingSubtitleRequest msr=new MeetingSubtitleRequest();
        Device device=new Device();
        device.setId(100120772);
        device.setType(2);
        device.setParticipantId("393344");
        device.setParticipantNumber("702301");
        device.setExternalUserId(null);
        Device[] devices={device};
        msr.setDevices(devices);
        MeetingSubtitle meetingSubtitle =new MeetingSubtitle();
        meetingSubtitle.setContent("发月闭关！！！");
        meetingSubtitle.setLocation("middle");
        meetingSubtitle.setAction("cancel");
        meetingSubtitle.setScroll("1");
        msr.setMeetingSubtitle(meetingSubtitle);
        Result result = cca.sendMessage(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, callNumber, msr);
        if(result!=null) {
            int errStatus = result.getErrorStatus();
            System.out.println(result);
            if (errStatus < 300) {
                System.out.println("测试成功！" + errStatus);
            } else {
                System.out.println("测试失败！" + errStatus);
            }
        }
    }
    
    /**
     * @Title: disconnect
     * @Description: 删除人员：在会议过程中删除与会人员
     * @author B1gB1gRAin
     * @throws IOException 
     * @date 2017年10月17日 下午5:56:06 
     *
     */
    @Test
    public void disconnect() throws IOException{
        ConferenceControlApi cca=new ConferenceControlApi();
        Device device =new Device();
        device.setId(20358075);
        device.setType(2);
//        device.setParticipantId("393344");
        device.setParticipantNumber("852369");
        device.setExternalUserId(null);
        Device[] devices={device};
        Result result = cca.disconnect(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, callNumber,devices);
        System.out.println(result);
    }
}
