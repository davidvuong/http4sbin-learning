package com.github.http4sbin.http.services

import scala.util.Random
import org.http4s.HttpService
import org.http4s.dsl._
import argonaut._
import Argonaut._

import scalaz._
import scalaz.stream._
import scalaz.concurrent.Task

import com.github.http4sbin.http.ArgonautInstances._

object BytesService {

  object RandomBytesInVar {
    val MIN_RANDOM_N = 0
    val MAX_RANDOM_N = 30

    def unapply(str: String): Option[Int] = {
      \/.fromTryCatchNonFatal(str.toInt).toOption.filter(n => n >= MIN_RANDOM_N || n <= MAX_RANDOM_N)
    }
  }

  private def getRandomBytes(n: Int): String = {
    val randomBytes: Process[Task, Byte] = Process.repeatEval(
      Task { (Random.nextInt(256) - 128).toByte }
    )
    randomBytes.take(100).runLog.run.foldLeft("")(_ + _.toChar)
  }

  val service = HttpService {
    case req @ GET -> Root / "bytes" / RandomBytesInVar(n) => {
      val randomBytes: Process[Task, Byte] = Process.repeatEval(
        Task { (Random.nextInt(256) - 128).toByte }
      )
      Ok(Json("bytes" := getRandomBytes(n)))
    }
  }
}
