package com.neo.demo.lib.structure;

import com.neo.demo.lib.NetSDKLib;

public class NET_RECORDBACKUP_PROCESSTIME  extends NetSDKLib.SdkStructure {
	public NET_TIME_EX1            stuStartTime;            // 开始时间
	public NET_TIME_EX1            stuEndTime;              // 结束时间
	public byte[]                  bReserved=new byte[64];          // 保留字段
}
