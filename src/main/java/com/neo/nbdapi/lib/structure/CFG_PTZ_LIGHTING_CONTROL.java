package com.neo.nbdapi.lib.structure;/**
 * @author 47081
 * @descriptio
 * @date 2020/11/9
 * @version 1.0
 */

import com.neo.nbdapi.lib.NetSDKLib;
import com.neo.nbdapi.lib.constant.SDKStructureFieldLenth;

/**
 * @author 47081
 * @version 1.0
 * @description 云台转动角度范围，单位：度
 * @date 2020/11/9
 */
public class CFG_PTZ_LIGHTING_CONTROL extends NetSDKLib.SdkStructure {
    /**
     * 手动灯光控制模式
     * "on-off"：直接开关模式,
     * "adjustLight"：手动调节亮度模式
     */
    public byte[] szMode = new byte[SDKStructureFieldLenth.CFG_COMMON_STRING_32];

    /**
     * 近光灯组数量
     */
    public int dwNearLightNumber;
    /**
     * 远光灯组数量
     */
    public int dwFarLightNumber;

}