package com.beeva.akka.actors

import akka.actor.{ActorRef, ActorLogging, Actor, Props}
import com.beeva.akka.messages.Messages

import scala.runtime.ScalaRunTime

/**
  * Created by rekkeb on 31/1/16.
  */
class Stringer(val data:String) extends Actor with ActorLogging{

    var numberOfChunks: Int = 0
    var chunksProcessed: Int = 0

    var actorSplit: ActorRef = null


//    override def preStart(): Unit = {
//        // create the reverser actor
//        val reverser = context.actorOf(Props[Reverser], "reverser")
//        // send it the string to reverse
//        reverser ! data
//    }

    def receive = {
        case Messages.Split =>
            //Store the actor who sends the Split message
            actorSplit = sender()

            val chunks = data.split(" ")
            numberOfChunks = chunks.length
            log.info("Split: {}", ScalaRunTime.stringOf(chunks))

            for (chunk <- chunks) {
                // create the reverser actor
                val reverser = context.actorOf(Props[Reverser])
                // tell it to perform the action
                reverser ! chunk
            }

        // when the reverser is done, and there is no more chunk to reverse
        // stop this actor and with it the application
        case Messages.ChunkProcessed =>
            chunksProcessed+=1

            if (chunksProcessed >= numberOfChunks) {
                log.info("String processed")
                actorSplit ! Messages.Done
                context.stop(self)
            }
    }

}
