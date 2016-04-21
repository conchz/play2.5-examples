package controllers

import play.api.Logger

trait Logging {
  lazy val log = Logger(this.getClass)
}
