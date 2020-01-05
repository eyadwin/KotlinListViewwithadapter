package com.example.kotlin_listview_with_adapter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var definitions = ArrayList<String>()
    private var words = ArrayList<String>()
    private var wordToDefinition =HashMap<String,String>()
    private lateinit var myAdapter :ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setupList()
        readWordsFiles()
        setupList2()
        definition_list.setOnItemClickListener { _, _, index, _ ->
            definitions.removeAt(index)
            myAdapter.notifyDataSetChanged()

        }


    }
    private fun readWordsFiles(){
        val reader = Scanner(resources.openRawResource(R.raw.words))
        while(reader.hasNextLine()){
            val line = reader.nextLine()
            Log.d("WordFile","The line is $line")
            //pied - multicolored, usually in blotches
            val parts = line.split("-")
            words.add(parts[0])
            wordToDefinition.put(parts[0],parts[1])
        }
    }
    private fun setupList2() {

        val rand = java.util.Random()
        val index = rand.nextInt(words.size)
        val word = words[index]
        the_word.text=word

        definitions.clear()
        definitions.add(wordToDefinition[word]!!)//pick the correct definition of the word
        words.shuffle()

        for(otherWord in words.subList(0,4)) {
            definitions.add(wordToDefinition[otherWord]!!)//pick 4 wrong deinitions
        }

        myAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_expandable_list_item_1, definitions
        )

        definition_list.adapter = myAdapter
    }
    private fun setupList() {
        var list = ArrayList<String>()
        list.add("Hello")
        list.add("Kotlin")
        list.add("Soso")
        list.add("Mouse")
        list.add("Pizza")
        list.add("Coffee")

        val rand = java.util.Random()
        val index = rand.nextInt(list.size)
        val word = list[index]
        the_word.text=word



        definitions.add("a greeting")
        definitions.add("mobile development language")
        definitions.add("the name of my cat")
        definitions.add("run from the cat")
        definitions.add("my best food :)")
        definitions.add("have caffeine")

        definitions.shuffle()

        myAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_expandable_list_item_1, definitions
        )

        definition_list.adapter = myAdapter

    }
}