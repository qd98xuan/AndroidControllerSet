package com.me.recyclerviewpaging

class Repo(
    val id:Int,
    val name:String,
    val description:String,
    val startCount:Int
)
class RepoResponse(
    val items:List<Repo>
)