package com.github.http4sbin.http.services

import org.http4s.HttpService
import org.http4s.dsl._

import com.github.http4sbin.http.Http4sHelpers.getHeadersAsJson

object HeaderService {

  val service = HttpService {
    case req @ GET -> Root / "headers" => {
      Ok(getHeadersAsJson(req.headers).toString)
    }
  }
}
