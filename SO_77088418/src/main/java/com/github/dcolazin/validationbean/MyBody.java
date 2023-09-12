package com.github.dcolazin.validationbean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyBody {

    @MyAnnotation
    private String validatedString;
    private String anotherString;

}
