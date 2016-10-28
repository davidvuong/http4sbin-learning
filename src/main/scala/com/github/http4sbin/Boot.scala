package com.github.http4sbin

import com.github.http4sbin.http.middleware.LoggingMiddleware
import com.github.http4sbin.http.{PingPongService, ExampleService, UserAgentService}
import org.http4s.HttpService
import org.http4s.server.syntax._
import org.http4s.server.ServerApp
import org.http4s.server.blaze.BlazeBuilder

import scala.util.Try

object Boot extends ServerApp {
  type Middleware = HttpService => HttpService

  val middlewareStack: Middleware = LoggingMiddleware(_)
  val service = List(
    PingPongService.service,
    UserAgentService.service,
    ExampleService.service
  ).reduce((combined, service) => combined orElse service)
  val serviceWithMiddleware = middlewareStack(service)

  def server(args: List[String]) = {
    BlazeBuilder
      .bindHttp(Try(System.getenv("PORT").toInt).toOption.getOrElse(8080), "0.0.0.0")
      .mountService(serviceWithMiddleware, "/")
      .start
  }
}
