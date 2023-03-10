package com.esatgozcu.pagingsimpleexample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.esatgozcu.pagingsimpleexample.infrastructure.ItemModel

@Composable
fun ItemsView(viewModel: ItemsViewModel = ItemsViewModel()) {

    val itemList: LazyPagingItems<ItemModel> = viewModel.user.collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        items(itemList) { item ->
            Text(
                modifier = Modifier.padding(10.dp),
                text = item?.id.toString()
            )
        }

        itemList.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    //You can add modifier to manage load state when first time response page is loading
                    item{
                        Column(
                            modifier = Modifier.fillParentMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                loadState.append is LoadState.Loading -> {
                    //You can add modifier to manage load state when next response page is loading
                    item{
                        CircularProgressIndicator()
                    }
                }
                loadState.append is LoadState.Error -> {
                    //You can use modifier to show error message
                }
            }
        }
    }
}