package com.spring.blog.dto;

import lombok.*;

@Getter @Setter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
public class BmiDTO {
    private double height;
    private double weight;
}
