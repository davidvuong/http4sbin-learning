package com.github.http4sbin.http

import scala.concurrent.ExecutionContext

import org.http4s.server.Router
import org.http4s.HttpService
import org.http4s.dsl._

import com.github.http4sbin.http.{UserAgentService => Http4sUserAgentService}

object RootService {

  def service(implicit executionContext: ExecutionContext = ExecutionContext.global): HttpService = Router(
    ""  -> rootService,
    "/user-agent" -> Http4sUserAgentService.service
  )

  def rootService(implicit executionContext: ExecutionContext) = HttpService {
    case req @ GET -> Root => {
      Ok("Hello, world!")
    }
    case req @ GET -> Root / "ping" => {
      Ok("pong")
    }
  }
}
