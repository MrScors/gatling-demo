package notes

import common.AbstractSimulation
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._


class CreateNewNoteSimulation extends AbstractSimulation {

  object Query {
    private val feeder = csv(dataFolder + "create-note.csv").eager.circular

    val scenarioName = "Check creating new note record"
    val chainExecution: ChainBuilder = repeat(repeatCount) {
      feed(feeder)
        .exec(
          http(scenarioName)
            .post(serviceUrl + "/notes")
            .body(ElFileBody("payload/post-create-note.json")).asJson
            .check(status.is(201)
            )
        )
    }
  }

  runScenario(Query.scenarioName, Query.chainExecution)

}
