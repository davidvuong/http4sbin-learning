package com.github.http4sbin.http.services

import org.http4s.HttpService
import org.http4s.dsl._
import org.http4s.argonaut.ArgonautInstances

import argonaut._, Argonaut._

object IPAddressService extends ArgonautInstances {

  val service = HttpService {
    case req @ GET -> Root / "ip" => {
      Ok(Json("ip" := req.remote.map(_.getAddress.toString).asJson))
    }
  }
}
