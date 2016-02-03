package com.beeva.akka

import org.junit.{Assert, Test}

/**
  * Created by rekkeb on 31/1/16.
  */
@Test
class AppTest {

    @Test
    def testMessage() = {
        Assert.assertEquals("Hello World!", "Hello World!")
    }

}
