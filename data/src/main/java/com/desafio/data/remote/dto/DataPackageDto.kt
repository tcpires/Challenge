package com.desafio.data.remote.dto

sealed class DataPackageDto {
    data class Response(
        var name: String,
        var title: String,
        var description: String,
        var homepage: String,
        var version: String,
        var license: String,
        var sources: ArrayList<Sources> = arrayListOf(),
        var resources: ArrayList<Resources> = arrayListOf()
    )

    data class Sources(
        var title: String,
        var path: String
    )

    data class Resources(
        var name: String,
        var path: String,
        var format: String,
        var mediatype: String,
        var bytes: Int,
        var schema: Schema
    )

    data class Schema(
        var fields: ArrayList<Fields>
    )

    data class Fields(
        var name: String,
        var type: String,
        var description: String
    )
}