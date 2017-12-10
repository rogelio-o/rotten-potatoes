package com.rogelioorts.rotten.potatoes.series.comments.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewCommentMessage {

  @JsonProperty("id_serie")
  private String idSerie;

  private Comment comment;

  private long total;

}
