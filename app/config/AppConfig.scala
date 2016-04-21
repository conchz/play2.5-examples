package config

import com.typesafe.config.ConfigFactory

trait AppConfig {
  lazy val conf = ConfigFactory.defaultApplication()
}
