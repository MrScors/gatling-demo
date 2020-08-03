package notes

import common.AbstractSimulation
import io.gatling.core.Predef._
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.http.Predef._


class GetAllExistingNotesSimulation extends AbstractSimulation {

  object Query {

    val scenarioName = "Check getting all notes list"
    val actionExecution: ActionBuilder =
      http(scenarioName)
        .get(serviceUrl + "/notes")
        .check(status.is(200)
        )
  }

  runScenario(Query.scenarioName, Query.actionExecution)

}
