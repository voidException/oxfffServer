package org.geilove.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 测试kotlin在java中的使用
 */
@RestController
class KotlinController {

    @RequestMapping("/kotlin")
    fun home(){
        System.out.println("sum of")
    }
}