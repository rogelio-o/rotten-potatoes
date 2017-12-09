# Rotten Potatoes

Vertx.x and microservices example application with Angular frontend.
This application is only for educational purpose.

## Architecture

The application has a microservices based architecture.

__WARNING:__ this application is only for education purpose, so there is
one microservice for each model of the information requirements. Some models
could have high coupling between each other and they could be in one
microservice together, but this is no the discussion here.

The features of each microservice are the followings:

- edge: it dispatches the static files, renders the templates and acts as proxy
between the "private" microservices and the outworld.
- series.comments: creates, updates, deletes and shows a paginated list of
comments. Each time a comment is created or updated, it send a message through
the event bus.
- series.data: creates, updates, deletes, show a series and show a
paginated list of series. Also, it listens the event bus and it updates
the series statistics when a new comment message, a removed comment message
or new rating message is received.
- series.episodes: creates, updates, deletes, show an episode and show a
paginated list of episodes.
- series.rating: creates new ratings for series. Each time a rating is created,
it send a message through the event bus.

There *scafolder* is a library used in each microservice. It have repetitive
tasks like create new HTTP server, initialize the configuration,...

## How to use it?

Each folder is a independent maven project, so it can be used like that.

In the edge, there is an Angular project. It can be started with `ng serve`
for developing. The production JS files are build with maven, so it doesn't
have to be done manually.

Take into account that the Java files uses *checkstyle* and *PMD* for
code quality assurance. Maybe you will have to configure your IDE with
the *checkstyle.xml* file and the *pmd.xml* file, they have all the used
rules.
