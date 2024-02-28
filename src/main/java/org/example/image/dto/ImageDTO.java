package org.example.image.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageDTO {
    private Long bookId;
    private byte[] image;
}
