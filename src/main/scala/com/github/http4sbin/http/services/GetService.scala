package com.github.http4sbin.http.services

import org.http4s.HttpService
import org.http4s.dsl._

import argonaut._, Argonaut._

import com.github.http4sbin.http.ArgonautInstances._
import com.github.http4sbin.http.Http4sHelpers.{getAbsoluteURI, HeadersEncodeJson}

object GetService {

  val service = HttpService {
    case req @ GET -> Root / "get" => {
      Ok(Json(
        "args"    := req.uri.query.params,
        "origin"  := req.remote.map(_.getAddress.toString),
        "headers" := req.headers,
        "url"     := getAbsoluteURI(req)
      ))
    }
  }
}
