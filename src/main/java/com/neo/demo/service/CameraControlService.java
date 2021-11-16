package com.neo.demo.service;

import com.neo.demo.lib.NetSDKLib;
import com.sun.jna.Pointer;
import org.springframework.stereotype.Service;

/**
 * 云台控制接口实现
 * 主要有 ：八个方向控制、变倍、变焦、光圈功能
 */
@Service
public class CameraControlService {

    // 设备断线通知回调
    private static final DisConnect disConnect       = new DisConnect();

    // 网络连接恢复
    private static final HaveReConnect haveReConnect = new HaveReConnect();

    public CameraControlService() {
        CameraLoginService.init(disConnect, haveReConnect);
    }

    private static class DisConnect implements NetSDKLib.fDisConnect {
        @Override
        public void invoke(NetSDKLib.LLong lLoginID, String pchDVRIP, int nDVRPort, Pointer dwUser) {
            System.out.printf("Device[%s] Port[%d] DisConnect!\n", pchDVRIP, nDVRPort);
        }
    }

    // 网络连接恢复，设备重连成功回调
    // 通过 CLIENT_SetAutoReconnect 设置该回调函数，当已断线的设备重连成功时，SDK会调用该函数
    private static class HaveReConnect implements NetSDKLib.fHaveReConnect {
        @Override
        public void invoke(NetSDKLib.LLong lLoginID, String pchDVRIP, int nDVRPort, Pointer dwUser) {
            System.out.printf("ReConnect Device[%s] Port[%d]\n", pchDVRIP, nDVRPort);
        }
    }

    /**
     * 向上
     */
    public boolean ptzControlUpStart(int nChannelID, int lParam1, int lParam2) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_UP_CONTROL,
                lParam1, lParam2, 0, 0);
    }
    public boolean ptzControlUpEnd(int nChannelID) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_UP_CONTROL,
                0, 0, 0, 1);
    }

    /**
     * 向下
     */
    public boolean ptzControlDownStart(int nChannelID, int lParam1, int lParam2) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_DOWN_CONTROL,
                lParam1, lParam2, 0, 0);
    }
    public boolean ptzControlDownEnd(int nChannelID) {
        return 	CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_DOWN_CONTROL,
                0, 0, 0, 1);
    }

    /**
     * 向左
     */
    public static boolean ptzControlLeftStart(int nChannelID, int lParam1, int lParam2) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_LEFT_CONTROL,
                lParam1, lParam2, 0, 0);
    }
    public static boolean ptzControlLeftEnd(int nChannelID) {
        return	CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_LEFT_CONTROL,
                0, 0, 0, 1);
    }

    /**
     * 向右
     */
    public static boolean ptzControlRightStart(int nChannelID, int lParam1,int lParam2) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_RIGHT_CONTROL,
                lParam1, lParam2, 0, 0);
    }
    public static boolean ptzControlRightEnd(int nChannelID) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_RIGHT_CONTROL,
                0, 0, 0, 1);
    }

    /**
     * 向左上
     */
    public static boolean ptzControlLeftUpStart(int nChannelID, int lParam1, int lParam2) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_EXTPTZ_ControlType.NET_EXTPTZ_LEFTTOP,
                lParam1, lParam2, 0, 0);
    }
    public static boolean ptzControlLeftUpEnd(int nChannelID) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_EXTPTZ_ControlType.NET_EXTPTZ_LEFTTOP,
                0, 0, 0, 1);
    }

    /**
     * 向右上
     * @param nChannelID
     */
    public static boolean ptzControlRightUpStart(int nChannelID, int lParam1, int lParam2) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_EXTPTZ_ControlType.NET_EXTPTZ_RIGHTTOP,
                lParam1, lParam2, 0, 0);
    }
    public static boolean ptzControlRightUpEnd(int nChannelID) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_EXTPTZ_ControlType.NET_EXTPTZ_RIGHTTOP,
                0, 0, 0, 1);
    }

    /**
     * 向左下
     */
    public static boolean ptzControlLeftDownStart(int nChannelID, int lParam1, int lParam2) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_EXTPTZ_ControlType.NET_EXTPTZ_LEFTDOWN,
                lParam1, lParam2, 0, 0);
    }
    public static boolean ptzControlLeftDownEnd(int nChannelID) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_EXTPTZ_ControlType.NET_EXTPTZ_LEFTDOWN,
                0, 0, 0, 1);
    }

    /**
     * 向右下
     */
    public static boolean ptzControlRightDownStart(int nChannelID, int lParam1, int lParam2) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_EXTPTZ_ControlType.NET_EXTPTZ_RIGHTDOWN,
                lParam1, lParam2, 0, 0);
    }
    public static boolean ptzControlRightDownEnd(int nChannelID) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_EXTPTZ_ControlType.NET_EXTPTZ_RIGHTDOWN,
                0, 0, 0, 1);
    }

    /**
     * 变倍+
     */
    public static boolean ptzControlZoomAddStart(int nChannelID, int lParam2) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_ZOOM_ADD_CONTROL,
                0, lParam2, 0, 0);
    }
    public static boolean ptzControlZoomAddEnd(int nChannelID) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_ZOOM_ADD_CONTROL,
                0, 0, 0, 1);
    }

    /**
     * 变倍-
     */
    public static boolean ptzControlZoomDecStart(int nChannelID, int lParam2) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_ZOOM_DEC_CONTROL,
                0, lParam2, 0, 0);
    }
    public static boolean ptzControlZoomDecEnd(int nChannelID) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_ZOOM_DEC_CONTROL,
                0, 0, 0, 1);
    }

    /**
     * 变焦+
     */
    public static boolean ptzControlFocusAddStart(int nChannelID, int lParam2) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_FOCUS_ADD_CONTROL,
                0, lParam2, 0, 0);
    }
    public static boolean ptzControlFocusAddEnd(int nChannelID) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_FOCUS_ADD_CONTROL,
                0, 0, 0, 1);
    }

    /**
     * 变焦-
     */
    public static boolean ptzControlFocusDecStart(int nChannelID, int lParam2) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_FOCUS_DEC_CONTROL,
                0, lParam2, 0, 0);
    }
    public static boolean ptzControlFocusDecEnd(int nChannelID) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_FOCUS_DEC_CONTROL,
                0, 0, 0, 1);
    }

    /**
     * 光圈+
     */
    public static boolean ptzControlIrisAddStart(int nChannelID, int lParam2) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_APERTURE_ADD_CONTROL,
                0, lParam2, 0, 0);
    }
    public static boolean ptzControlIrisAddEnd(int nChannelID) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_APERTURE_ADD_CONTROL,
                0, 0, 0, 1);
    }

    /**
     * 光圈-
     */
    public static boolean ptzControlIrisDecStart(int nChannelID, int lParam2) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_APERTURE_DEC_CONTROL,
                0, lParam2, 0, 0);
    }
    public static boolean ptzControlIrisDecEnd(int nChannelID) {
        return CameraLoginService.netsdk.CLIENT_DHPTZControlEx(CameraLoginService.m_hLoginHandle, nChannelID,
                NetSDKLib.NET_PTZ_ControlType.NET_PTZ_APERTURE_DEC_CONTROL,
                0, 0, 0, 1);
    }
}
