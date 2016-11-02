package com.github.http4sbin.http.services

import org.http4s.HttpService
import org.http4s.dsl._

import argonaut._, Argonaut._

import com.github.http4sbin.http.ArgonautInstances._

object IPAddressService {

  val service = HttpService {
    case req @ GET -> Root / "ip" => {
      Ok(Json("ip" := req.remote.map(_.getAddress.toString).asJson))
    }
  }
}
