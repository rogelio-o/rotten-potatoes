package com.rogelioorts.rotten.potatoes.series.comments.routing.comments;

import com.rogelioorts.rotten.potatoes.series.comments.repositories.CommentsRepository;
import com.rogelioorts.rotten.potatoes.shared.repositories.PaginatedOptions;
import com.rogelioorts.rotten.potatoes.shared.utils.Responses;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class ListCommentsHandler implements Handler<RoutingContext> {

  private final CommentsRepository commentsRepository;

  public ListCommentsHandler(final CommentsRepository commentsRepository) {
    this.commentsRepository = commentsRepository;
  }

  @Override
  public void handle(final RoutingContext context) {
    final String idSerie = context.pathParam("idSerie");
    final JsonObject query = new JsonObject().put("id_serie", idSerie);
    final JsonObject sort = new JsonObject().put("creation_date", -1);
    final PaginatedOptions paginatedOptions = PaginatedOptions.create(context, CommentsRepository.DEFAULT_PER_PAGE, query, sort);

    commentsRepository.findPaginated(paginatedOptions, res -> {
      if (res.failed()) {
        context.fail(res.cause());
      } else {
        Responses.sendJson(context, res.result());
      }
    });
  }

}
