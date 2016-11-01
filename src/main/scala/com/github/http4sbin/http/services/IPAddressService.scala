package com.github.http4sbin.http.services

import org.http4s.HttpService
import org.http4s.dsl._

import argonaut._, Argonaut._

object IPAddressService {

  val service = HttpService {
    case req @ GET -> Root / "ip" => {
      req.remote match {
        case Some(ipAddress) => Ok(Json("ip" := ipAddress.getAddress.toString).toString)
        case None => NoContent()
      }
    }
  }
}
