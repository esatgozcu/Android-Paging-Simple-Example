package com.esatgozcu.pagingsimpleexample.infrastructure

class FakeServer {
    //Return mock data list for Pagination
    //When limit out, return empty data list
    object Instance{
        //Server list of item size
        var limitOfItem = 100
        //Pagination queries
        var mLimit = 20

        fun getItemList(page: Int): List<ItemModel> {
            // 0..19 --> 20..39 --> 40..59
            val range = (page* mLimit) until page* mLimit + mLimit

            return if (range.first == limitOfItem){
                emptyList()
            } else{
                range.map {
                    ItemModel(id = it)
                }
            }
        }
    }
}