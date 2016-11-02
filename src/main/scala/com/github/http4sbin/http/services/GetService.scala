package com.github.http4sbin.http.services

import org.http4s.HttpService
import org.http4s.dsl._
import org.http4s.argonaut.ArgonautInstances

import argonaut._, Argonaut._

import com.github.http4sbin.http.Http4sHelpers.{getAbsoluteURI, HeadersEncodeJson}

object GetService extends ArgonautInstances {

  val service = HttpService {
    case req @ GET -> Root / "get" => {
      Ok(Json(
        "args"    := req.uri.query.params.asJson,
        "origin"  := req.remote.map(_.getAddress.toString).asJson,
        "headers" := req.headers.asJson,
        "url"     := getAbsoluteURI(req)
      ))
    }
  }
}
