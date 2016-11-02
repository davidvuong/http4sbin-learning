package com.github.http4sbin.http.services

import scala.util.Random.nextBytes

import org.http4s.HttpService
import org.http4s.dsl._

import argonaut._, Argonaut._

import com.github.http4sbin.http.ArgonautInstances._

object BytesService {

  object RandomBytesInVar {
    def unapply(str: String): Option[Int] = {
      try {
        str.toInt match {
          case n: Int if n < 1  => None
          case n: Int if n > 30 => None
          case n: Int => Some(n)
        }
      } catch {
        case e: NumberFormatException => None
      }
    }
  }

  private def getRandomBytes(n: Integer): String = {
    val randomBytes = new Array[Byte](n)
    nextBytes(randomBytes)
    randomBytes.foldLeft("")(_ + _.toChar)
  }

  val service = HttpService {
    case req @ GET -> Root / "bytes" / RandomBytesInVar(n) => {
      Ok(Json("bytes" := getRandomBytes(n).asJson))
    }
  }
}
