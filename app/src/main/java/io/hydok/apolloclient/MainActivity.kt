package io.hydok.apolloclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo3.ApolloClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql/")
            .build()



        lifecycleScope.launch {
            val gqlResult = apolloClient.query(GetCharactersQuery()).execute()

            Log.d("GraphQL", gqlResult.operation.document())
            Log.d("GraphQL", gqlResult.operation.id())

            gqlResult.data?.characters?.results?.forEach {
                Log.d("GraphQL", it.toString())
            }
        }
    }
}