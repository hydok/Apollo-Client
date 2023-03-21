package io.hydok.apolloclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo3.ApolloClient
import io.hydok.apolloclient.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql/")
            .build()

        lifecycleScope.launch {
            val gqlResult = apolloClient.query(GetCharactersQuery()).execute()

            //Log.d("GraphQL", gqlResult.operation.document())
            //Log.d("GraphQL", gqlResult.operation.id())

            gqlResult.data?.characters?.results?.let {
                binding.profileRecyclerview.adapter = ProfileAdapter(it)
            }
        }
    }
}