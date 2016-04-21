import javax.inject._

import akka.stream.Materializer
import filters.{BasicAuthenticationFilter, BasicAuthenticationFilterConfiguration, LoggingFilter}
import play.api._
import play.api.http.HttpFilters
import play.filters.gzip.GzipFilter

/**
  * This class configures filters that run on every request. This
  * class is queried by Play to get a list of filters.
  *
  * Play will automatically use filters from any class called
  * `Filters` that is placed the root package. You can load filters
  * from a different class by adding a `play.http.filters` setting to
  * the `application.conf` configuration file.
  *
  * @param env           Basic environment settings for the current application.
  * @param loggingFilter [[LoggingFilter]].
  */
@Singleton
class Filters @Inject()(env: Environment, configuration: Configuration, loggingFilter: LoggingFilter)
                       (implicit val materializer: Materializer) extends HttpFilters {

  override val filters = {

    Seq(
      loggingFilter,
      new BasicAuthenticationFilter(BasicAuthenticationFilterConfiguration parse configuration, materializer),
      new GzipFilter(shouldGzip = (request, response) =>
        response.body.contentType.exists(_.startsWith("text/html")) || request.path.endsWith("jsroutes.js"))
    )

  }

}
