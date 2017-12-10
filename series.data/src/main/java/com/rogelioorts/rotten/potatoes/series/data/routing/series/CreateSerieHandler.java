package com.rogelioorts.rotten.potatoes.series.data.routing.series;

import com.rogelioorts.rotten.potatoes.series.data.models.Serie;
import com.rogelioorts.rotten.potatoes.series.data.repositories.SeriesRepository;
import com.rogelioorts.rotten.potatoes.shared.utils.Requests;
import com.rogelioorts.rotten.potatoes.shared.utils.Responses;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class CreateSerieHandler implements Handler<RoutingContext> {

  private final SeriesRepository seriesRepository;

  public CreateSerieHandler(final SeriesRepository seriesRepository) {
    this.seriesRepository = seriesRepository;
  }

  @Override
  public void handle(final RoutingContext context) {
    Requests.bodyAsObjectAndValidate(context, Serie.class, model -> {
      seriesRepository.insert(model, res -> {
        if (res.failed()) {
          context.fail(res.cause());
        } else {
          Responses.sendJson(context, model, HttpResponseStatus.CREATED.code());
        }
      });
    });
  }

}
