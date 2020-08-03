package notes

import common.AbstractSimulation
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._


class ZDeleteExistingNoteSimulation extends AbstractSimulation {

  object Query {
    private val feeder = csv(dataFolder + "get-delete-note.csv").eager.circular

    val scenarioName = "Check deleting existing note record"
    val chainExecution: ChainBuilder = repeat(repeatCount) {
      feed(feeder)
        .exec(
          http(scenarioName)
            .delete(serviceUrl + "/notes/${ID}")
            .check(status.is(200)
            )
        )
    }
  }

  runScenario(Query.scenarioName, Query.chainExecution)

}
