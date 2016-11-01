package com.github.http4sbin.http.services

import org.http4s.HttpService
import org.http4s.dsl._

import argonaut._, Argonaut._

object HeaderService {

  val service = HttpService {
    case req @ GET -> Root / "headers" => {
      Ok(req.headers.foldLeft(jEmptyObject)((i, h) => i.->:(h.name.value := h.value)).toString())
    }
  }
}
