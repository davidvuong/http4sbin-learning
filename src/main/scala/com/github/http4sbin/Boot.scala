package com.github.http4sbin

import com.github.http4sbin.http.ExampleService
import org.http4s.server.ServerApp
import org.http4s.server.blaze.BlazeBuilder

import scala.util.Try

object Boot extends ServerApp {

  val service = ExampleService.service

  def server(args: List[String]) = {
    BlazeBuilder
      .bindHttp(Try(System.getenv("PORT").toInt).toOption.getOrElse(8080), "0.0.0.0")
      .mountService(service, "/")
      .start
  }
}