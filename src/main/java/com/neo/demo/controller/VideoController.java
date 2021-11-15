package com.neo.demo.controller;

import com.github.kokorin.jaffree.StreamType;
import com.github.kokorin.jaffree.ffmpeg.FFmpeg;
import com.github.kokorin.jaffree.ffmpeg.PipeOutput;
import com.neo.demo.service.CameraControlService;
import com.neo.demo.service.CameraLoginService;
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

    @GetMapping(value = "/live.mp4")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> livestream() {

        String rtspUrl = "rtsp:admin:admin@123@192.168.100.156:80/cam/realmonitor?channel=1&subtype=1";

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
                            .addArguments("-b:a", "96k" )
                            .addOutput(PipeOutput.pumpTo(os)
                                    .disableStream(StreamType.AUDIO)
                                    .disableStream(StreamType.SUBTITLE)
                                    .disableStream(StreamType.DATA)
                                    .setFrameCount(StreamType.VIDEO, 100L)
                                    //1 frame every 10 seconds
                                    .setFrameRate(0.1)
                                    .setDuration(1, TimeUnit.HOURS)
                                    .setFormat("ismv"))
                            .addArgument("-nostdin")
                            .execute();
                });
    }

    @PostMapping(value = "/up")
    @ResponseBody
    public ResponseEntity<Boolean> upCamera(@RequestParam("ip") String ip,
                                            @RequestParam("port") int port,
                                            @RequestParam("user") String user,
                                            @RequestParam("password") String password) {
        CameraLoginService.login(ip, port, user, password);
        cameraControlService.ptzControlUpStart(1, 2, 2);
        return ResponseEntity.ok(true);
    }
}