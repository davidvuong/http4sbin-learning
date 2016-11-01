package com.github.http4sbin.http.middleware

import scalaz.concurrent.Task
import org.http4s.HttpService

object PrintRequestResponseMiddleware {

  def apply(service: HttpService): HttpService = {
    HttpService.lift { request =>
      Task {
        println(request)
      }.flatMap { _ =>
        service.run(request).map { response =>
          println(response)
          response
        }
      }
    }
  }
}
