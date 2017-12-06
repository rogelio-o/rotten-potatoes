package com.rogelioorts.workshop.vertx.microservices.series.comments.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rogelioorts.workshop.vertx.microservices.utils.repositories.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment implements Model {

  @JsonProperty("_id")
  private String id;

  @JsonProperty("id_serie")
  private String idSerie;

  private String text;

  private String owner;

  @JsonProperty("creation_date")
  private LocalDateTime creationDate;

  @JsonProperty("update_date")
  private LocalDateTime updateDate;

}
