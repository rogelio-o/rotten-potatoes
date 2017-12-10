package com.rogelioorts.rotten.potatoes.shared.repositories;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelError {

  private String attribute;

  private String code;

  private Map<String, Object> params;

  private String message;

}
