package com.rogelioorts.rotten.potatoes.series.data.routing.series;

import java.util.NoSuchElementException;

import com.rogelioorts.rotten.potatoes.series.data.models.Serie;
import com.rogelioorts.rotten.potatoes.series.data.repositories.SeriesRepository;
import com.rogelioorts.rotten.potatoes.shared.utils.Requests;
import com.rogelioorts.rotten.potatoes.shared.utils.Responses;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class UpdateSerieHandler implements Handler<RoutingContext> {

  private final SeriesRepository seriesRepository;

  public UpdateSerieHandler(final SeriesRepository seriesRepository) {
    this.seriesRepository = seriesRepository;
  }

  @Override
  public void handle(final RoutingContext context) {
    final String id = context.pathParam("id");

    Requests.bodyAsObjectAndValidate(context, Serie.class, model -> {
      seriesRepository.update(id, model, res -> {
        if (res.failed()) {
          if (res.cause().getClass().equals(NoSuchElementException.class)) {
            context.fail(HttpResponseStatus.NOT_FOUND.code());
          } else {
            context.fail(res.cause());
          }
        } else {
          Responses.sendJson(context, res.result());
        }
      });
    });
  }

}
