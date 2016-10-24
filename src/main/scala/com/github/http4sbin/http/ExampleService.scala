package com.github.http4sbin.http

import org.http4s.HttpService
import org.http4s.dsl._

object ExampleService {

  val service = HttpService {
    case req @ GET -> Root => {
      Ok("Example done!")
    }
  }
}
