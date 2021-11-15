package com.neo.demo.lib.structure;

import com.neo.demo.lib.NetSDKLib;
import com.neo.demo.lib.constant.SDKStructureFieldLenth;

/**
 * 音频输出通道相关信息
 *
 * @author 47040
 * @version 1.0.0
 * @since Created in 2021/3/9 9:24
 */
public class NET_AUDIO_OUTPUT_CHANNEL_INFO extends NetSDKLib.SdkStructure {

    /**
     * 通道编号
     */
    public byte[] szID = new byte[SDKStructureFieldLenth.MAX_CHANNEL_ID_LEN];
    /**
     * 保留字节
     */
    public byte[] byReserved = new byte[1024];

}
