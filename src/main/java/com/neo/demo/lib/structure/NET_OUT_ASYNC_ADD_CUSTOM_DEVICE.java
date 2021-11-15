package com.neo.demo.lib.structure;

import com.neo.demo.lib.NetSDKLib;

/***
 * CLIENT_AsyncAddCustomDevice 接口输出参数 
 * @author 119178
 *
 */
public class NET_OUT_ASYNC_ADD_CUSTOM_DEVICE extends NetSDKLib.SdkStructure {
	public int           				dwSize;
	/**
	 * 分配的逻辑通道个数
	 */
	public int								nLogicChannelNum;		
	/**
	 * 分配的逻辑通道号列表
	 */
	public int[]								nLogicChannels=new int[128];	
	/**
	 * 设备ID
	 */
	public byte[]							szDeviceID=new byte[128];		 
	
	public NET_OUT_ASYNC_ADD_CUSTOM_DEVICE(){
		 this.dwSize = this.size();
	}
}
