package com.beeva.akka

import akka.actor.{Props, ActorSystem}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import com.beeva.akka.actors.Stringer
import com.beeva.akka.messages.Messages
import org.scalatest.{BeforeAndAfterAll, WordSpecLike, MustMatchers}


/**
  * Created by rekkeb on 31/1/16.
  */
class StringerTest(_system: ActorSystem) extends TestKit(_system)
    with ImplicitSender
    with WordSpecLike
    with MustMatchers
    with BeforeAndAfterAll {


    def this() = this(ActorSystem("TestStringer"))

    override def afterAll {
        TestKit.shutdownActorSystem(system)
    }


    "A Stringer Actor" must {
        "receive messages" in {
            //TODO Implements the business logic test
        }
    }

}
