package com.rtitipco.kafkatofolder.controller;

import com.rtitipco.kafkatofolder.processor.Kafkaprocessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;


@RestController

public class KafkaRestController {


    @Autowired
     Kafkaprocessor Processor;

    @PostMapping("/pushtokafka")
    public  void PostMessage(@RequestBody String strMsg)
    {
        Processor.PostMessageToKafka(strMsg);

    }


}
