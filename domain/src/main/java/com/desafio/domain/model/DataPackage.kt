package com.desafio.domain.model

class DataPackage {

    data class Response(
        var name: String,
        var title: String,
        var description: String,
        var homepage: String,
        var version: String,
        var license: String,
        var sources: List<Sources>,
        var resources: List<Resources>
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
        var fields: List<Fields>
    )

    data class Fields(
        var name: String,
        var type: String,
        var description: String
    )
}