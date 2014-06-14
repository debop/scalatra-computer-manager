import com.typesafe.config.ConfigFactory
import org.eclipse.jetty.server.{NetworkTrafficServerConnector, Server}
import org.eclipse.jetty.servlet.DefaultServlet
import org.eclipse.jetty.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener
import org.scalatra.servlet.ScalatraListener._
import org.slf4j.LoggerFactory

/**
 * JettyLauncher
 * @author Sunghyouk Bae
 */
class JettyLauncher extends App {

  private lazy val log = LoggerFactory.getLogger(getClass)

  val env = sys.props.get(org.scalatra.EnvironmentKey).getOrElse("development")

  // load config using typesafe config
  val config: AppConfig = AppConfig(ConfigFactory.load(env))
  val hostname: String = config.hostname
  val port: Int = config.port
  val lifecycle: Option[String] = config.lifecycle

  log.info(s"hostname=$hostname, port=$port, lifecycle=$lifecycle")

  // setup server
  val server = new Server()
  server.setStopAtShutdown(true)

  val connector = new NetworkTrafficServerConnector(server)
  connector.setHost(hostname)
  connector.setPort(port)
  connector.setIdleTimeout(90000)
  server.addConnector(connector)

  val context = new WebAppContext()
  context.setServer(server)
  context.setContextPath("/")
  context.setResourceBase("src/main/webapp")

  lifecycle.foreach(l => context.setInitParameter(LifeCycleKey, l))
  context.addEventListener(new ScalatraListener())

  // default servlet: context.addServlet(classOf[DefaultServlet], "/")
  context.addServlet(classOf[DefaultServlet], "/")
  server.setHandler(context)

  server.start()
  server.join()
}
