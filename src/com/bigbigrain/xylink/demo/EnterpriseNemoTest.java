package com.bigbigrain.xylink.demo;

import java.io.IOException;

import org.junit.Test;

import com.xylink.model.NemoDto;
import com.xylink.sdk.enterpriseNemo.EnterpriseNemoApi;
import com.xylink.util.Result;

public class EnterpriseNemoTest {
	
	@Test
	public void getEnterpriseNemos() throws IOException{
		EnterpriseNemoApi ena = new EnterpriseNemoApi(); 
		Result<NemoDto[]>  results = ena.getEnterpriseNemos(XyLinkConstants.ENTERPRISE_ID, XyLinkConstants.TOKEN);
		NemoDto[] nemoDtos = results.getData();
		for(NemoDto nd : nemoDtos){
			System.out.println(nd);
		}
	}
}
