package com.bigbigrain.xylink.demo;

import com.xylink.model.CallNumberInfo;
import com.xylink.sdk.enterpriseNemo.CallNumberApi;
import com.xylink.util.Result;

import org.junit.Test;

import java.io.IOException;

/**
 * @ClassName: CallNumberTest
 * @Description: 获取小于号信息
 * @author B1gB1gRAin
 * @date 2017年10月18日 上午9:42:11
 *
 */
public class CallNumberTest {
    
	/**
     * @Title: getCallNumberInfo
     * @Description:获取小鱼号信息 
     * @author B1gB1gRAin
     * @date 2017年10月18日 上午9:44:15 
     *
     */
    @Test
    public void getCallNumberInfo(){
        CallNumberApi cna=new CallNumberApi();
        String number="702301";
        try {
        	Result<CallNumberInfo> result = cna.getCallNumberInfo(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN,number);
        	System.out.println(result);
        }catch (IOException e){
            System.out.println("testEnd--"+e);
        }
    }
}
