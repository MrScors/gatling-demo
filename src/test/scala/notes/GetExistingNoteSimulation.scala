package notes

import common.AbstractSimulation
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._


class GetExistingNoteSimulation extends AbstractSimulation {

  object Query {
    private val feeder = csv(dataFolder + "get-delete-note.csv").eager.circular

    val scenarioName = "Check getting existing note record"
    val chainExecution: ChainBuilder = repeat(repeatCount) {
      feed(feeder)
        .exec(
          http(scenarioName)
            .get(serviceUrl + "/notes/${ID}")
            .check(status.is(200)
            )
        )
    }
  }

  runScenario(Query.scenarioName, Query.chainExecution)

}
