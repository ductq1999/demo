package com.neo.demo.lib.structure;

import com.neo.demo.lib.NetSDKLib;

/**
 * @description 速度限制
 * @author 119178
 * @date 2021/3/11
 */
public class NET_SPEED_LIMIT extends NetSDKLib.SdkStructure {
	/**
	 * 速度上限
	 */
	public int							nSpeedUpperLimit;	
	/**
	 * 速度下限
	 */
	public int							nSpeedLowerLimit;						
}
