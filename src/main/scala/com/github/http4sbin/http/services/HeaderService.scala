package com.github.http4sbin.http.services

import org.http4s.HttpService
import org.http4s.dsl._

object HeaderService {

  val service = HttpService {
    case req @ GET -> Root / "headers" => {
      Ok(req.headers.toString())
    }
  }
}
