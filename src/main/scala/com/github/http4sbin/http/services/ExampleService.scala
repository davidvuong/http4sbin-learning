package com.github.http4sbin.http.services

import org.http4s.dsl._
import org.http4s.{HttpService, ParseFailure, QueryParamDecoder, QueryParameterValue}

import scalaz.Scalaz._
import scalaz._

sealed trait SortDirection
  case object Asc extends SortDirection
  case object Desc extends SortDirection

object SortDirection {
  def fromString(string: String): Option[SortDirection] = {
    string.toLowerCase match {
      case "asc" => Some(Asc)
      case "desc" => Some(Desc)
      case _ => None
    }
  }
}

case class SkynetId(id: String)

object ExampleService {
  protected class MatchPathVar[A](cast: String => Option[A]) {
    def unapply(str: String): Option[A] = cast(str)
  }

  object SortDirectionParamMatcher extends QueryParamDecoderMatcher[SortDirection]("sort")
  implicit def searchSortDirectionDecoder = new QueryParamDecoder[SortDirection] {
    def decode(value: QueryParameterValue): ValidationNel[ParseFailure, SortDirection] =
      SortDirection.fromString(value.value) match {
        case Some(sort) => sort.successNel
        case None       => ParseFailure("invalid sort must be asc or desc", "internal err").failureNel
      }
  }

  object SkynetIdMatcher extends MatchPathVar(a => SkynetId(a).some)

  val service = HttpService {
    case req @ GET -> Root / "search" / SkynetIdMatcher(skynetId) :? SortDirectionParamMatcher(sort) => {
      Ok("good")
    }
  }
}
