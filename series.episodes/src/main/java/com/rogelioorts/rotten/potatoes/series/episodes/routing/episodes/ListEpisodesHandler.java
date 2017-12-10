package com.rogelioorts.rotten.potatoes.series.episodes.routing.episodes;

import com.rogelioorts.rotten.potatoes.series.episodes.repositories.EpisodesRepository;
import com.rogelioorts.rotten.potatoes.shared.repositories.PaginatedOptions;
import com.rogelioorts.rotten.potatoes.shared.utils.Responses;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class ListEpisodesHandler implements Handler<RoutingContext> {

  private final EpisodesRepository episodesRepository;

  public ListEpisodesHandler(final EpisodesRepository episodesRepository) {
    this.episodesRepository = episodesRepository;
  }

  @Override
  public void handle(final RoutingContext context) {
    final String idSerie = context.pathParam("idSerie");
    final JsonObject query = new JsonObject().put("id_serie", idSerie);
    final JsonObject sort = new JsonObject().put("season", 1).put("number", 1);
    final PaginatedOptions paginatedOptions = PaginatedOptions.create(context, EpisodesRepository.DEFAULT_PER_PAGE, query, sort);

    episodesRepository.findPaginated(paginatedOptions, res -> {
      if (res.failed()) {
        context.fail(res.cause());
      } else {
        Responses.sendJson(context, res.result());
      }
    });
  }

}
