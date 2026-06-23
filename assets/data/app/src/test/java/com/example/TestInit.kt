package com.example

import org.junit.Test
import com.example.data.RoadmapRepo

class TestInit {
    @Test
    fun testInit() {
        println(RoadmapRepo.roadmaps.size)
    }
}
