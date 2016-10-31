package com.github.http4sbin.http.services

import org.http4s.HttpService
import org.http4s.dsl._

object PingPongService {

  val service = HttpService {
    case req @ GET -> Root / "ping" => {
      Ok("pong")
    }
  }
}
