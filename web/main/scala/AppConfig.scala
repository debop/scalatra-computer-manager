import com.typesafe.config.Config
import org.slf4j.LoggerFactory

/**
 * AppConfig
 * @author Sunghyouk Bae
 */
class AppConfig(config: Config) {
  private lazy val log = LoggerFactory.getLogger(getClass)

  val port = config.getInt("app.port")
  val hostname = config.getString("app.hostname")
  val useHttp = config.getBoolean("app.useHttp")
  val useHttps = config.getBoolean("app.useHttps")
  val forceHttps = config.getBoolean("app.forceHttps")
  val lifecycle = if (config.hasPath("app.lifecycle")) Some(config.getString("app.lifecycle")) else None

  log.info(s"AppConfig: port=$port, hostname=$hostname, useHttp=$useHttp, useHttps=$useHttps, lifecycle=$lifecycle")
}
