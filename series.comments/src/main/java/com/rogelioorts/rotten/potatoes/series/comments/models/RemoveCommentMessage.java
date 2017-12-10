package com.rogelioorts.rotten.potatoes.series.comments.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveCommentMessage {

  private String id;

  @JsonProperty("id_serie")
  private String idSerie;

  private long total;

}
