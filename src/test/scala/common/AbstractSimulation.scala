package common

import java.io.File

import com.typesafe.config.ConfigFactory
import io.gatling.core.Predef._
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.session.Expression
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

abstract class AbstractSimulation extends Simulation {

  private val parsedConfig = ConfigFactory.parseFile(new File("env.properties"))
  private val conf = ConfigFactory.load(parsedConfig)

  protected val serviceUrl: String = conf.getString("service-url")
  protected val authorization: String = conf.getString("authorization")
  protected val username: String = conf.getString("username")
  protected val repeatCount: Expression[Int] = conf.getInt("repeat-count")
  protected val dataFolder: String = conf.getString("data-folder")


  protected val commonHeaders = Map(
    "Content-Type" -> "application/json",
    "Authorization" -> authorization,
    "username" -> username,
  )

  def runScenario(scenarioName: String, chainBuilder: ChainBuilder): Unit = {
    val httpProtocol = http
      .headers(commonHeaders)

    val scn = scenario(scenarioName).exec(chainBuilder)
    val concurrentUsers = conf.getInt("concurrent-users")
    setUp(
      scn.inject(atOnceUsers(concurrentUsers))).protocols(httpProtocol)
  }

  def runScenario(scenarioName: String, actionBuilder: ActionBuilder): Unit = {
    val httpProtocol = http
      .headers(commonHeaders)

    val scn = scenario(scenarioName).repeat(repeatCount) {
      exec(actionBuilder)
    }

    val concurrentUsers = conf.getInt("concurrent-users")
    setUp(
      scn.inject(atOnceUsers(concurrentUsers))).protocols(httpProtocol)
  }
}
