package com.neo.demo.lib.structure;

import com.neo.demo.lib.NetSDKLib;

import static com.neo.demo.lib.NetSDKLib.MAX_PATH;

/**
 * @author 47081
 * @version 1.0
 * @description openssl库信息配置
 * @date 2021/3/2
 */
public class NET_CONFIG_OPENSSL_INFO extends NetSDKLib.SdkStructure {
  public byte[] szSsleay = new byte[MAX_PATH]; // openssl协议库
  // windows下 为ssleay32.dll库全路径，当路径为空时默认从当前NetSDK.dll路径下查找加载
  // linux下	为libssl.so	  库全路径，当路径为空时默认从系统路径下查找加载
  public byte[] szLibeay = new byte[MAX_PATH]; // openssl加密库
  // windows下 为libeay32.dll库全路径，当路径为空时默认从当前NetSDK.dll路径下查找加载
  // linux下	为libcrypto.so库全路径，当路径为空时默认从系统路径下查找加载
  /** 保留字节 */
  public byte[] bReserved = new byte[504];
}
