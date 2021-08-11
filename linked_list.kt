/*
SINGLY LINKED LIST USING KOTLIN
Advantages:
LinkedList is that insertions and deletion can be done very quickly.
If you just want to insert an element right to the beginning of the LinkedList, that can be done in constant time O(1).
If you want to delete an element at the beginning of a LinkedList, again constant time O(1).
Disadvantages:
If you want to append an item at the end of the list, that might require going through the whole LinkedList, until you reach the very last element, and then inserting the element, this would take Linear time O(n).
 */

import java.lang.IndexOutOfBoundsException

data class Node<T>(var data: T, var next: Node<T>? = null) {
    override fun toString(): String {
        return if (this.next != null) {
            "${this.data} -> ${this.next.toString()}"
        } else {
            "${this.data}"
        }
    }
}

class LinkedList<T> {
    private var head: Node<T>? = null
    private var size = 0

    override fun toString(): String {
        return if (isEmpty()) {
            "List is empty"
        } else {
            head.toString()
        }
    }

    private fun isEmpty(): Boolean {
        return this.size == 0
    }

    fun length(): Int {
        return if (isEmpty()) {
             0
         } else {
             this.size
         }
     }

    // o(n)
    fun appendToLast(value: T){
        if(this.head == null) {
            this.head = Node(data = value)
            this.size++
        } else {
            var currentNode = this.head
            while (currentNode?.next !=  null) {
                currentNode = currentNode.next
            }
            currentNode?.next = Node(data = value)
            this.size++
        }
    }

    // o(1)
    fun appendToFirst(value: T) {
        if (this.head == null) {
            this.head = Node(data = value)
            this.size++
        } else {
            val headNode = this.head
            this.head = Node(data = value)
            this.head?.next = headNode
            this.size++
        }
    }

    // o(n)
    fun insert(index: Int, value: T) {
        if (index > this.size - 1) {
            throw IndexOutOfBoundsException("Index out of range")
        } else if (index == 0 ){
            return appendToFirst(value = value)
        } else if (index == size - 1) {
            return appendToLast(value = value)
        } else {
            var currentNode = this.head
            var counter = 0

            while(currentNode?.next != null) {
                // to get the previous node
                if(counter + 1 == index) {
                    break
                }
                currentNode = currentNode.next
                counter++
            }

            val nodeToBeInserted = Node(data = value)
            nodeToBeInserted.next = currentNode?.next
            currentNode?.next = nodeToBeInserted
            this.size++
        }
    }

    //o(n)
    fun removeFromLast() {
        if (this.head == null) {
            return
        }
        var currentNode = this.head

        while(currentNode?.next != null) {
            if(currentNode.next?.next == null) {
                break
            }
            currentNode = currentNode.next
        }

        currentNode?.next = null
        this.size--
    }

    //o(1)
    fun removeFromFirst() {
        if (this.head == null) {
            return
        }
        val  nodeToBeRemoved = this.head
        this.head = null
        if (nodeToBeRemoved?.next != null) {
            this.head = nodeToBeRemoved.next
        }
        this.size--
    }

    fun remove(index: Int) {
        if (index > this.size - 1) {
            throw IndexOutOfBoundsException("Index out of range")
        } else if (index == 0 ){
            return removeFromFirst()
        } else if (index == size - 1) {
            return removeFromLast()
        } else {
            var currentNode = this.head
            var counter = 0

            while(currentNode?.next != null) {
                // to get the previous node
                if(counter + 1 == index) {
                    break
                }
                currentNode = currentNode.next
                counter++
            }

            currentNode?.next = currentNode?.next?.next
            this.size--
        }
    }
}
