package com.rogelioorts.rotten.potatoes.series.comments.routing.comments;

import java.util.NoSuchElementException;

import com.rogelioorts.rotten.potatoes.series.comments.repositories.CommentsRepository;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class DeleteCommentHandler implements Handler<RoutingContext> {

  private final CommentsRepository commentsRepository;

  public DeleteCommentHandler(final CommentsRepository commentsRepository) {
    this.commentsRepository = commentsRepository;
  }

  @Override
  public void handle(final RoutingContext context) {
    final String id = context.pathParam("id");

    commentsRepository.delete(id, res -> {
      if (res.failed()) {
        final Throwable exception = res.cause();

        if (exception.getClass().equals(NoSuchElementException.class)) {
          sendOkResponse(context);
        } else {
          context.fail(res.cause());
        }
      } else {
        sendOkResponse(context);
      }
    });
  }

  private void sendOkResponse(final RoutingContext context) {
    context.response().setStatusCode(HttpResponseStatus.NO_CONTENT.code()).end();
  }

}
