package com.esatgozcu.pagingsimpleexample.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.esatgozcu.pagingsimpleexample.infrastructure.FakeServer
import com.esatgozcu.pagingsimpleexample.infrastructure.ItemModel
import kotlinx.coroutines.delay

class PagingDataSource: PagingSource<Int, ItemModel>() {

    override fun getRefreshKey(state: PagingState<Int, ItemModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemModel> {
        val nextPage = params.key ?: 0
        //Call Server
        val data = FakeServer.Instance.getItemList(page = nextPage)
        //Wait Response
        delay(3_000L)

        return LoadResult.Page(
            data = data,
            prevKey = if (nextPage == 0) null else nextPage - 1,
            nextKey = if (data.isEmpty()) null else nextPage + 1
        )
    }
}