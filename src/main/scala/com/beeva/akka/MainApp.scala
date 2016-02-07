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

        val creator = system.actorOf(Props(new Creator("This string is going to be split and reversed")), "creator")
        //Add a watcher so that when the creator stops, the whole system stops
        system.actorOf(Props(classOf[Terminator], creator), "terminator")

        creator ! Messages.Create
    }

    class Creator(data: String) extends Actor with ActorLogging {

        override def receive: Receive = {
            case Messages.Create =>
                val stringer = context.actorOf(Props(new Stringer(data)), "stringer")

                stringer ! Messages.Split

            case Messages.Done => context.stop(self)
        }
    }

    class Terminator(ref: ActorRef) extends Actor with ActorLogging {
        context watch ref
        def receive = {
            case Terminated(_) =>
                log.info("{} has terminated, shutting down system", ref.path)
                context.system.terminate()
        }

        //https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch13s09.html
    }

}
