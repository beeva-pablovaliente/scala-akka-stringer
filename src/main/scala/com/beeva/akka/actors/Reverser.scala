package com.beeva.akka.actors

import akka.actor.{ActorLogging, Actor}
import com.beeva.akka.messages.Messages

/**
  * Created by rekkeb on 31/1/16.
  */

class Reverser extends Actor with ActorLogging {
    def receive = {
        case chunk: String =>
            log.info(chunk.reverse)

            sender() ! Messages.Done
    }
}
