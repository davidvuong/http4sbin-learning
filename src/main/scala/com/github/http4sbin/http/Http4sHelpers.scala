package com.github.http4sbin.http

import org.http4s.{Header, Headers, Request}
import argonaut._, Argonaut._

object Http4sHelpers {

  implicit val HeaderEncodeJson = EncodeJson[Header] { h =>
    Json(h.name.value := h.value)
  }

  implicit val HeadersEncodeJson = EncodeJson[Headers] { hs =>
    hs.foldLeft(jEmptyObject)((json, h) => json.deepmerge(h.asJson))
  }

  def getAbsoluteURI(request: Request): String = {
    val protocol = request.isSecure match {
      case None        => "http"
      case Some(false) => "http"
      case Some(true)  => "https"
    }
    s"$protocol://${request.serverAddr}${request.uri.renderString}"
  }
}
