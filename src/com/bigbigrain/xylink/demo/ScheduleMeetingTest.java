package com.bigbigrain.xylink.demo;

import com.xylink.model.ReminderMeeting;
import com.xylink.sdk.dating.ScheduleMeetingApi;
import com.xylink.util.Result;

import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: ScheduleMeetingTest
 * @Description: 预约会议室相关功能测试
 * @author B1gB1gRAin
 * @date 2017年10月18日 上午8:43:48
 *
 */
public class ScheduleMeetingTest {
	
	private String callNumber ="702301";
	
    /**
     * @Title: remindMeeting
     * @Description: 预约会议
     * @author B1gB1gRAin
     * @date 2017年10月18日 上午9:00:43 
     *
     * @throws IOException
     */
    @Test
    public void remindMeeting() throws IOException{
        ScheduleMeetingApi sma=new ScheduleMeetingApi();
        ReminderMeeting reminderMeeting=new ReminderMeeting();
        reminderMeeting.setTitle("zhangy测试预约的会议");
        reminderMeeting.setStartTime(15058137777890L);
        reminderMeeting.setEndTime(15058139897890L);
        Set<String> participants = new HashSet<String>();
        participants.add("496329");
        reminderMeeting.setParticipants(participants);
        reminderMeeting.setMeetingRoomType(1);
        reminderMeeting.setPassword("123456");
        reminderMeeting.setAddress("司法局办公室");
        reminderMeeting.setAutoInvite(1);
        reminderMeeting.setAutoRecord(0);
        int maxParticipantCount=5;
        Result result = sma.remindMeeting(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, reminderMeeting, maxParticipantCount);
        System.out.println(result);
    }
    
    /**
     * @Title: updateMeeting
     * @Description: 修改预约会议
     * @author B1gB1gRAin
     * @date 2017年10月18日 上午9:15:18 
     *
     * @throws IOException
     */
    @Test
    public void updateMeeting() throws IOException{
        ScheduleMeetingApi sma=new ScheduleMeetingApi();
        ReminderMeeting reminderMeeting=new ReminderMeeting();
        reminderMeeting.setStartTime(15058137777890L);
        reminderMeeting.setPassword("2345");
        reminderMeeting.setAddress("地中海");
        reminderMeeting.setAutoInvite(1);
        reminderMeeting.setAutoRecord(0);
        reminderMeeting.setEndTime(15058139897890L);
        int maxParticipantCount=5;
        String meetingId = "34436";
        Result result = sma.updateMeeting(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, meetingId,reminderMeeting,maxParticipantCount);
        System.out.println(result);
    }
    
    /**
     * @Title: deleteMeeting
     * @Description: 删除会议
     * @author B1gB1gRAin
     * @date 2017年10月18日 上午9:16:24 
     *
     * @throws IOException
     */
    @Test
    public void deleteMeeting() throws IOException{
        ScheduleMeetingApi sma=new ScheduleMeetingApi();
        String meetingId = "34436";
        Result result = sma.deleteMeeting(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, meetingId);
        System.out.println(result);
    }
    
    /**
     * @Title: getAllMeeting
     * @Description: 按照结束时间获取会议
     * @author B1gB1gRAin
     * @date 2017年10月18日 上午9:17:19 
     *
     * @throws IOException
     */
    @Test
    public void getAllMeeting() throws IOException{
        ScheduleMeetingApi sma=new ScheduleMeetingApi();
        long endtime =15058139897890L;
        Result result = sma.getAllMeeting(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, endtime);
        System.out.println(result);
    }
    
    /**
     * @Title: getMeeting
     * @Description: 根据会议id获取会议信息
     * @author B1gB1gRAin
     * @date 2017年10月18日 上午9:19:47 
     *
     * @throws IOException
     */
    @Test
    public void getMeeting() throws IOException{
    	ScheduleMeetingApi sma=new ScheduleMeetingApi();
        String meetingId = "34436";
        sma.getMeeting(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, meetingId);
    }
}
