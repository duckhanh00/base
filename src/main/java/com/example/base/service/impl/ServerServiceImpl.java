package com.example.base.service.impl;

import com.example.base.api.form.CreateServerForm;
import com.example.base.dto.MovieDTO;
import com.example.base.dto.ServerDTO;
import com.example.base.entity.Movie;
import com.example.base.entity.Server;
import com.example.base.enumeration.Status;
import com.example.base.service.ServerService;
import com.example.base.store.repository.ServerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.Column;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

import static com.example.base.enumeration.Status.SERVER_DOWN;
import static com.example.base.enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {
    private final ServerRepository serverRepository;

    @Override
    public void create(CreateServerForm createServerForm) {
        log.info("Saving new server: {}", createServerForm.getName());
        createServerForm.setImageUrl(setServerImageUrl());

        serverRepository.save(Server.builder()
                .ipAddress(createServerForm.getIpAddress())
                .name(createServerForm.getName())
                .memory(createServerForm.getMemory())
                .type(createServerForm.getType())
                .imageUrl(createServerForm.getImageUrl())
                .status(createServerForm.getStatus())
                .createAt(new Date())
                .build());
        log.info("OK!");
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging new server: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<ServerDTO> list(int limit) {
        log.info("Fetching all server");
        Collection<Server>  serverEntities = serverRepository.findAll(PageRequest.of(0, limit)).toList();
        Collection<ServerDTO> serverDTOs = serverEntities.stream()
                .map(item -> ServerDTO.builder()
                        .id(item.getId())
                        .ipAddress(item.getIpAddress())
                        .name(item.getName())
                        .memory(item.getMemory())
                        .type(item.getType())
                        .imageUrl(item.getImageUrl())
                        .status(item.getStatus())
                        .build()
                )
                .collect(Collectors.toList());
        return serverDTOs;
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching all server by id: {}", id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating new server: {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by ID: {}", id);
        serverRepository.deleteById(id);
        return TRUE;
    }

    private String setServerImageUrl() {
        String [] imageNames = {"server1.png", "server2.png", "server3.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/assets/image" + imageNames[new Random().nextInt(3)]).toUriString();
    }
}
