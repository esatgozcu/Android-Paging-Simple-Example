package com.esatgozcu.pagingsimpleexample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.esatgozcu.pagingsimpleexample.dataSource.PagingDataSource
import com.esatgozcu.pagingsimpleexample.infrastructure.FakeServer
import com.esatgozcu.pagingsimpleexample.infrastructure.ItemModel
import kotlinx.coroutines.flow.Flow

class ItemsViewModel : ViewModel() {
    val user: Flow<PagingData<ItemModel>> = Pager(PagingConfig(pageSize = FakeServer.Instance.mLimit)) {
        PagingDataSource()
    }.flow
        // cachedIn allows paging to remain active in the viewModel scope, so even if the UI
        // showing the paged data goes through lifecycle changes, pagination remains cached and
        // the UI does not have to start paging from the beginning when it resumes.
        .cachedIn(viewModelScope)
}