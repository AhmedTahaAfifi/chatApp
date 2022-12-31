package com.example.chatapp.model

import com.example.chatapp.R

data class Category (
    val id:String?=null,
    val name:String?=null,
    val imageId:Int?=null
        ){
    companion object{
        const val ISLAM = "islam"
        const val GAMES = "games"
        const val MUSIC = "music"
        const val SPORTS = "sports"
        const val VIDEO = "video"
        const val TECHNOLOGY = "technology"
        fun fromId(catId:String):Category{
            when(catId){
                ISLAM->{
                    return Category(
                        ISLAM,
                        name = "islam",
                        imageId = R.drawable.ic_islam
                    )
                }
                GAMES->{
                    return Category(
                        GAMES,
                        name = "games",
                        imageId = R.drawable.ic_games
                    )
                }
                MUSIC->{
                    return Category(
                        MUSIC,
                        name = "music",
                        imageId = R.drawable.ic_music
                    )
                }
                SPORTS->{
                    return Category(
                        SPORTS,
                        name = "sports",
                        imageId = R.drawable.ic_sports
                    )
                }
                VIDEO->{
                    return Category(
                        VIDEO,
                        name = "video",
                        imageId = R.drawable.ic_video
                    )
                }
                else->{
                    return Category(
                        TECHNOLOGY,
                        name = "technology",
                        imageId = R.drawable.ic_technology
                    )
                }
            }
        }
        fun getCategoryList():List<Category>{
            return listOf(
                fromId(ISLAM),
                fromId(GAMES),
                fromId(MUSIC),
                fromId(SPORTS),
                fromId(VIDEO),
                fromId(TECHNOLOGY)
            )
        }
    }


}
