package notes

import common.AbstractSimulation
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._


class EditExistingNoteSimulation extends AbstractSimulation {

  object Query {
    private val feeder = csv(dataFolder + "update-note.csv").eager.circular

    val scenarioName = "Check updating existing note record"
    val chainExecution: ChainBuilder = repeat(repeatCount) {
      feed(feeder)
        .exec(
          http(scenarioName)
            .put(serviceUrl + "/notes")
            .body(ElFileBody("payload/put-edit-note.json")).asJson
            .check(status.is(200)
            )
        )
    }
  }

  runScenario(Query.scenarioName, Query.chainExecution)

}
