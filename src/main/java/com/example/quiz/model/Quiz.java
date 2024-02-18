/**
 * Developed by S.A. WEERASINGHE - PS/2019/259
 *  * Quiz Page Development
 */


package com.example.quiz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Quiz {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String question;
    @NotBlank
    private String subject;


    @NotBlank
    private String questionCategory;


    @ElementCollection
    private List<String> multiple_options;


    @ElementCollection
    private List<String> correct_option;
}


