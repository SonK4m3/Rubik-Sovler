package com.example.rubiksovler.data

class Stack<T> {
    private val stack: MutableList<T> = mutableListOf()

    fun push(element: T) {
        stack.add(element)
    }

    fun pop(): T? {
        if (isEmpty()) {
            return null
        }
        return stack.removeAt(stack.size - 1)
    }

    fun peek(): T? {
        if (isEmpty()) {
            return null
        }
        return stack[stack.size - 1]
    }

    fun isEmpty(): Boolean {
        return stack.isEmpty()
    }

    fun size(): Int {
        return stack.size
    }

    fun popAll() {
        stack.clear()
    }

    fun toArrayList(): ArrayList<T> {
        val array = ArrayList<T>()
        for(ele in stack) {
            array.add(ele)
        }
        return array
    }
}