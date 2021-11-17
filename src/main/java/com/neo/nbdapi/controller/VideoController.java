package com.neo.nbdapi.controller;

import com.github.kokorin.jaffree.StreamType;
import com.github.kokorin.jaffree.ffmpeg.FFmpeg;
import com.github.kokorin.jaffree.ffmpeg.PipeOutput;
import com.neo.nbdapi.service.CameraControlService;
import com.neo.nbdapi.service.CameraLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private CameraControlService cameraControlService;

    // xem video
    @GetMapping(value = "/live.mp4")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> livestream(@RequestParam("rtsp_url") String rtspUrl) {

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(os -> {
                    FFmpeg.atPath()
                            .addArgument("-re")
                            .addArguments("-acodec", "pcm_s16le")
                            .addArguments("-rtsp_transport", "tcp")
                            .addArguments("-i", rtspUrl)
                            .addArguments("-vcodec", "copy")
                            .addArguments("-af", "asetrate=22050")
                            .addArguments("-acodec", "aac")
                            .addArguments("-b:a", "96k")
                            .addOutput(PipeOutput.pumpTo(os)
                                    .disableStream(StreamType.AUDIO)
                                    .disableStream(StreamType.SUBTITLE)
                                    .disableStream(StreamType.DATA)
                                    //1 frame every 10 seconds
                                    .setFrameRate(0.1)
                                    .setDuration(1, TimeUnit.HOURS)
                                    .setFormat("ismv"))
                            .addArgument("-nostdin")
                            .execute();
                });
    }

    // login cam de dieu khien; truoc khi dieu khien bat buoc phai login
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<Boolean> loginCamera(@RequestParam("ip_cam") String idCam,
                                               @RequestParam("port") int port,
                                               @RequestParam("user") String user,
                                               @RequestParam("password") String password) {
        Boolean check = CameraLoginService.login(idCam, port, user, password);
        return ResponseEntity.ok(check);
    }

    // keo cammera len tren
    @PostMapping(value = "/up")
    @ResponseBody
    public ResponseEntity<Boolean> upCamera() {
        Boolean check = cameraControlService.ptzControlUpStart(0, 0, 2);
        return ResponseEntity.ok(check);
    }

    // dung keo camera len
    @PostMapping(value = "/up-end")
    @ResponseBody
    public ResponseEntity<Boolean> endUpCamera() {
        Boolean check = cameraControlService.ptzControlUpEnd(0);
        return ResponseEntity.ok(check);
    }

    // ha camera xuong
    @PostMapping(value = "/down")
    @ResponseBody
    public ResponseEntity<Boolean> downCamera() {
        Boolean check = cameraControlService.ptzControlDownStart(0, 0, 2);
        return ResponseEntity.ok(check);
    }

    //dung ha camera
    @PostMapping(value = "/down-end")
    @ResponseBody
    public ResponseEntity<Boolean> endDownCamera() {
        Boolean check = cameraControlService.ptzControlDownEnd(0);
        return ResponseEntity.ok(check);
    }

    //keo camera sang trai
    @PostMapping(value = "/left")
    @ResponseBody
    public ResponseEntity<Boolean> leftCamera() {
        Boolean check = cameraControlService.ptzControlLeftStart(0, 0, 2);
        return ResponseEntity.ok(check);
    }

    // dung keo camera sang trai
    @PostMapping(value = "/left-end")
    @ResponseBody
    public ResponseEntity<Boolean> endLeftCamera() {
        Boolean check = cameraControlService.ptzControlLeftEnd(0);
        return ResponseEntity.ok(check);
    }

    // keo camera sang phai
    @PostMapping(value = "/right")
    @ResponseBody
    public ResponseEntity<Boolean> rightCamera() {
        Boolean check = cameraControlService.ptzControlRightStart(0, 0, 2);
        return ResponseEntity.ok(check);
    }

    // dung keo camera sang phai
    @PostMapping(value = "/right-end")
    @ResponseBody
    public ResponseEntity<Boolean> endRightCamera() {
        Boolean check = cameraControlService.ptzControlRightEnd(0);
        return ResponseEntity.ok(check);
    }

    // keo camera len tren ben trai
    @PostMapping(value = "/left-up")
    @ResponseBody
    public ResponseEntity<Boolean> leftUpCamera() {
        Boolean check = cameraControlService.ptzControlLeftUpStart(0, 0, 2);
        return ResponseEntity.ok(check);
    }

    // dung keo camera len tren sang trai
    @PostMapping(value = "/left-up-end")
    @ResponseBody
    public ResponseEntity<Boolean> endLeftUpCamera() {
        Boolean check = cameraControlService.ptzControlLeftUpEnd(0);
        return ResponseEntity.ok(check);
    }

    // keo camera len tren sang phai
    @PostMapping(value = "/right-up")
    @ResponseBody
    public ResponseEntity<Boolean> rightUpCamera() {
        Boolean check = cameraControlService.ptzControlRightUpStart(0, 0, 2);
        return ResponseEntity.ok(check);
    }

    // dung keo camera len tren sang phai
    @PostMapping(value = "/right-up-end")
    @ResponseBody
    public ResponseEntity<Boolean> endRightUpCamera() {
        Boolean check = cameraControlService.ptzControlRightUpEnd(0);
        return ResponseEntity.ok(check);
    }

    // keo camera xuong duoi ben trai
    @PostMapping(value = "/left-down")
    @ResponseBody
    public ResponseEntity<Boolean> leftDownCamera() {
        Boolean check = cameraControlService.ptzControlLeftDownStart(0, 0, 2);
        return ResponseEntity.ok(check);
    }

    // dung keo camera xuong duoi ben trai
    @PostMapping(value = "/left-down-end")
    @ResponseBody
    public ResponseEntity<Boolean> endLeftDownCamera() {
        Boolean check = cameraControlService.ptzControlLeftDownEnd(0);
        return ResponseEntity.ok(check);
    }

    // keo camera xuong duoi ben phai
    @PostMapping(value = "/right-down")
    @ResponseBody
    public ResponseEntity<Boolean> rightDownCamera() {
        Boolean check = cameraControlService.ptzControlRightDownStart(0, 0, 2);
        return ResponseEntity.ok(check);
    }

    // dung keo camera xuong duoi ben phai
    @PostMapping(value = "/right-down-end")
    @ResponseBody
    public ResponseEntity<Boolean> endRightDownCamera() {
        Boolean check = cameraControlService.ptzControlRightDownEnd(0);
        return ResponseEntity.ok(check);
    }

    // phong to
    @PostMapping(value = "/zoom-add")
    @ResponseBody
    public ResponseEntity<Boolean> zoomAddCamera() {
        Boolean check = cameraControlService.ptzControlZoomAddStart(0, 2);
        return ResponseEntity.ok(check);
    }

    // dung phong to
    @PostMapping(value = "/zoom-add-end")
    @ResponseBody
    public ResponseEntity<Boolean> endZoomAddCamera() {
        Boolean check = cameraControlService.ptzControlZoomAddEnd(0);
        return ResponseEntity.ok(check);
    }

    // thu nho
    @PostMapping(value = "/zoom-dec")
    @ResponseBody
    public ResponseEntity<Boolean> zoomDecCamera() {
        Boolean check = cameraControlService.ptzControlZoomDecStart(0, 2);
        return ResponseEntity.ok(check);
    }

    // dung thu nho
    @PostMapping(value = "/zoom-dev-end")
    @ResponseBody
    public ResponseEntity<Boolean> endZoomDecCamera() {
        Boolean check = cameraControlService.ptzControlZoomDecEnd(0);
        return ResponseEntity.ok(check);
    }

}