package com.neo.nbdapi.lib.structure;

import com.neo.nbdapi.lib.NetSDKLib;

import static com.neo.nbdapi.lib.NetSDKLib.MAX_PATH;

/**
 * @author 47081
 * @version 1.0
 * @description
 * @date 2021/2/22
 */
public class ObjectUrl extends NetSDKLib.SdkStructure {
    public byte[] objectUrl=new byte[MAX_PATH];
}
