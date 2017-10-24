package com.bigbigrain.xylink.demo;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.xylink.model.MeetingInfo;
import com.xylink.model.SdkMeeting;
import com.xylink.model.SdkMeetingReq;
import com.xylink.sdk.conferenceControl.CreateMeetingApi;
import com.xylink.util.Result;

/**
 * @ClassName: CreateMeetingApiTest
 * @Description: 会议室相关测试
 * @author B1gB1gRAin
 * @date 2017年10月17日 下午3:58:29
 *
 */
public class CreateMeetingApiTest {

	private CreateMeetingApi createMeetingApi;
	
	@Before
	public void init(){
		createMeetingApi = new CreateMeetingApi();
	}
	
	/**
	 * @Title: testCreateMeeting
	 * @Description: 测试创建会议室
	 * @author B1gB1gRAin
	 * @date 2017年10月17日 下午4:01:28 
	 *
	 * @throws IOException
	 */
	@Test
	public void testCreateMeeting() throws IOException{
		SdkMeetingReq sdkMeetingReq = new SdkMeetingReq();
        sdkMeetingReq.setMeetingName("张雨的私人会议室");
        sdkMeetingReq.setPassword("123456");
        sdkMeetingReq.setMeetingNumber("910071111111");
        SdkMeeting sdkMeeting = createMeetingApi.createMeeting(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, sdkMeetingReq);
        
        System.out.println(sdkMeeting);
	}
	
	/**
	 * @Title: testGetBatchMeetingInfo
	 * @Description: 测试根据会议室编号获取会议室信息
	 * @author B1gB1gRAin
	 * @date 2017年10月17日 下午4:14:09 
	 *
	 * @throws IOException
	 */
	@Test
	public void testGetBatchMeetingInfo() throws IOException{
		String[] meetingRoomNumbers =  {"910041111111","910051111111"};
		Result<MeetingInfo[]>  results = createMeetingApi.getBatchMeetingInfo(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN,meetingRoomNumbers);
		MeetingInfo[] meetingInfos = results.getData();
		for(MeetingInfo info : meetingInfos){
			System.out.println(info);
		}
	}
	
	/**
	 * @Title: testGetMeetingInfo
	 * @Description: 根据会议室编号获取会议室信息
	 * @author B1gB1gRAin
	 * @date 2017年10月17日 下午4:19:19 
	 *
	 * @throws IOException
	 */
	@Test
	public void testGetMeetingInfo() throws IOException{
		Result<MeetingInfo> result = createMeetingApi.getMeetingInfo(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, "910051111111");
		System.out.println(result);
	}
	
	/**
	 * @Title: testUpdateMeetingInfo
	 * @Description: 测试更新会议室信息
	 * @author B1gB1gRAin
	 * @date 2017年10月17日 下午4:28:36 
	 *
	 * @throws IOException
	 */
	@Test
	@SuppressWarnings("rawtypes")
	public void testUpdateMeetingInfo() throws IOException{
		MeetingInfo meetingInfo = new MeetingInfo();
		meetingInfo.setMeettingRoomName("张雨修改了的聊天室");
		Result result = createMeetingApi.updateMeetingInfo(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, "910041111111", meetingInfo);
		System.out.println(result);
	}
	
	/**
	 * @Title: testDeleteMeetingInfo
	 * @Description: 测试根据会议室编号删除会议室
	 * @author B1gB1gRAin
	 * @date 2017年10月17日 下午4:59:23 
	 *
	 * @throws IOException
	 */
	@Test
	@SuppressWarnings("rawtypes")
	public void testDeleteMeetingInfo() throws IOException{
		Result result = createMeetingApi.deleteMeetingInfo(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN, "910041111111");
		System.out.println(result);
	}
}
