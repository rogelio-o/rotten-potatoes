package com.rogelioorts.rotten.potatoes.series.comments.routing.comments;

import java.util.NoSuchElementException;

import com.rogelioorts.rotten.potatoes.series.comments.models.Comment;
import com.rogelioorts.rotten.potatoes.series.comments.repositories.CommentsRepository;
import com.rogelioorts.rotten.potatoes.shared.utils.Requests;
import com.rogelioorts.rotten.potatoes.shared.utils.Responses;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class UpdateCommentHandler implements Handler<RoutingContext> {

  private final CommentsRepository commentsRepositor;

  public UpdateCommentHandler(final CommentsRepository commentsRepositor) {
    this.commentsRepositor = commentsRepositor;
  }

  @Override
  public void handle(final RoutingContext context) {
    final String id = context.pathParam("id");
    final String idSerie = context.pathParam("idSerie");

    Requests.bodyAsObjectAndValidate(context, Comment.class, model -> model.setIdSerie(idSerie), model -> {
      commentsRepositor.update(id, model, res -> {
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
