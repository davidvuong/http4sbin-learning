package com.github.http4sbin

import com.github.http4sbin.http.middleware.PrintRequestResponseMiddleware
import com.github.http4sbin.http.services.{
  HeaderService, PingPongService, UserAgentService, IPAddressService, GetService, BytesService
}

import org.http4s.HttpService
import org.http4s.server.syntax._
import org.http4s.server.ServerApp
import org.http4s.server.blaze.BlazeBuilder

import scala.util.Try

object Boot extends ServerApp {

  type Middleware = HttpService => HttpService

  val middlewareStack: Middleware = PrintRequestResponseMiddleware(_)
  val service = List(
    PingPongService.service,
    UserAgentService.service,
    HeaderService.service,
    IPAddressService.service,
    GetService.service,
    BytesService.service
  ).reduce((combined, service) => combined orElse service)
  val serviceWithMiddleware = middlewareStack(service)

  def server(args: List[String]) = {
    BlazeBuilder
      .bindHttp(Try(System.getenv("PORT").toInt).toOption.getOrElse(8080), "0.0.0.0")
      .mountService(serviceWithMiddleware, "/")
      .start
  }
}
