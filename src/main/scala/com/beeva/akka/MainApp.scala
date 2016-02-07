package com.beeva.akka

import akka.actor._
import com.beeva.akka.actors.Stringer
import com.beeva.akka.messages.Messages

/**
  * Actors Formation
  *
  */
object MainApp {

    def main(args: Array[String]): Unit = {
        val system = ActorSystem("akka-string")

        val stringer = system.actorOf(Props(new Stringer("This string is going to be split and reversed")), "stringer")
        system.actorOf(Props(classOf[Terminator], stringer), "terminator")

        //TODO Send the Split message to the stringer actor
    }

    class Terminator(ref: ActorRef) extends Actor with ActorLogging {
        context watch ref
        def receive = {
            case Terminated(_) =>
                log.info("{} has terminated, shutting down system", ref.path)
                context.system.terminate()
        }
    }

}
