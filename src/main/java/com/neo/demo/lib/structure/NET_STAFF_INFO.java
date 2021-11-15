package com.neo.demo.lib.structure;

import com.neo.demo.lib.NetSDKLib;
import com.neo.demo.lib.enumeration.NET_EM_STAFF_TYPE;

/**
 * @description  标尺信息
 * @author 119178
 * @date 2021/3/16
 */
public class NET_STAFF_INFO extends NetSDKLib.SdkStructure {
	/**
	 * 起始坐标点
	 */
	public NetSDKLib.DH_POINT stuStartLocation;
	/**
	 * 终止坐标点
	 */
	public NetSDKLib.DH_POINT stuEndLocation;
	/**
	 * 实际长度,单位米
	 */
	public float               nLenth;
	/**
	 * 标尺类型
	 * {@link NET_EM_STAFF_TYPE}
	 */
	public int                 emType;                
}
