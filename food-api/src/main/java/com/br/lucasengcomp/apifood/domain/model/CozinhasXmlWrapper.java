package com.br.lucasengcomp.apifood.domain.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.util.List;


@Data
@JacksonXmlRootElement(localName = "cozinhas")
public class CozinhasXmlWrapper {

    @NonNull
    @JsonProperty("cozinha")
    @JacksonXmlElementWrapper(useWrapping = false)
//    @JacksonXmlProperty(localName = "cozinha")
    private List<Cozinha> cozinhas;

}
