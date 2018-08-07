package com.nileshdeokar.healthapp

import android.util.Log
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testStringBuilder(){
        val builder = NStringBuilder()

        builder.append("A")
        assertEquals("A",builder.toString())
        builder.append("B")
        assertEquals("AB",builder.toString())
        builder.append("C")
        assertEquals("ABC",builder.toString())
        builder.append("C12323i38592398538013049583")
        assertEquals("ABCC12323i38592398538013049583",builder.toString())
        builder.append("12121")
        assertEquals("ABCC12323i3859239853801304958312121",builder.toString())
        builder.append("ATASAAAAA")
        assertEquals("ABCC12323i3859239853801304958312121ATASAAAAA",builder.toString())
//
    }

    @Test
    @Throws(Exception::class)
    fun array_reverse() {

        val a = intArrayOf(1, 2, 3, 4)

        val reversed = IntArray(a.size)

        var i = a.size - 1
        while (i <= 0) {

            reversed[i - (i - 1)] = a[i]
            i--
        }

        Log.d("arra","st == "+reversed.toString())
        assertArrayEquals(a, reversed)

        assertEquals(4, (2 + 2).toLong())
    }

}
