package com.github.http4sbin.http

import org.http4s.{Headers, Query, Request}
import argonaut._, Argonaut._

object Http4sHelpers {

  def getHeadersAsJson(headers: Headers): Json =
    headers.foldLeft(jEmptyObject)((json, h) => json.->:(h.name.value := h.value))

  def getQueryAsJson(query: Query): Json =
    query.foldLeft(jEmptyObject)((json, t) => json.->:(t._1 := t._2))

  def getAbsoluteURI(request: Request): String = {
    val isSecure = request.isSecure match {
      case None           => false
      case Some(security) => security
    }
    val protocol = if (isSecure) "https://" else "http://"
    s"$protocol${request.serverAddr}${request.uri.renderString}"
  }
}
