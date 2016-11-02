package com.github.http4sbin.http.services

import org.http4s.HttpService
import org.http4s.dsl._
import org.http4s.headers.`User-Agent`

import argonaut._, Argonaut._

import com.github.http4sbin.http.ArgonautInstances._

object UserAgentService {

  val service = HttpService {
    case req @ GET -> Root / "user-agent" => {
      req.headers.get(`User-Agent`) match {
        case Some(userAgent) => Ok(Json("user-agent" := userAgent.toString))
        case None => NoContent()
      }
    }
  }
}
