package com.example.base.controller;

import com.example.base.api.form.CreateServerForm;
import com.example.base.api.response.ApiResponse;
import com.example.base.api.response.SuccessResponse;
import com.example.base.entity.Server;
import com.example.base.enumeration.Status;
import com.example.base.service.impl.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {
    private final ServerServiceImpl serverService;

    @GetMapping("/list")
    public ApiResponse getServers(@RequestHeader(name = "clientMessageId") String clientMessageId,
                                @RequestParam(defaultValue = "2") Integer limit,
                                @RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer size) {
        return new SuccessResponse(clientMessageId, serverService.list(limit));
    }

    @GetMapping("/ping/{ipAddress}")
    public ApiResponse pingServer(@RequestHeader(name = "clientMessageId") String clientMessageId,
                                  @PathVariable("ipAddress") String ipAddress,
                                  @RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer size) throws IOException {
        Server server = serverService.ping(ipAddress);
        Status status = server.getStatus();
        return new SuccessResponse(clientMessageId, status);
    }

    @GetMapping("/get/{id}")
    public ApiResponse getServer(@RequestHeader(name = "clientMessageId") String clientMessageId,
                                 @PathVariable("ip") Long ip,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer size) {
        Server server = serverService.get(ip);
        return new SuccessResponse(clientMessageId, server.getName());
    }

    @PostMapping("/save")
    public ApiResponse saveServer(@RequestHeader(name = "clientMessageId") String clientMessageId,
                                  @RequestBody @Valid CreateServerForm createServerForm) {
        serverService.create(createServerForm);
        return new SuccessResponse(clientMessageId);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteServer(@RequestHeader(name = "clientMessageId") String clientMessageId,
                                    @PathVariable("ip") Long ip) {
        Boolean isDelete = serverService.delete(ip);
        return new SuccessResponse(clientMessageId, isDelete);
    }

    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@RequestHeader(name = "clientMessageId") String clientMessageId,
                                 @PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "khanhduc/IT Lessons/base/assets" + fileName));
    }
}
