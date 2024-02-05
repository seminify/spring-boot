package org.seminify.app.service;

import org.seminify.app.dto.GuestbookDTO;

public interface GuestbookService {
    Long register(GuestbookDTO dto);
}
