package com.example.base.service;

import com.example.base.api.form.CreateServerForm;
import com.example.base.dto.ServerDTO;
import com.example.base.entity.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    void create(CreateServerForm createServerForm);
    Server ping(String ipAddress) throws IOException;
    Collection<ServerDTO> list(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);
}
