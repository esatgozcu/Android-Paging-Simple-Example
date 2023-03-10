# Jetpack Paging Simple Example
This app is made for Jetpack Paging. We made a simple app without retrofit, di etc. and a rudimentary UI to understand Paging more easy.
<br />
<br /> App contains:<br />
* Jetpack Compose<br/>
* Paging v3<br/>

# Api connection
We don't need api connection for pageble data. Here is simple fake server.

```kotlin
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
```
## Result
<br />
<img src="https://user-images.githubusercontent.com/35576161/224275389-6c5e2c28-815e-4b06-acbd-41924c9da007.gif" width="216" height="384"/>
