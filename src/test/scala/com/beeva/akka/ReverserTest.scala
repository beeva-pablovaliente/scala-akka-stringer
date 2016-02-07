package com.beeva.akka

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import com.beeva.akka.actors.Reverser
import com.beeva.akka.messages.Messages
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}


/**
  * Created by rekkeb on 31/1/16.
  */
class ReverserTest extends TestKit(ActorSystem("testSystem"))
    with ImplicitSender
    with WordSpecLike
    with MustMatchers
    with BeforeAndAfterAll {


    override def afterAll {
        TestKit.shutdownActorSystem(system)
    }


    "A Reverser Actor" must {
        "send back a ChunkProcessed message" in {
            //TODO Implements the business logic test
        }
    }

}
