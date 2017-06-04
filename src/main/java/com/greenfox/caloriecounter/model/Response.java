package com.greenfox.caloriecounter.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

  String status;

  public Response(String status) {
    this.status = status;
  }

  public Response() {
  }
}
