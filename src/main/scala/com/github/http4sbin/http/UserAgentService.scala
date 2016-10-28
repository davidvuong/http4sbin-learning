package com.github.http4sbin.http

import org.http4s.HttpService
import org.http4s.headers.`User-Agent`
import org.http4s.dsl._

object UserAgentService {

  val service = HttpService {
    case req @ GET -> Root / "user-agent" => {
      req.headers.get(`User-Agent`) match {
        case Some(userAgent) => Ok(userAgent.toString)
        case None => NoContent()
      }
    }
  }
}
