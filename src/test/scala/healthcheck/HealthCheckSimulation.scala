package healthcheck

import common.AbstractSimulation
import io.gatling.core.Predef._
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.http.Predef._


class HealthCheckSimulation extends AbstractSimulation {

  object Query {

    val scenarioName = "Check service is up and running"
    val actionExecution: ActionBuilder =
      http(scenarioName)
        .get(serviceUrl + "/healthcheck")
        .check(status.is(200)
        )
  }

  runScenario(Query.scenarioName, Query.actionExecution)

}
