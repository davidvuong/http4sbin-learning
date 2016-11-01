package com.github.http4sbin.http.services

import org.http4s.HttpService
import org.http4s.dsl._

import argonaut._, Argonaut._

import com.github.http4sbin.http.Http4sHelpers.{getHeadersAsJson, getQueryAsJson, getAbsoluteURI}

object GetService {

  val service = HttpService {
    case req @ GET -> Root / "get" => {
      val ip = req.remote match {
        case Some(ipAddress) => jString(ipAddress.getAddress.toString)
        case None            => jNull
      }
      val headers = getHeadersAsJson(req.headers)
      val args = getQueryAsJson(req.uri.query)
      val url = getAbsoluteURI(req)

      Ok(Json(
        "args"    := args,
        "origin"  := ip,
        "headers" := headers,
        "url"     := url
      ).toString)
    }
  }
}
